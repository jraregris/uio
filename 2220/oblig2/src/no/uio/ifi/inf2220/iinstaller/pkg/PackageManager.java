package no.uio.ifi.inf2220.iinstaller.pkg;

import no.uio.ifi.inf2220.iinstaller.Globals;
import no.uio.ifi.inf2220.iinstaller.utils.Handy;
import no.uio.ifi.inf2220.iinstaller.control.CmdLoop;
import no.uio.ifi.inf2220.iinstaller.control.SinglePass;
import no.uio.ifi.inf2220.iinstaller.graph.DoubleDAG;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * This class takes care of most of the actions
 * requested by the {@link CmdLoop} and the {@link SinglePass}.
 *
 * <p>
 * This is the heart of the application, and it holds all
 * the {@link Repository}s which in turn holds all the
 * packages or {@link Program}s which they are called, to
 * avoid any namespace conflict with {@link java.lang.Package}.
 * It also holds our {@link DoubleDAG}, which provides information
 * about dendencies and reverse dependencies. 
 * </p>
 *
 *
 * @author bjarneh@ifi.uio.no
 */

public class PackageManager{


    private List<Repository> repositories;
    private List<String> installedCache;
    private List<String> allProgramsCache;
    private DoubleDAG dag;


    public PackageManager(){
        ;
    }

    /** Generate cache of installed packages */
    private void generateInstalledCache(){

        installedCache = new ArrayList<String>();

        File installDir = new File(Globals.iinstallerPkgDir);

        if(! installDir.isDirectory()){
            System.err.println("'"+Globals.iinstallerPkgDir+
                    "' does not seem to be a directory");
            return;
        }

        File[] dircontent = installDir.listFiles();
        for(File f : dircontent){
            installedCache.add(f.getName().replaceFirst("\\.pkg", ""));
        }
    }

    /** List all packages we have installed */
    public void listInstalled(){

        if(installedCache == null){ generateInstalledCache(); }

        if(installedCache.size() > 0){
            System.out.println("\n[ installed software ]\n");
            Handy.shellFormatText(installedCache);
        }else{
            System.out.println("\n [ nothing installed ]\n");
        }
    }


    /** List all packages available in repositories */
    public void listAll(){

        if(repositories == null){ loadRepositories(); }
        if(allProgramsCache == null){ generateProgramsCache(); }

        System.out.println("\n[ all packages available ]\n");
        Handy.shellFormatText(allProgramsCache);

    }

    /** Update everything by clearing caches. */
    public void update(){

        Globals.loadGlobals();
        repositories = null;
        installedCache = null;
        allProgramsCache = null;
        dag = null;

        System.out.println("\n [ all caches are cleared ]\n");
    }

    /**
     * Print dependencies and reverse dependencies
     * of package.
     *
     * @param name of package we want to investigate
     * @return true if package found, false otherwise
     */
    public boolean depends(String name){

        Program program = findProgram(name);

        if(program == null){
            System.out.println(" package '"+name+"' not found");
            return false;
        }else{

            if(dag == null){ generateDAG(); }

            Set<String> deps = dag.getAllDependencies(name);
            Set<String> revDeps = dag.getAllReverseDependencies(name);

            System.out.println(" depends - "+deps);
            System.out.println(" reverse - "+revDeps);

            return true;
        }
    }

    /**
     * @return Program if found in repositories, else null
     */
    private Program findProgram(String name){

        if(repositories == null){ loadRepositories(); }

        Program program = null;

        for(Repository repo : repositories){

            if(repo.contains(name)){
                program = repo.get(name);
                break;
            }
        }

        return program;

    }


    /**
     * Print description of package.
     *
     *
     * @param name of package we are looking for
     * @return true if package found, false otherwise
     */
    public boolean pkgInfo(String name){
        Program program = findProgram(name);

        if(program == null){
            System.out.printf(" %s  -  not found \n",name);
            return false;
        }else{

            System.out.println("\n[ package info ] \n");
            System.out.println(program.getDescription());
            System.out.println();

        }
        return true;
    }


    /**
     * Visualize dependency graph.
     */
    public void visual(){

        if(repositories == null){ loadRepositories(); }
        if(dag == null){ generateDAG(); }

        dag.visualize();
    }

    /**
     * Install a package.
     *
     * Tries to locate the package, if we find it,
     * we use the dependency graph to calculate dependencies
     * which have to be installed to keep system sound.
     *
     * @param name of package we want to install
     * @return true if package was installed, false otherwise
     */
    public boolean install(String name){

        Program program = findProgram(name);

        if(program == null){
            System.out.printf(" %s  -  not found \n",name);
            return false;

        }else{

            if(program.isInstalled()){
                System.out.printf(" %s  -  is already installed \n",name);
                return false;
            }

            if(dag == null){ generateDAG(); }

            Set<String> requirements = dag.requiredToInstall(name);

            if(requirements.size() > 0){

                System.out.println("\n[ missing dependencies ] \n");

                for(String dep : requirements){
                    System.out.println(" - "+dep);
                }

                boolean answer = 
                    Handy.yesNoDialog("\n install them? [y/N] ");

                if(answer){

                    if(! installRequired(requirements)){
                        return false;
                    }

                }else{
                    return false;
                }
            }

            if(program.install()){
                dag.markAsInstalled(name);
                System.out.printf(" %s  -  successfully installed \n",name);
            }else{
                System.out.println(" failed to install: "+name);
                return false;
            }

            installedCache = null;
        }
        return true;

    }


