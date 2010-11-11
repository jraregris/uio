package no.uio.ifi.inf2220.iinstaller.parse;

/**
 * This class represents options that take trailing
 * arguments.
 * <p>
 * This implementation actually only supports a single
 * argument for each String option. This is quite common
 * and is usually solved by concatenating arguments
 * if more then one is required.
 * </p>
 *
 * <pre>
 *  Typical string options:
 *
 *  --file  filename.txt
 *  --geometry  101x20
 *  --install  program
 *  --remove   program
 *  --search   pattern
 *  --output   stdout
 * </pre>
 *
 *
 * @author bjarneh@ifi.uio.no
 */

public class StringOption extends Option{

    String arg = null;

    public StringOption(String _opts){
        super(_opts);
    }

    /** {@inheritDoc} */
    public boolean isSet(){
        return (arg != null);
    }

    /**
     * This function is called to indicate
     * that this option was set to _arg.
     *
     * @param _arg the argument we wish to set
     * this StringOption to
     */
    public void setOption(String _arg){
        this.arg = _arg;
    }

    /** {@inheritDoc} */
    public void reset(){
        this.arg = null;
    }

    /** {@inheritDoc} */
    public String toString(){
        return super.toString() + " " + arg;
    }

    /**
     * Returns the value this option has been set to.
     *
     * @return the value of this StringOption
     */
    public String getValue(){
        return arg;
    }
}










