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

    public void add(Person person) {
       if(hasNext()){
           next.add(person);
       } else {
           next = new Friend(person);
       }
        
    }

    
}
