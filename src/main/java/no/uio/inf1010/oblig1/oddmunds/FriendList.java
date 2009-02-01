package no.uio.inf1010.oblig1.oddmunds;

class FriendList{
    private Friend first;

    
    
	public void addFriend(Person person) {
		if(hasFirst()){
			first.add(person);
		} else {
			
			first = new Friend(person);
		}
		
	}



    private boolean hasFirst() {
        if(first != null){
            return true;
        } else {
            return false;
        }
    }
}