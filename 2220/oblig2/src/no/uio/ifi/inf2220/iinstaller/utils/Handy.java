package no.uio.ifi.inf2220.iinstaller.utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URI;
import java.net.MalformedURLException;
import java.net.URISyntaxException;




/**
 * This class holds different utility functions.  Since they come in
 * handy several places I gathered them in one class to make them
 * available everywhere.
 *
 *
 * @author bjarneh@ifi.uio.no
 */

public class Handy{

    /**
     * The inverse function of: {@link String#split}. 
     * It assembles a String from an array and a
     * separator token.
     *
     * @param a is an array which will be imploded
     * @param separator is the token used as a separator
     * @return the imploded string
     */

    public static <T> String implode(T[] a, String separator){
        StringBuilder sb = new StringBuilder();

        if(a.length > 0){
            for(int i = 0; i < a.length - 1; i++){
                sb.append(a[i].toString());
                sb.append(separator);
            }
            sb.append(a[a.length - 1]);
        }
        return sb.toString();
    }


    /**
     * Get content from URI, URL, absolute path - without comments.
     *
     * @param uri location of the file
     * @return content of file without comments or blank lines
     * @see Handy#getContentFromURI
     */

    public static String getURIcontentWithoutComments(String uri){
        String content = Handy.getContentFromURI(uri);
        return Handy.commentAndBlankRemover(content);
    }

    /**
     * A comment is this context is anything following
     * a # sign. This function will remove these pieces
     * of text and also blank lines.
     *
     * @param s String which may contain comments and
     * blank lines
     * @return String without comments and blank lines
     */

    public static String commentAndBlankRemover(String s){

        String[] lines = s.split("\n");
        List<String> list = new ArrayList<String>(lines.length);

        for(String line : lines){
            line = line.replaceFirst("#.*", "");
            line = line.trim();
            if(! line.trim().equals("")){
                list.add(line);
            }
        }

        String[] cleanLines = new String[list.size()];
        list.toArray(cleanLines);

        return Handy.implode(cleanLines, "\n");
    }


    /**
     * Reads files based on URI, URL or absolute path.
     *
     * We are usually interested in the content of a file
     * and this function gives a somewhat unified way of
     * getting the content ({@link String}). This function
     * does not throw exceptions, but rather prints
     * the error to standard error ({@link System#err}),
     * and then the program exits.
     * Usually one would like to do the whole try-catch
     * game with exceptions, but if this application has
     * problems reading files, not matter which files,
     * we might as well kill it.
     *
     * @param identifier location of the file
     * @return content of file
     */

    public static String getContentFromURI(String identifier){

        URL url;
        URI uri;
        InputStream stream;
        int itoken;
        char ctoken;
        StringBuilder sb = new StringBuilder();
        String error = null;


        // read file over http
        if(identifier.startsWith("http://")){

            try{

                url = new URL(identifier);
                sb = new StringBuilder();
                stream = url.openStream();

                while( (itoken = stream.read()) != -1 ){
                    ctoken = (char) itoken;
                    sb.append(ctoken);
                }


            }catch(MalformedURLException mux){
                error = "malformed url: "+identifier;
            }catch(IOException iox){
                error = "could not read: "+identifier;
            }

        // read local file
        }else if(identifier.startsWith("file:///") || 
                (new File(identifier)).isFile() ){

            try{

                File file;
                if(identifier.startsWith("file:///")){
                    uri = new URI(identifier);
                    file = new File(uri);
                }else{
                    file = new File(identifier); 
                }

                sb = new StringBuilder();
                Scanner scanner = new Scanner(file);

                while(scanner.hasNext()){
                    sb.append(scanner.nextLine()+"\n");//scanner eats linefeed
                }
                scanner.close();


            }catch(URISyntaxException uix){
                error = "malformed uri: "+identifier;
            }catch(IOException iox){
                error = "could not read: "+identifier;
            }

        }

        // there really is no recovery if this application
        // fails to read files.....
        if(error != null){
            System.err.println(error);
            System.exit(1); // exit with error status
        }

        return sb.toString();

    }

    /**
     * Format a {@link List} of short {@link String}s to fit shell/terminal.
     *
     * We have little information about the actual width of
     * the shell/terminal, so we'll just assume standard width of 
     * 80 characters. It is also assumed that the words
     * are short, which they are in this case since they are
     * all 'fake package names' that we control the size of.
     *
     * @param words list of words to format to three columns
     */

    public static void shellFormatText(List<String> words){

        int max = words.size();

        // no formatting for short lists
        if(max <= 5){
            for(String s : words){
                System.out.println("  "+s);
            }
            System.out.println();
            return;
        }


        // now strip off 3 and 3 elements and print them 
        // out with a somewhat nice format...

        int overflow = max%3;

        for(int i = 0; i < ( max - overflow ) ; i += 3){
            System.out.printf(" %-19s   %-19s   %-19s \n", 
                    words.get(i), words.get(i+1), words.get(i+2));
        }

        switch(overflow){
            case 1:
                System.out.printf(" %-19s \n", words.get(max - 1)); 
                break;
            case 2:
                System.out.printf(" %-19s   %-19s\n", words.get(max - 2), words.get(max -1));
                break;
        }

        System.out.println();

    }

    /**
     * Writes a yes - no dialog and returns true of false
     * pending on the answer.
     *
     *
     * @param message the question that you want to ask
     * @return true or false pending on the answer from
     * the user
     */

    public static boolean yesNoDialog(String message){

        Scanner sc = new Scanner(System.in);
        System.out.print(message);

        try{

            String input = sc.nextLine().toLowerCase();

            if(input.equals("y") || input.equals("yes")){
                return true;
            }

        }catch(NoSuchElementException nsx){
            // Ctrl-D should quit peacefully
            System.out.println();
            return false;
        }

        return false;

    }




    public static void main(String[] args){

        System.out.println(Handy.implode(args, ";")+"\n");

        List<String> alist = new ArrayList<String>();

        for(String ss : args){
            alist.add(ss);
        }

        shellFormatText(alist);

    }
}

