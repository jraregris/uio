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

	public void testMarkAsUnInstalled() {
		DoubleDAG dag = new DoubleDAG();
		dag.addNode("node", true);
		assertTrue(dag.nodes.get("node").installed);
		/*
		 * assertFalse(dag.nodes.get("node").installed);
		 * dag.markAsInstalled("node");
		 */
	}

}
