package no.uio.inf1010.oblig1.oddmunds;

public class Friend {

    private Person friend;

    private Friend next;

    public Friend(Person person) {
        this.friend = person;
    }

    public void add(Person person) {
        if (hasNext()) {
            next.add(person);
        } else {
            next = new Friend(person);
        }

    }

    public Person getFriend() {
        return friend;
    }

    public Friend getNext() {
        return next;
    }

    public boolean hasNext() {
        if (next != null) {
            return true;
        } else {
            return false;
        }
    }

	public boolean isFriendsWith(String username) {
		if(friend.getUsername().equals(username)){
			return true;
		} else if(hasNext()){
			return next.isFriendsWith(username);
		} else {
		return false;
		}
	}

	public Friend defriend(Person person) {
		if(friend==person){
			return next;
		} else {
			next = next.defriend(person);
			return this;
		}
	}

	public String getFriendsString() {
		String s = new String(friend.getName() + "\n");
		if(hasNext()){
			s = s + next.getFriendsString();
		}
		return s;
	}

}
