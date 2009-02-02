package no.uio.inf1010.oblig1.oddmunds;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PersonListTest {

    @Test
    public void testAddPerson() {
        PersonList pl = new PersonList();
        Person p = new Person("satan", "Melke Priiis");

        pl.add(p); // Adding a person

        assertEquals("First person out is not the same as first person in!", p,
                pl.getFirst());

    }

    @Test
    public void testGetName() {
        PersonList pl = new PersonList();
        Person p = new Person("satan", "Melke Priiis");
        pl.add(p);
        assertEquals("Does not return the correct Name", "Melke Priiis", pl
                .getFirst().getName());

    }

    @Test
    public void testEmptyPersonList() {
        PersonList pl = new PersonList();

        assertNull("Empty PersonList should return null!", pl.getFirst());

    }

    @Test
    public void testRemovePerson() {
        PersonList pl = new PersonList();
        Person p1 = new Person("metmor", "Old as a hill!");
        pl.add(p1);
        assertTrue(p1.equals(pl.getFirst()));
        pl.remove("metmor");
        assertNull(pl.getFirst());
    }
    
}