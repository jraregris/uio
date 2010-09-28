package no.uio.ifi.inf2220.h10.oblig1.oddmunds;

import junit.framework.TestCase;

public class DictTest extends TestCase {

	public void testInsert() {
		Dict d = new Dict();
		assertFalse(d.has("familie"));
		d.insert("familie");
		assertTrue(d.has("familie"));
	}

}
