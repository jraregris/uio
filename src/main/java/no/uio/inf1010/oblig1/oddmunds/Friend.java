package no.uio.inf1010.oblig1.oddmunds;

public class Friend extends Link{

	public Friend(Person person) {
		this.friend = person;
	}

	private Friend next;
	private Person friend;
	
	public Person getFriend(){
		return friend;
	}
	
	public Friend getNext(){
		return next;
	}
}
