package no.uio.inf1010.oblig1.oddmunds;
class Person{
	
    public Person(String name, String username) {
		this.name = name;
		this.username = username;
		friends = new FriendList();
	}

	private String name;
    private String username;
    private FriendList friends;

    private Person next;

    boolean hasNext(){
	if(next != null){
	    return true;
	} else {
		return false;
	    }
	}
    
	
    void insertPerson(Person person){
	if(hasNext()){
	    next.insertPerson(person);
	} else {
	    next = person;
	}
    }

    Person getNext(){
	return next;
    }

    String getName(){
	return name;
    }

    String getUsername(){
	return username;
    }

    FriendList getFriends(){
	return friends;
    }
    
    void addFriend(Person person){
    	friends.addFriend(person);
    }
 

}