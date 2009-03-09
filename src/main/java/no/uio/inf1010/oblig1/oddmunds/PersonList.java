package no.uio.inf1010.oblig1.oddmunds;

class PersonList {
	private Person first;

	public Person getFirst() {
		if (hasFirst()) {
			return first;
		} else {
			return null;
		}
	}

	public boolean hasFirst() {
		if (first != null) {
			return true;
		} else {
			return false;
		}
	}

	public void add(Person p) {
		if (hasFirst()) {
			first.add(p);
		} else {
			first = p;
		}
	}

	public void addNewPerson(String username, String name) {
		add(new Person(username, name));
	}

	public void remove(String username) {
		if(hasFirst())
		{
	    first.removeFromAllFriends(getPersonByUsername(username));
	
		first = first.remove(username);
		}

	}

	public Person getPersonByUsername(String username) {
		if (hasFirst()) {
			return first.getPersonByUsername(username);
		} else {
			return null;
		}

	}

	public void befriend(String username1, String username2) {
		if (getPersonByUsername(username1) != null
				&& getPersonByUsername(username2) != null) {
			if (getPersonByUsername(username1).isFriendsWith(username2) != true) {
				getPersonByUsername(username1).befriend(getPersonByUsername(username2));
			}
			if (getPersonByUsername(username2).isFriendsWith(username1) != true) {
				getPersonByUsername(username2).befriend(getPersonByUsername(username1));
			}
		}
	}

	public void defriend(String username1, String username2) {
		if (getPersonByUsername(username1) != null
				&& getPersonByUsername(username2) != null) {
			if (getPersonByUsername(username1).isFriendsWith(username2) == true) {
				getPersonByUsername(username1).defriend(getPersonByUsername(username2));
			}
			if (getPersonByUsername(username2).isFriendsWith(username1) == true) {
				getPersonByUsername(username2).defriend(getPersonByUsername(username1));
			}
		}
		
	}

	public String listFriendsAsString(String person) {
		if(getPersonByUsername(person)!= null){
			return getPersonByUsername(person).getFriends().toString();
		} else {
			return new String("Error! Person does not exist!");
		}

	}

}