package no.uio.ifi.inf2220.iinstaller.parse;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;



/**
 * <p>
 * This class helps with option parsing, when command line
 * arguments are given they are classified into two categories
 * {@link BoolOption} and {@link StringOption}. 
 * StringOptions takes arguments, and BoolOptions are just switches which
 * can be turned on, and they are by default turned off.  Examples of
 * BoolOptions could be '--version' or '--help' and examples of
 * StringOptions could be '--file' or '-bitrate' since the last two
 * require additional arguments following them.  If StringOptions lacks a
 * trailing argument, no exception is thrown, instead the program halts
 * with an error message, if the boolean ignoreErrors is not set.  (This
 * is what you would do in 99.99 percent of the time anyway when such an
 * event occurs). 
 * </p>
 * <p>
 * This implementation does not follow the syntax from the
 * standard C routine <strong>getopt</strong> with short and long
 * options, but instead they are all given as a {@link String}
 * which is separated by whitespace.
 * </p>
 * <strong>Example:</strong>
 * <pre>
 * GetOpt getopt = new GetOpt();
 *
 * getopt.addStringOption("-b -bitrate --bitrate"); 
 * getopt.addBoolOption("-h -help --help");
 *
 * </pre>
 *
 * <strong>
 * Typical use:
 * </strong>
 * 
 * <pre>
 *
 *
 * Map&lt;String, String&gt; defaults;
 *
 * // fill defaults with values which can be 
 * // overwritten by arguments from user
 *
 * GetOpt getopt = new GetOpt();
 *
 * getopt.addBoolOption("-h -help --help");
 * getopt.addBoolOption("-h -help --help");
 * getopt.addStringOption("-b -bitrate --bitrate");
 * getopt.addStringOption("-g -geometry --geometry");
 *
 * getopt.parse(argv); //  String[] argv - input arguments
 *
 * if(getopt.isSet("-help")){
 *  System.out.println("I will help you");
 * }
 *
 * if(getopt.isSet("-version")){
 *  System.out.println("version 2.0");
 * }
 * 
 * if(getopt.isSet("-bitrate"){
 *   defaults.put("bitrate", getopt.getStringOption("-bitrate"));
 * }
 *
 * if(getopt.isSet("-geometry"){
 *   defaults.put("geometry", getopt.getStringOption("-g"));
 * }
 *
 * </pre>
 *
 * @author bjarneh@ifi.uio.no
 */

public class GetOpt{


    List<BoolOption> boolOptions;
    List<StringOption> stringOptions;
    List<String> restArgs;
    Option cache = null;
    boolean ignoreErrors = false;


    public GetOpt(){
        boolOptions = new ArrayList<BoolOption>();
        stringOptions = new ArrayList<StringOption>();
        restArgs = new ArrayList<String>();
    }

    /**
     * Makes it possible to ignore errors, default
     * behaviour is to quit on errors with {@link System#exit}.
     */
    public void stayAliveOnError(){
        this.ignoreErrors = true;
    }

    /**
     * Add a {@link BoolOption} with this function.
     * @param opts white space separated list of
     * options which have the same meaning
     */
    public void addBoolOption(String opts){
        BoolOption bo = new BoolOption(opts);
        boolOptions.add(bo);
    }

    /**
     * Add a {@link StringOption} with this function.
     * @param opts white space separated list of
     * options which have the same meaning
     */
    public void addStringOption(String opts){
        StringOption so = new StringOption(opts);
        stringOptions.add(so);
    }

