package no.uio.inf1010.oblig1.oddmunds;

class Person extends Link {

	public Person(String username, String name) {
		this.name = name;
		this.username = username;
		friends = new FriendList();
	}

	private String name;
	private String username;
	private FriendList friends;

	private Person next;

	void insertPerson(Person person) {
		if (hasNext()) {
			next.insertPerson(person);
		} else {
			next = person;
		}
	}

	public Person getNext(){
		if(hasNext()){
			return next;
		} else {
			return null;
		}
	}
	
	
	String getName() {
		return name;
	}

	String getUsername() {
		return username;
	}

	FriendList getFriends() {
		return friends;
	}

	void add(Person person) {
		friends.addFriend(person);
	}

}