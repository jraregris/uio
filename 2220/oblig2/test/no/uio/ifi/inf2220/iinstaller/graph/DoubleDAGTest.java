package no.uio.ifi.inf2220.iinstaller.graph;

import static org.junit.Assert.*;

import org.junit.Test;
import no.uio.ifi.inf2220.iinstaller.graph.*;

public class DoubleDAGTest {

	@Test
	public void testMarkAsInstalled() {
		DoubleDAG dag = new DoubleDAG();
		dag.addNode("node", false);
		assertFalse(dag.nodes.get("node").installed);
		dag.markAsInstalled("node");
		assertTrue(dag.nodes.get("node").installed);
	}

	@Test
	public void testMarkAsUnInstalled() {
		DoubleDAG dag = new DoubleDAG();
		dag.addNode("node", true);
		assertTrue(dag.nodes.get("node").installed);
		dag.markAsUninstalled("node");
		assertFalse(dag.nodes.get("node").installed);
	}
	
	@Test
	public void testAddNode(){
		DoubleDAG dag = new DoubleDAG();
		assertNull(dag.nodes.get("node"));
		dag.addNode("node", false);
		assertNotNull(dag.nodes.get("node"));
	}
	
	@Test
	public void testAddEdge(){
		DoubleDAG dag = new DoubleDAG();
		dag.addNode("parent", false);
		dag.addNode("child", false);

		DNode parent = dag.nodes.get("parent");
		DNode child = dag.nodes.get("child");
		
		assertFalse(parent.children.contains(child));
		assertFalse(child.parents.contains(parent));

		dag.addEdge("parent", "child");

		assertTrue(parent.children.contains(child));
		assertTrue(child.parents.contains(parent));
	}
	
	@Test
	public void testContainsLoop(){
		DoubleDAG dag = new DoubleDAG();
		dag.addNode("ali", false);
		dag.addNode("bob", false);
		dag.addNode("cecil", false);
		
		assertFalse(dag.containsLoop());

		dag.addEdge("ali", "bob");
		dag.addEdge("bob", "cecil");
		assertFalse(dag.containsLoop());
		
		dag.addEdge("cecil", "ali");
		assertTrue(dag.containsLoop());
	}

}