    /**
     * This function parses the input arguments
     * based on the options added.
     * Note: this does not follow getopt standard
     * a series of short options cannot be given
     * as one string, and so on..
     *
     * @param args arguments to be parsed
     */
    public void parse(String[] args){

        /*  
         * TODO: (extra) preprocess args
         *
         * Note that one can transform series of short
         * options like -xvzf into -x -v -z -f
         * before they are ever parsed, this will add
         * the functionality which is present in most
         * implementations of 'getopt'
         *  */

        boolean fatalError = false;

        Stack<String> argStack = reverseArrayToStack(args);

        String next;
        Option option;
        StringOption stringoption;
        BoolOption booloption;

        while(! argStack.empty()){

            next = argStack.pop(); 
            option = isOption(next);

            if(option != null){

                if(option instanceof BoolOption){

                    booloption = (BoolOption) option;
                    booloption.setOption();

                }else if(option instanceof StringOption){

                    if(argStack.empty()){
                        System.err.println("missing argument for: "+next);
                        fatalError = true;
                        break;
                    }else{
                        stringoption = (StringOption) option;
                        stringoption.setOption(argStack.pop());
                    }
                }

            }else{// isOption returned null

                restArgs.add(next);
            }
        }

        if((! ignoreErrors) && fatalError){ System.exit(1); }

    }


    /**
     * Tells if an option is set.
     *
     * @param opt option we are looking for
     */
    public boolean isSet(String opt){
        Option o = isOption(opt);
        if(o != null){
            cache = o;
            return o.isSet();
        }
        return false;
    }


    /**
     * Returns the value of a {@link StringOption}
     * which have been set.
     *
     * @param opt option we want the value of
     */
    public String getStringOption(String opt){
        
        StringOption so;

        if(cache != null && cache.contains(opt) && cache.isSet()){
            so = (StringOption) cache;
            return so.getValue(); 
        }else{
            so = isStringOption(opt);
            if(so != null && so.isSet()) return so.getValue();
        }
        return null;
    }


    private Option isOption(String o){
        Option opt = isBoolOption(o);
        return ( opt == null ? isStringOption(o) :  opt );
    }

    private BoolOption isBoolOption(String o){
        for(BoolOption  bo : boolOptions){
            if(bo.contains(o)) return bo;
        }
        return null;
    }

    private StringOption isStringOption(String o){
        for(StringOption so : stringOptions){
            if(so.contains(o)) return so;
        }
        return null;
    }


    /**
     * Reset all options to their default value.
     *
     * Their default value is false for BoolOptions
     * and null for StringOptions
     */
    public void reset(){

        for(BoolOption  bo : boolOptions){
            bo.reset();
        }
        for(StringOption so : stringOptions){
            so.reset();
        }
        restArgs = new ArrayList<String>();

    }


    /**
     * Return remaining arguments after parsing.
     *
     * @return arguments after parsing
     */
    public String[] getArgs(){
        String[] s = new String[restArgs.size()];
        return restArgs.toArray(s);
    }

    /**
     * Adds elements of an array onto a {@link Stack} 
     * in reversed order. This will make
     * them pop off in the right order, and it is made
     * generic, although it could just as well have been
     * made from String[] to Stack&lt;String&gt;
     *
     * @param a an array of elements
     * @return a stack of all the elements in the array
     * in reversed order
     */
    public <T> Stack<T> reverseArrayToStack(T[] a){
    
        Stack<T> stack = new Stack<T>();

        for(int i = a.length - 1; i >= 0; i--){
            stack.push(a[i]);
        }

        return stack;
    }



    /**
     * Return String representation of GetOpt.
     * This will print the value of all
     * options, and the remaining arguments.
     *
     * @return String representation of GetOpt
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(BoolOption  bo : boolOptions){
            sb.append(bo.toString() + "\n");
        }
        for(StringOption so : stringOptions){
            sb.append(so.toString() + "\n");
        }

        sb.append("[args] ");
        for(String r : restArgs){
            sb.append("'"+r+"' ");
        }

        return sb.toString();
    }


    public static void main(String[] args){
        GetOpt getopt = new GetOpt();
        getopt.addBoolOption("-h -help --help");
        getopt.addBoolOption("-v --version -version");
        getopt.addStringOption("-f --file -file");
        getopt.addStringOption("-D --dirs");
        getopt.addStringOption("search s");

        getopt.parse(args);

        System.out.println(getopt);

        if(getopt.isSet("-help")){
            System.out.println(" help is on the way ");
        }
        if(getopt.isSet("-version")){
            System.out.println(" version 0.1 ");
        }
        if(getopt.isSet("-file")){
            System.out.println("-file: "+getopt.getStringOption("-file"));
        }

        // and so on...


    }
}