    /**
     * Remove a package.
     *
     * Tries to locate the package, if we find it,
     * dependency graph returns reverse dependencies
     * which must be removed as well to keep system
     * sound.
     *
     * @param name of package we want to remove
     * @return true if package was removed, false otherwise
     */
    public boolean remove(String name){

        Program program = findProgram(name);

        if(program == null){
            System.out.printf(" %s  -  not found \n",name);
            return false;
        }else{

            if(! program.isInstalled()){
                System.out.printf(" %s  -  is not installed \n",name);
                return false;
            }

            if(dag == null){ generateDAG(); }

            Set<String> requirements = dag.requiredToRemove(name);

            if(requirements.size() > 0){

                System.out.println("\n[ remove destroys other programs ] \n");

                for(String dep : requirements){
                    System.out.println(" - "+dep);
                }

                boolean answer = 
                    Handy.yesNoDialog("\n remove them? [y/N] ");

                if(answer){

                    if(! removeRequired(requirements)){
                        return false;
                    }

                }else{
                    return false;
                }

            }

            if(program.remove()){
                dag.markAsUninstalled(name);
                System.out.printf(" %s  - successfully removed \n",name);
            }else{
                System.out.println(" failed to remove: "+name);
            }

            installedCache = null;
        }

        return true;

    }

    /** 
     * Install required packages. 
     *
     * The dependency graph calculates the
     * set of packages which needs to be installed
     * in order to keep our system sound.
     *
     * @param set of packages which needs to be installed
     * @return true if everything works out, false otherwise
     * */
    private boolean installRequired(Set<String> set){

        Program program;

        for(String name : set){
            program = findProgram(name);
            if(program.install()){
                dag.markAsInstalled(name);
                System.out.println(" installed dependency: "+name);
            }else{
                System.out.println(" failed to install dependency: "+name);
                return false;
            }
        }

        return true;
    }


    /** 
     * Remove broken packages. 
     *
     * The dependency graph calculates the
     * set of packages which will be broken.
     *
     * @param set of broken packages
     * @return true if everything works out, false otherwise
     * */
    private boolean removeRequired(Set<String> set){

        Program program;

        for(String name : set){
            program = findProgram(name);
            if(program.remove()){
                dag.markAsUninstalled(name);
                System.out.println(" removed broken: "+name);
            }else{
                System.out.println(" failed to remove: "+name);
                return false;
            }
        }


        return true;
    }


    /** Generate the dependency graph. */
    private void generateDAG(){

        if(allProgramsCache == null){ generateProgramsCache(); }

        dag = new DoubleDAG();
        Program program;

        // add nodes
        for(String prg : allProgramsCache){
            program = findProgram(prg);
            dag.addNode(prg, program.isInstalled());
        }

        // add edges
        for(String name : allProgramsCache){
            program = findProgram(name);
            String[] deps = program.getDepends(); 
            for(String d : deps){
                dag.addEdge(name, d);
            }
        }

        if(dag.containsLoop()){
            System.err.println("\n [ ERROR: loop in dependency graph ] \n");
            System.exit(1);
        }

    }


    /** Generate cache of package names */
    private void generateProgramsCache(){

        allProgramsCache = new ArrayList<String>();

        for(Repository repo : repositories){
            allProgramsCache.addAll(repo.getAllPrograms());
        }
    }


    /** 
     * Search for a package in repositories.
     *
     * @param pattern we are looking for
     * */
    public void search(String pattern){

        if(repositories == null){ loadRepositories(); }

        List<String> matches;

        for(Repository repo : repositories){

            System.out.println("\n[ repository ] <"+repo.getURI()+">\n");

            matches = repo.search(pattern);

            if(matches.size() == 0){

                System.out.println("  no matches\n");

            }else{

                Handy.shellFormatText(matches);
            }

        }
    }


    /** 
     * Return list of repositories from
     * Globals.iinstallerRepoFile 
     * @return list of repository URI's
     */
    private List<String> getRepoURIs(){

        List<String> list = new ArrayList<String>();

        String cleanURIs = 
            Handy.getURIcontentWithoutComments(Globals.iinstallerRepoFile);

        String[] repos = cleanURIs.split("\n");

        for(String line : repos){
            // only add lines which have valid 'URI' beginnings
            if(line.startsWith("http://") || line.startsWith("file:///")){
                list.add(line);
            }
        }
        return list;
    }

    /** 
     * Create new {@link Repository} instances
     * for each repository listed in 
     * Globals.iinstallerRepoFile.
     * @see getRepoURIs
     * */
    private void loadRepositories(){

        List<String> URIs = getRepoURIs();

        repositories = new ArrayList<Repository>(URIs.size());

        for(String uri : URIs){

            Repository r = new Repository(uri);
            repositories.add(r);

        }
    }

    // try out some stuff
    public static void main(String[] args){

        Globals.loadGlobals();
        PackageManager pm = new PackageManager();
        pm.listInstalled();
        // search for packages
        for(String a : args){
            pm.search(a);
        }

        long t_0 = System.currentTimeMillis();
        pm.listAll();
        long t_1 = System.currentTimeMillis();
        pm.listAll();
        long t_2 = System.currentTimeMillis();
        System.out.println("first:"+(t_1 - t_0) + " ms second:"+(t_2 - t_1)+" ms");

    }
}
