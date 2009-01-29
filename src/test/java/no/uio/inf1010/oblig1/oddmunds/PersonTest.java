package no.uio.inf1010.oblig1.oddmunds;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonTest {

	@Test
	public void testPerson() {
		Person p = new Person("person", "Per Persson");
		assertEquals("person", p.getUsername());
	}

	@Test
	public void testInsertPerson() {
		fail("Not yet implemented");
	}

}
