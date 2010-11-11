package no.uio.ifi.inf2220.iinstaller.graph;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
// only needed for javadoc link to work
import no.uio.ifi.inf2220.iinstaller.pkg.Program;

/**
 * The nodes inside the dependency graph are represented by this
 * class. They could have been represented by the {@link Program}
 * class as well, but with this class we are able to separate the
 * entire dependency graph problem from the rest of the application.
 *
 *
 * TODO: implement needed functionality in this class
 *
 * @author bjarneh@ifi.uio.no
 */

public class DNode implements Comparable<DNode> {

    String name; // is unique
    boolean installed;

    // used when looking for loops
    int topsortPlacement;
    int indegree;


    List<DNode> parents; // nodes depending on you
    List<DNode> children; // nodes you depend on

    DNode(String _name){
        this(_name, false);
    }

    DNode(String _name, boolean _installed){
        name = _name;
        parents = new ArrayList<DNode>();
        children = new ArrayList<DNode>();
        installed = _installed;
        indegree = -1;
        topsortPlacement = -1;
    }   

    /**
     * @return name of this node/package
     */
    public String getName(){
        return name;
    }

    /**
     * @param parent is a node that depends on me
     */
    public void addParent(DNode parent){
        parents.add(parent);
    }

    /**
     * @param child is a node that I depend on
     */
    public void addChild(DNode child){
        children.add(child);
    }

    /**
     * @param other being compared to me
     * @return 0 if we are equal, 1 if I am bigger, -1 otherwise
     */
    public int compareTo(DNode other){

        // we point to the same object
        if( other == this ){ return 0; }

        if( this.topsortPlacement == other.topsortPlacement ){
            return 0;
        }else if( this.topsortPlacement > other.topsortPlacement ){
            return 1;
        }else{ // this.topsortPlacement < other.topsortPlacement
            return -1;
        }

    }

    /**
     * @param other being compared to me
     * @return true if we are equal, false otherwise
     */
    public boolean equals(Object other){
        // we point to the same object
        if( other == this ){ return true; } 
        
        // DNode name is unique
        if( other instanceof DNode ){
            DNode odn = (DNode) other;
            return odn.getName().equals(name);
        }else{ 
            return false; 
        }
    }

    public int hashCode(){ return name.hashCode(); }

    /*------------------------------------
     *
     * Remember to document your code
     *
     * -----------------------------------*/

}

