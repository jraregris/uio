package no.uio.inf1010.oblig1.oddmunds;

class PersonList{
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
	
	
}