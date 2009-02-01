package no.uio.inf1010.oblig1.oddmunds;

public class Oblig {

	/**s
	 * @param args
	 */
	public static void main(String[] args) {
		PersonList pl = new PersonList();
		Person p1 = new Person("satan", "Satan");
		Person p2 = new Person("gro", "Gro Harlem");
		
		
		pl.add(p1);
		pl.add(p2);
		
		System.out.println(pl.getFirst().getName());
		Person p3 = pl.getFirst().getNext();
		System.out.println(p3.getName());
		

	}

}
