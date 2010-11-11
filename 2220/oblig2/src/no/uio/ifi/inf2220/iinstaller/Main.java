package no.uio.ifi.inf2220.iinstaller;

import no.uio.ifi.inf2220.iinstaller.control.CmdLoop;
import no.uio.ifi.inf2220.iinstaller.control.SinglePass;
import no.uio.ifi.inf2220.iinstaller.parse.GetOpt;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * <p>
 * This class starts the iinstaller program.
 * It performes some simple startup checks to see if all the
 * files needed are in place, and uses the class {@link GetOpt}
 * to parse input arguments. It also fills the {@link Globals}
 * which are needed quite often.
 * </p>
 *
 * <p>
 * If no arguments are given we jump into a loop with several
 * choices, these actions can also be stated at the command
 * line with the options given inside the brackets: 
 * </p>
 * 
 * <pre>
 * 1.  install   [ -install ]
 * 2.  remove    [ -remove  ]
 * 3.  search    [ -search  ]
 * 4.  pkginfo   [ -pkginfo ]
 * 5.  list      [  -list   ]
 * 6.  all       [  -all    ]
 * 7.  depends   [ -depends ] 
 * 8.  globals   [ -globals ]
 * 9.  help      [  -help   ]
 * 10. update 
 * 11. quit  
 * </pre>
 * 
 * Example of command line use:
 *
 * <pre>
 * iinstaller = java -jar path/to/inf2220.jar
 *
 * iinstaller -install somePackage
 * iinstaller -list 
 * iinstaller -search something
 * iinstaller -remove someUnwantedPackage
 * iinstaller -globals
 *
 * </pre>
 *
 * <p>
 * If no arguments are given control is handed over to
 * {@link CmdLoop} otherwise control is handed over to 
 * {@link SinglePass}.
 * </p>
 *
 *  @author bjarneh@ifi.uio.no 
 */


public class Main{

    public Main(){
        ;
    }

    /**
     * @param argv input arguments
     */
    private void init(String[] argv){

        if(! startUpCheck()){
            System.err.println("could not start up iinstaller");
            System.exit(1); // exit bad if startUpCheck fails
        }

        //  no arguments -> command loop
        if(argv.length == 0){
            new CmdLoop(Globals.userName+"> ");
        }else{
        //  arguments -> single-pass
            new SinglePass().parseArgv(argv);
        }
    }

    /**
     * make sure everything is in place, if not create it.
     * @return true if everything works out, false otherwise
     */
    private boolean startUpCheck(){

        File idir = new File(Globals.iinstallerDir);
        File ipkgdir = new File(Globals.iinstallerPkgDir);
        File irepo = new File(Globals.iinstallerRepoFile);

        if(! idir.isDirectory()){ idir.mkdir(); }
        if(! ipkgdir.isDirectory()){ ipkgdir.mkdir(); }
        if(! irepo.isFile()){ writeInitReposFile(Globals.iinstallerRepoFile); }

        if(! (idir.canRead() & ipkgdir.canWrite())){
            System.err.println("can not read or write iinstaller files");
            return false;
        }
        return true;
    }

    /**
     * Write default repo file to users iinstaller directory.
     * @param filename is the location of user-repository-file
     */
    private void writeInitReposFile(String filename){
        try{
            PrintWriter printwriter = new PrintWriter(filename);
            printwriter.println("# iInstaller version 1.0\n");
            printwriter.println("http://bjarneh.at.ifi.uio.no/iinstaller/repo.txt");
            printwriter.println("#http://bjarneh.at.ifi.uio.no/iinstaller/badrepo.txt");
            printwriter.close();
        }catch(IOException iox){
            iox.printStackTrace(System.err);
        }
    }


    /**
     * This is where it all starts.
     * We load the global variables first, then we start
     * off either {@link SinglePass} or {@link CmdLoop} pending on
     * whether the user has provided input arguments.
     *
     * @param args input arguments
     */
    public static void main(String[] args){

        // load the Global variables first
        Globals.loadGlobals();
        new Main().init(args);

    }
}

