package no.uio.ifi.inf2220.iinstaller.pkg;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import no.uio.ifi.inf2220.iinstaller.utils.Handy;
import no.uio.ifi.inf2220.iinstaller.Globals;

/**
 * This class holds a single <strong> program/package </strong> from the
 * repository. 
 *
 * <p>
 * This class handles <strong> install </strong> and <strong>
 * remove </strong>, and holds information about dependencies,
 * which is used to build the dependency graph.  This class got
 * the name Program, to avoid confusion with the class {@link
 * java.lang.Package}.
 * </p>
 *
 * @author bjarneh@ifi.uio.no
 */

public class Program{

    String uri;         // uri to .pkg file
    String name;        // package name (packagename - .pkg)
    String pkgContent;  // the file content of this package
    String description; 

    String[] depends;  // list of dependencies 


    public Program(String _uri, String _name){
        uri = _uri;
        name = _name;
    }
    

    public String getURI(){
        return uri;
    }


    public String getName(){
        return name;
    }


    /** @return an array of dependencies (package-names) */
    public String[] getDepends(){

        if(depends == null){ generateDepends(); }

        return depends;
    }


    /** @return description of package */
    public String getDescription(){

        if(description == null){ generateDescription(); }

        return description;
    }


    private void generatePkgContent(){
        pkgContent = Handy.getContentFromURI(uri);
    }


    private void generateDescription(){

        if(pkgContent == null){ generatePkgContent(); }
        
        String[] lines = pkgContent.split("\n");

        // if no description is found in pkg file
        description = " no information for: "+name;
        
        for(String line: lines){

            if(line.startsWith("description:")){
            
                if(! line.substring(12).trim().equals("")){
                    description = line.substring(12);
                }
            }
        }
    }


    private void generateDepends(){

        if(pkgContent == null){ generatePkgContent(); }
        
        String[] lines = pkgContent.split("\n");
        // set dependencies to none
        depends = new String[0];
        
        for(String line: lines){
            if(line.startsWith("depends:")){

                String dependslist = line.substring(8).trim();

                if(! dependslist.equals("")){
                    depends = dependslist.split("\\s+");
                }
            }
        }
    }


    /**
     * installs this Program, if an error occurs
     * return value is false.
     *
     *
     * @return true if install went ok, false otherwise
     */
    public boolean install(){
        
        if(pkgContent == null){ generatePkgContent(); }

        String filename = Globals.iinstallerPkgDir+ Globals.fileSeparator+name+".pkg";
        try{
            PrintWriter printwriter = new PrintWriter(filename);
            printwriter.print(pkgContent);
            printwriter.close();
            return true;
        }catch(IOException iox){
            System.err.println("problems installing: "+name);
        }
        return false;
    }

    /**
     * removes this Program, if an error occurs
     * return value is false.
     *
     *
     * @return true if remove went ok, false otherwise
     */
    public boolean remove(){
        
        String filename = Globals.iinstallerPkgDir+ Globals.fileSeparator+name+".pkg";
        File file = new File(filename);

        if(! file.delete()){
            System.err.println(" could not delete program: "+name);
            return false;
        }
        return true;
    }


    /**
     * @return true if Program is installed, false otherwise
     */
    public boolean isInstalled(){
       String filename = Globals.iinstallerPkgDir+ Globals.fileSeparator+name+".pkg";
       File file = new File(filename);
       return file.exists();
    }


    public String toString(){
        return " Program("+name+".pkg)\n";
    }

}

