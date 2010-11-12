package no.uio.ifi.inf2220.iinstaller.graph;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


/**
 * Graph class.
 *
 * <p>
 * This is a Directed Acyclic Graph (DAG), if the graph
 * contains a cycle the repositories are corrupt, and
 * we should exit.  It is called DoubleDAG since it
 * holds two DAG's, i.e., we have links to dependencies
 * and reverse dependencies. Dependencies tells us
 * which packages/programs we must install before we
 * can install a certain package, and reverse
 * dependencies tells us which programs become corrupt
 * once a program is removed.
 * </p>
 * <strong> Example: </strong>
 *
 * <pre>
 * package1: depends: package2
 * </pre>
 *
 * <p>
 * package2 now reverse depends on package1,
 * since removing package2 will destroy package1.
 * </p>
 *
 * <p>
 * TODO: 
 * implement the functionality needed in order
 * for this class to to what it should.
 * </p>
 *
 */

public class DoubleDAG {


    Map<String, DNode> nodes;


    public DoubleDAG(){
        nodes = new HashMap<String, DNode>();
    }

    public void markAsInstalled(String name){
    	nodes.get(name).installed = true;
        System.out.println("Marked as installed: "+name);
    }

    public void markAsUninstalled(String name){
        nodes.get(name).installed = false;
        System.out.println("Marked as uninstalled: "+name);
    }

    public void addNode(String name, boolean installed){
        nodes.put(name, new DNode(name, installed));
        System.out.println("Adding node: "+name);
    }

    public void addEdge(String parent, String child){
        nodes.get(parent).addChild(nodes.get(child));
        nodes.get(child).addParent(nodes.get(parent));
        System.out.println("Adding an edge between "+parent+" and "+child);
    }

    public void visualize(){
    	try {
			Vizualizer v = new Vizualizer();
			for(DNode n : nodes.values()){
				for(DNode c: n.children){
					v.addEdge(n.name,c.name);
				}
			}
			v.visualize();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(666);
		}
        System.out.println("Visualizing dependency graph... Stand by, YMMV.");
    }

    public boolean containsLoop(){
        // TODO
        System.out.println("[TODO] -----------------------------------------------------");
        System.out.println("[TODO] create a topological ordering of the nodes, if this ");
        System.out.println("[TODO] fails we have a loop in the graph. the topological ");
        System.out.println("[TODO] ordering will determine each nodes topsortPlacement ");
        System.out.println("[TODO] which can be used later to determine in which order ");
        System.out.println("[TODO] the programs should be installed or removed. ");
        System.out.println("[TODO] adding the DNodes to a SortedSet will automatically ");
        System.out.println("[TODO] sort them based on topsortPlacement, but the ordering");
        System.out.println("[TODO] must be reversed if we calculate reverse dependencies");
        System.out.println("[TODO] -----------------------------------------------------");
    
        return false;
    }

    public Set<String> requiredToInstall(String name){
        // TODO
        System.out.println("[TODO] calculate (missing) dependencies for "+name);
        return new HashSet<String>();
    }

    public Set<String> requiredToRemove(String name){
        // TODO
        System.out.println("[TODO] calculate (broken) reverse dependencies for "+name);
        return new HashSet<String>();
    }


    public Set<String> getAllDependencies(String name){
        // TODO
        System.out.println("[TODO] calculate ALL dependencies for "+name);
        return new HashSet<String>();
    }

    public Set<String> getAllReverseDependencies(String name){
        // TODO
        System.out.println("[TODO] calculate ALL reverse dependencies for "+name);
        return new HashSet<String>();
    }

}

