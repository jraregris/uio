package no.uio.ifi.inf2220.iinstaller.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Vizualizer {
	File tmp;
	BufferedWriter out;

	public Vizualizer() throws IOException {
		tmp = File.createTempFile("doubledag", ".dot");
		tmp.deleteOnExit();

		out = new BufferedWriter(new FileWriter(tmp));

		out.write("digraph daggraph {\nrankdir = LR;\n");
	}

	public void addEdge(String parent, String child) throws IOException {
		out.write("\""+parent+ "\" -> \"" +child+ "\";\n");
	}

	public void visualize() throws IOException {
		out.write("}\n");
		out.close();
		String command = "dot -Txlib " + tmp.getAbsolutePath();
		Runtime.getRuntime().exec(command);
	}

}
