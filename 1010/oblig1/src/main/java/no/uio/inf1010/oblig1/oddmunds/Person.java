package no.uio.inf1010.oblig1.oddmunds;

class Person {

	private String name;

	private String username;
	private FriendList friends;
	private Person next;

	public Person(String username, String name) {
		this.name = name;
		this.username = new String(username).toLowerCase();
		friends = new FriendList();
	}

	void add(Person person) {
		if (hasNext()) {
			next.add(person);
		} else {
			next = person;
		}
	}

	void addFriend(Person person) {
		friends.addFriend(person);
	}

	void removeNext() {
		if (next != null) {
			next = null;
		}
	}

	public FriendList getFriends() {
		return friends;
	}

	public String getName() {
		return name;
	}

	public Person getNext() {
		if (hasNext()) {
			return next;
		} else {
			return null;
		}
	}

	public String getUsername() {
		return username;
	}

	public boolean hasNext() {
		if (next != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Person p) {
		if (this.username.equalsIgnoreCase(p.username)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * A (perhaps) unusual way of node removal.
	 */
	public Person remove(String usernameOfPersonToBeRemoved) {
		if (username.equals(usernameOfPersonToBeRemoved)) {
			return next;
		} else {
			next = next.remove(usernameOfPersonToBeRemoved);
			return this;
		}

	}

	public void addFriend(String usernameOfPersonToBeAdded) {
		Person p = next.getPersonByUsername(usernameOfPersonToBeAdded);
		addFriend(p);
	}

	public Person getPersonByUsername(String username) {
		if (this.username.equals(username)) {
			return this;
		} else {
			if (hasNext()) {
				return next.getPersonByUsername(username);
			} else {
				return null;
			}
		}
	}
	
	public boolean isFriendsWith(String username){
		return friends.isFriendsWith(username);
		}

	public void befriend(Person newFriend) {
		friends.addFriend(newFriend);		
	}

	public void defriend(Person person) {
		friends.defriend(person);
		
	}

	public void removeFromAllFriends(Person person) {
		if(isFriendsWith(person.getUsername())){
			defriend(person);
		}
		if(hasNext()){
			next.removeFromAllFriends(person);
		}
		
	}

}