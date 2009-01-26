package no.uio.inf1010.oblig1.oddmunds;

public abstract class Chain {
	private Link first;
	
	public Link getLink(){
		if(hasFirst()){
			return first;
		} else {
			return null;
		}
	}

	public boolean hasFirst() {
		if(first != null){
			return true;
		} else {
			return false;
		}
	}
}
