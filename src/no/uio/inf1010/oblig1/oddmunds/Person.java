class Person{
    private String name;
    private String username;
    private FriendList friends;

    private Person next;

    boolean hasNext(){
	if(next != NULL){
	    return true;
	    else {
		return false;
	    }
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

}