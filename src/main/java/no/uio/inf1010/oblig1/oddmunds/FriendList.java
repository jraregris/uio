package no.uio.inf1010.oblig1.oddmunds;

class FriendList extends Chain{
    private Friend first;

	public void addFriend(Person person) {
		if(hasFirst()){
			first.addFriend(person);
		} else {
			
			first = new Friend(person);
		}
		
	}
}