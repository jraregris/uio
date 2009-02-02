package no.uio.inf1010.oblig1.oddmunds;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonTest {
    @Test
    public void testGetName() {
        Person p = new Person("benito", "Jan Banan");
        assertEquals("Jan Banan", p.getName());
    }

    @Test
    public void testGetUsername() {
        Person p = new Person("benito", "Jan Banan");
        assertEquals("Jan Banan", p.getName());

    }

    @Test
    public void testEquals() {
        Person p1 = new Person("feten", "Holger Helstpels");
        Person p2 = new Person("geita", "Holger Helstpels");
        Person p3 = new Person("mensket", "Mulkepulke");
        Person p4 = new Person("mensket", "Reknemenn");
        Person p6 = new Person("feten", "Holger Helstpels");

        assertTrue("Is not equal it self!", p1.equals(p1));
        assertFalse("Is equal with person with same name, but not username!",
                p1.equals(p2));
        assertTrue("Is equal with person with same name, username", p1
                .equals(p6));
        assertTrue("Is equal with person with same username, different name",
                p3.equals(p4));
    }

    @Test
    public void testAddFriend() {
        Person p1 = new Person("feten", "Holger Helstpels");
        Person p2 = new Person("geita", "Holger Helstpels");

        p1.add(p2); // Adding p2 to the PersonList
        p1.addFriend("geita");
        
        assertEquals("Friend added should be the same as first returned", p2,
                p1.getFriends().getFirst().getFriend());
    }

    @Test
    public void testRemoveFriend() {
        Person p1 = new Person("feten", "Holger Helstpels");
        Person p2 = new Person("geita", "Holger Helstpels");

        p1.add(p2);
        //assertEquals(p2, p1.getFriends().getFirst().getFriend()); // Making sure
                                                                  // the friend
                                                                  // is added in
                                                                  // the first
                                                                  // place.

    }
}
