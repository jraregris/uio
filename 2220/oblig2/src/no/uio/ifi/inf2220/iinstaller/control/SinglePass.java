package no.uio.ifi.inf2220.iinstaller.control;

import no.uio.ifi.inf2220.iinstaller.parse.GetOpt;
import no.uio.ifi.inf2220.iinstaller.Globals;
import no.uio.ifi.inf2220.iinstaller.pkg.PackageManager;
import no.uio.ifi.inf2220.iinstaller.utils.Handy;


/**
 * This class takes control if a single operation
 * is wanted (i.e., a single: install, remove, list..).
 *
 * This class does more or less the same as a single loop
 * iteration from {@link CmdLoop}, but it would have been
 * very ugly to make a special case for this purpose so it
 * was put into a class of its own.
 * 
 *
 * @author bjarneh@ifi.uio.no
 */

public class SinglePass{

    PackageManager manager;
    String version = "iinstaller version 1.0";

    public SinglePass(){
        manager = new PackageManager();
    }

    protected void printHelp(){

        StringBuilder sb = new StringBuilder();
        sb.append("\n iinstaller - package manager  \n\n");
        sb.append(" -h --help       print this menu and exit\n");
        sb.append(" -v --version    print version number and exit\n");
        sb.append(" -l --list       list installed packages and exit\n");
        sb.append(" -p --pkginfo    list information about package and exit\n");
        sb.append(" -a --all        list all available packages in repositories\n");
        sb.append(" -d --depends    list dependencies for package\n");
        sb.append(" -g --globals    print system information\n");
        sb.append(" -i --install    install package\n");
        sb.append(" -r --remove     remove package\n");
        sb.append(" -s --search     search for package\n");
        sb.append(" -v --visual     show visual representation of graph \n");
        System.out.println(sb.toString());
        System.exit(0);
    }

    /**
     * Parses command line arguments and calls
     * appropriate function in {@link PackageManager}. 
     *
     * @param args command line arguments
     */

    public void parseArgv(String[] args){

        int exitValue = 0;

        GetOpt getopt = new GetOpt();

        getopt.addBoolOption("-h -help --help");
        getopt.addBoolOption("-v -version --version");
        getopt.addBoolOption("-l -list --list list");
        getopt.addBoolOption("-g -globals --globals globals");
        getopt.addBoolOption("-a -all --all all");
        getopt.addBoolOption("-v -visual --visual visual");
        getopt.addStringOption("-i -install --install install");
        getopt.addStringOption("-d -depends --depends depends");
        getopt.addStringOption("-r -remove --remove remove");
        getopt.addStringOption("-s -search --search search");
        getopt.addStringOption("-p -pkginfo --pkginfo pkginfo");

        getopt.parse(args);

        String[] remaining = getopt.getArgs();

        if(remaining.length > 0){
            System.out.println("don't know what to do with: '"+
                                Handy.implode(remaining, "', '") +"'");
        }

        if(getopt.isSet("-help")){

            printHelp();
        
        }else if(getopt.isSet("-globals")){

            Globals.printInfo();
        
        }else if(getopt.isSet("-version")){

            System.out.println(this.version);
        
        }else if(getopt.isSet("-all")){

            manager.listAll();
        
        }else if(getopt.isSet("-list")){

            manager.listInstalled();
        
        }else if(getopt.isSet("-install")){
            
            exitValue = manager.install(getopt.getStringOption("-install"))  ? 0 : 1;

            /*--------------------------------------

               just a compact way of writing this
             
             if(manager.install(getopt.getStringOption("-install"))){
                 exitValue = 0;
             }else{
                 exitValue = 1;
             }

             ---------------------------------------*/
           
        }else if(getopt.isSet("-pkginfo")){
        
            exitValue = manager.pkgInfo(getopt.getStringOption("-pkginfo")) ? 0 : 1;
        
        }else if(getopt.isSet("-search")){

            manager.search(getopt.getStringOption("-search"));
        
        }else if(getopt.isSet("-remove")){

            exitValue = manager.remove(getopt.getStringOption("-remove")) ? 0 : 1;
        
        }else if(getopt.isSet("-depends")){

            exitValue = manager.depends(getopt.getStringOption("-depends")) ? 0 : 1;
        
        }else if(getopt.isSet("-visual")){
            manager.visual();
        }

        System.exit(exitValue);
    }
}
