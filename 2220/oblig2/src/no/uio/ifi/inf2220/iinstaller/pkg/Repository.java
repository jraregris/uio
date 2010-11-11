package no.uio.ifi.inf2220.iinstaller.pkg;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import no.uio.ifi.inf2220.iinstaller.utils.Handy;

/**
 * This class represents a repository, (a collection of {@link Program}s).
 * 
 * <p>
 * This class holds a Map of {@link Program}s, and they are stored in a
 * {@link TreeMap} for ordering, i.e., this will return the programs
 * alphabetically sorted. Other types of {@link Map}s could just as
 * easily have been used.
 * </p>
 *
 * <p>
 * This class reads the content of the repository and instantiates the
 * {@link Program}s. This class allows us to <strong> search </strong> and
 * fetch programs ( <strong> get </strong>) from the repository.
 * </p>
 * 
 *
 *
 * @author bjarneh@ifi.uio.no
*/

public class Repository{

    private String uri;

    private TreeMap<String, Program> programs;

    public Repository(String _uri){
        uri = _uri;
    }

    public String getURI(){
        return uri;
    }

    /**
     * Preform a search in this repository.
     * Matches are located using {@link String#contains}.
     *
     * @param pattern we are looking for
     * @return list of matching {@link Program} names ({@link String}s)
     */

    public List<String> search(String pattern){

        if(programs == null){ loadPrograms(); }

        List<String> matches = new ArrayList<String>(programs.size());
        Set<String> keys = programs.keySet();

        for(String program : keys){
            if(program.contains(pattern)){
                matches.add(program);
            }
        }

        return matches;
    }

    /**
     * tell if a {@link Program} in this Repository
     * has a matching name.
     * 
     * @param name of {@link Program} we are looking for
     * @return true if found, false otherwise
     */

    public boolean contains(String name){
        
        if(programs == null){ loadPrograms(); }

        Set<String> keys = programs.keySet();
        return keys.contains(name);
    }

    /**
     * fetch {@link Program} from this Repository.
     *
     *
     * @param name of {@link Program} we are looking for
     * @return {@link Program} with matching name
     */

    public Program get(String name){

        if(programs == null){ loadPrograms(); }

        if(contains(name)){
            return programs.get(name); 
        }
        return null;
    }

    /**
     * return {@link Set} containing names ({@link String}) of all
     * {@link Program}s in this Repository.
     *
     *
     * @return {@link Set} of all {@link Program} names
     */

    public Set<String> getAllPrograms(){

        if(programs == null){ loadPrograms(); }
        
        return programs.keySet();
    }

    private void loadPrograms(){

        programs = new TreeMap<String, Program>();

        Pattern namepattern = Pattern.compile("^.*/iinstaller/.*?([^\\/]+)\\.pkg\\s*$");

        String cleanPrograms =  Handy.getURIcontentWithoutComments(uri);
        String[] packageURIs = cleanPrograms.split("\n");

        for(String packageURI : packageURIs){

            Matcher m = namepattern.matcher(packageURI);
            
            if(m.matches()){

                Program p = new Program(packageURI, m.group(1));
                programs.put(m.group(1), p);

            }else if(! packageURI.trim().equals("")){
                System.err.println("malformed URI: "+packageURI);
            }
        }
    }

    public String toString(){
        return  "Repository: "+uri+"\n"+ programs.toString();
    }

}

