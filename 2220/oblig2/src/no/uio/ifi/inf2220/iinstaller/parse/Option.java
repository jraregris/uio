package no.uio.ifi.inf2220.iinstaller.parse;

import java.util.List;
import java.util.Arrays;

/**
 * <p>
 * This is a base class for both types of options
 * {@link BoolOption} and {@link StringOption}.
 * </p>
 * <p>
 * The things they have in common should be placed
 * in a super-class, but since we do not want any
 * instantiations of this class it is abstract.
 * </p>
 * <p>
 * The nice thing about the abstract classes
 * (instead of using an interface), is 
 * that we get the possibility to make default
 * implementations of functions. 
 * </p>
 *
 *
 * @author bjarneh@ifi.uio.no
 */

public abstract class Option{

    List<String> opts;

    public Option(String _opts){
        opts = Arrays.asList(_opts.trim().split("\\s+"));
    }

    /**
     * Tell whether or not an option (String)
     * belongs inside this instance.
     * This function is needed inside both options
     * {@link StringOption} and {@link BoolOption}
     * so we put it in an abstract class which they
     * both extend
     *
     * @param op the string option we are looking for
     * @return true if option-argument is 
     * contained inside this Option
     */
    public boolean contains(String op){
        return opts.contains(op);
    }

    /**
     * Test whether or not options was 
     * set during argument parsing
     *
     * @return true if this option was set
     */
    public abstract boolean isSet();

    /**
     * Resets the option i.e., options become
     * 'unset' again.
     */
    public abstract void reset();

    /**
     * Return String representation of option
     * @return String representation of this option
     */
    public String toString(){
        return opts.toString();
    }
}
