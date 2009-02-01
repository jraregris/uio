package no.uio.inf1010.oblig1.oddmunds;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonListTest {

    @Test
    public void testAddPerson() {
        PersonList pl = new PersonList();
        Person p = new Person("satan", "Melke Priiis"); 
               
        pl.add(p); // Adding a person
        
        assertEquals("First person out is not the same as first person in!", p, pl.getFirst());
        
    }
    
    @Test
    public void testEmptyPersonList() {
        PersonList pl = new PersonList();
               
        assertNull("Empty PersonList should return null!",pl.getFirst()); 
        
    }
    
}