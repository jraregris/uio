package no.uio.ifi.inf2220.iinstaller;

/**
 * This class holds system information.
 *
 * This information is made global since it is needed quite a
 * few places, in Java there really is no actual global
 * namespace, but by making all the variables inside a class
 * static and public we get more or less the same behaviour as
 * with global variables. 
 *
 * @author bjarneh@ifi.uio.no
 */

public class Globals{

    public static String OS;
    public static String fileSeparator;
    public static String pathSeparator;
    public static String userHome;
    public static String iinstallerDir;
    public static String iinstallerPkgDir;
    public static String iinstallerRepoFile;
    public static String userName;
    public static boolean simpleCMD;


    public static void printInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nOS                 |  "+OS+"\n");
        sb.append("fileSeparator      |  "+fileSeparator+"\n");
        sb.append("pathSeparator      |  "+pathSeparator +"\n");
        sb.append("userHome           |  "+userHome +"\n");
        sb.append("userName           |  "+userName +"\n");
        sb.append("iinstallerDir      |  "+iinstallerDir +"\n");
        sb.append("iinstallerPkgDir   |  "+iinstallerPkgDir +"\n");
        sb.append("iinstallerRepoFile |  "+iinstallerRepoFile +"\n");
        sb.append("simpleCMD          |  "+simpleCMD +"\n\n");
        System.out.println(sb.toString());
    }

    public static void loadGlobals(){

        Globals.OS                 = System.getProperty("os.name").toLowerCase();
        Globals.fileSeparator      = System.getProperty("file.separator");
        Globals.pathSeparator      = System.getProperty("path.separator");
        Globals.userHome           = System.getProperty("user.home");
        Globals.userName           = System.getProperty("user.name");
        Globals.iinstallerDir      = Globals.userHome+ Globals.fileSeparator+ ".iinstaller";
        Globals.iinstallerPkgDir   = Globals.iinstallerDir+Globals.fileSeparator+"ipkgs";
        Globals.iinstallerRepoFile = Globals.iinstallerDir+Globals.fileSeparator+"irepo.txt";

        if(Globals.OS.startsWith("win") || Globals.OS.startsWith("mac")){
            Globals.simpleCMD = true;
        }else{
            Globals.simpleCMD = false;
        }
    }

}
