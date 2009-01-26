package no.uio.inf1010.oblig1.oddmunds;

class FriendList{
    private Friend first;

	public void addFriend(Person person) {
		if(hasNext()){
			next.addFriend(person);
		} else {
			
			first = new Friend(person);
		}
		
	}
}