package no.uio.inf1010.oblig1.oddmunds;

class FriendList {
	private Friend first;

	public void addFriend(Person person) {
		if (hasFirst()) {
			first.add(person);
		} else {

			first = new Friend(person);
		}

	}

	private boolean hasFirst() {
		if (first != null) {
			return true;
		} else {
			return false;
		}
	}

	public Friend getFirst() {
		return first;
	}

	public boolean isFriendsWith(String username) {
		if (hasFirst()) {
			return first.isFriendsWith(username);
		} else {
			return false;
		}
	}

	public void defriend(Person person) {
		if(hasFirst()){
			if(first.getFriend()==person){
				first = first.getNext();
			} else {
				first = first.defriend(person);
			}
		}
	}
	
	@Override
	public String toString(){
		if(hasFirst()){
			return first.getFriendsString();
		}
		return null;
	}
}