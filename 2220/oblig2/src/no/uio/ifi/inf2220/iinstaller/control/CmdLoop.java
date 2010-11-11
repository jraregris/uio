package no.uio.ifi.inf2220.iinstaller.control;

import java.util.Scanner;
import java.util.NoSuchElementException;
import no.uio.ifi.inf2220.iinstaller.Globals;
import no.uio.ifi.inf2220.iinstaller.pkg.PackageManager;
import no.uio.ifi.inf2220.iinstaller.parse.GetOpt;
import no.uio.ifi.inf2220.iinstaller.utils.Handy;

/**
 * This class handles user requests by reading commands
 * from standard input in a loop.
 *
 * <p>
 * Is is more or less repetitive version of the {@link SinglePass} class
 * which only performs one command and then quits. {@link GetOpt} is used to
 * parse the user input here as well. 
 * </p>
 *
 * <p>
 * The functions inside this class are made protected instead of private
 * for the sake of documentation. Javadoc hides private functions and fields.
 * </p>
 *
 *
 * @author bjarneh@ifi.uio.no
 */


public class CmdLoop{

    private String prompt;
    private String menu;
    private PackageManager manager;
    private GetOpt parser;

    private final boolean CONTINUE = false;
    private final boolean ENDLOOP = true;

    /**
     * Empty constructor to use default prompt.
     */
    public CmdLoop(){
        this("> ");
    }

    /**
     * Initializes the class variables and
     * enter loop.
     *
     * @param _prompt gives the string which
     * precedes command line
     */
    public CmdLoop(String _prompt){

        prompt = _prompt;
        manager = new PackageManager();

        if(! Globals.simpleCMD){ clearScreen(); }

        initParser();
        createMenu();
        help();

        loop();        
    }


    private void initParser(){

        parser = new GetOpt();
        parser.stayAliveOnError();//ignore errors

        parser.addStringOption("s search");
        parser.addStringOption("i install");
        parser.addStringOption("r rm remove delete");
        parser.addStringOption("p pkginfo");
        parser.addStringOption("d depends");
        parser.addBoolOption("h help");
        parser.addBoolOption("g globals global");
        parser.addBoolOption("a all");
        parser.addBoolOption("u update");
        parser.addBoolOption("q quit");
        parser.addBoolOption("l list");
        parser.addBoolOption("v visual");
        if(! Globals.simpleCMD){
            parser.addBoolOption("c clear");
        }

    }

    private void createMenu(){

        StringBuilder menuBuffer = new StringBuilder();

        menuBuffer.append("\n iInstaller  \n\n");
        menuBuffer.append(" |  q / quit     |   exit iInstaller\n");
        menuBuffer.append(" |  h / help     |   print this menu\n");
        menuBuffer.append(" |  g / globals  |   print system info\n");
        menuBuffer.append(" |  l / list     |   list installed packages on system\n");
        menuBuffer.append(" |  a / all      |   list all packages in repositories\n");
        menuBuffer.append(" |  u / update   |   update repository info\n");
        menuBuffer.append(" |  s / search   |   search for package\n");
        menuBuffer.append(" |  p / pkginfo  |   show info about package\n");
        menuBuffer.append(" |  d / depends  |   print dependencies for package\n");
        menuBuffer.append(" |  i / install  |   install package with matching name\n");
        menuBuffer.append(" |  r / remove   |   remove package with matching name\n");
        menuBuffer.append(" |  v / visual   |   show visual representation of graph \n");

        if(! Globals.simpleCMD){
            menuBuffer.append(" |  c / clear    |   clear terminal\n");
        }

        this.menu = menuBuffer.toString();
    }


    private void help(){
        System.out.println(menu);
    }


    /**
     * This function prints an ANSI 'clear screen
     * sequence'. That will work with most terminals
     * running Linux/Unix/(Mac?).  Windows
     * also supports escape sequences, but it has
     * to be made aware that they will come, short
     * story: clearing the terminal is not present if
     * you run this program under Windows :-)
     */
    protected void clearScreen(){
        System.out.print("\033[H\033[2J");
    }


    /**
     * Reads user input from {@link System#in}.
     */
    protected void loop(){

        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        while(!quit){
            System.out.print(prompt);
            try{
                String input = sc.nextLine();
                quit = execute(input);
            }catch(NoSuchElementException nsx){
                // Ctrl-D should quit peacefully
                System.out.println();
                quit = true;
            }
        }
        sc.close();
    }


    /**
     * This function executes a command given by user.
     *
     * To execute a command, will be to call the appropriate
     * function inside the {@link PackageManager} which handles
     * most of the action besides printing the 'help' menu
     * and clearing the screen.
     *
     * @param cmd command given by user
     * @return true if user har typed 'quit' false otherwise
     */

    protected boolean execute(String cmd){

        String cmd_trim = cmd.trim();

        // ignore blank input line
        if(cmd_trim.equals("")){ return CONTINUE; }

        String[] input = cmd_trim.split("\\s+");
        parser.parse(input);

        // if we have remaining arguments something is not as it should
        // be and we write an error and return
        String[] remaining = parser.getArgs();
        if(remaining.length > 0){
            System.out.println("don't know what to do with: '"+
                    Handy.implode(remaining, "', '") +"'");
            parser.reset();
            return CONTINUE;
        }

        if(parser.isSet("help")){
            help();

        }else if(parser.isSet("quit")){
            return ENDLOOP;

        }else if(parser.isSet("globals")){
            System.out.println("\n\n[ system info ]");
            Globals.printInfo();

        }else if(parser.isSet("all")){
            manager.listAll();

        }else if(parser.isSet("update")){
            manager.update();

        }else if(parser.isSet("clear")){
            clearScreen();

        }else if(parser.isSet("list")){
            manager.listInstalled();

        }else if(parser.isSet("search")){
            manager.search(parser.getStringOption("search"));
        
        }else if(parser.isSet("depends")) {
            manager.depends(parser.getStringOption("depends"));

        }else if(parser.isSet("pkginfo")){
            manager.pkgInfo(parser.getStringOption("pkginfo"));

        }else if(parser.isSet("install")){
            manager.install(parser.getStringOption("install"));

        }else if(parser.isSet("remove")){
            manager.remove(parser.getStringOption("remove"));
        
        }else if(parser.isSet("visual")){
            manager.visual();

        }
        

        // we have to reset parser between loops
        // to clear options which may have been set 
        parser.reset();

        return CONTINUE;
    }

}
