package no.uio.ifi.inf2220.iinstaller.parse;

/**
 * This class represents the boolean options,
 * these options are just switches that can be
 * turned on.
 *
 * 
 * <pre>
 * Typical boolean options:
 *
 *  --version
 *  --help
 *  --gui
 *  --text-view
 *  --log-errors
 * </pre>
 *
 *
 * @author bjarneh@ifi.uio.no
 */

public class BoolOption extends Option{

    private boolean set = false;

    public BoolOption(String _opts){
        super(_opts);
    }

    /** {@inheritDoc} */
    public boolean isSet(){
        return this.set;
    }

    /**
     * This function is called to notify
     * that this boolean options has 
     * been set.
     */
    public void setOption(){
        this.set = true;
    }

    /** {@inheritDoc} */
    public void reset(){
        this.set = false;
    }

    /** {@inheritDoc} */
    public String toString(){
        return super.toString() + " " +this.set;
    }

}

