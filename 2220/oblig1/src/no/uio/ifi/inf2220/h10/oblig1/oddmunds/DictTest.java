import static org.junit.Assert.*;

public class DictTest {
	@org.junit.Test
	public void testInsert() {
		Dict d = new Dict();
		assertFalse(d.has("familie"));
		d.insert("familie");
		assertTrue(d.has("familie"));
	}

	@org.junit.Test
	public void testRemoveHead() {
		Dict d = new Dict();
		d.insert("familie");
		assertTrue(d.has("familie"));
		d.remove("familie");
		assertFalse(d.has("familie"));

	}

	@org.junit.Test
	public void testRemoveLeaf() {
		Dict d = new Dict();
		d.insert("patte", "familie");

		assertTrue(d.has("patte", "familie"));

		d.remove("familie");

		assertTrue(d.has("patte"));
		assertFalse(d.has("familie"));
	}	
}