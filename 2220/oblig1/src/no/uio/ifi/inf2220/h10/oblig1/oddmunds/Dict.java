package no.uio.ifi.inf2220.h10.oblig1.oddmunds;

public class Dict {
	DictNode head;
	
	public Dict(String string) {
		File f = new File(string);
	}

	public boolean has(String string) {
		if(head == null)
			return false;
		return head.has(string);
	}

	public void insert(String string){
		if(head == null)
			head = new DictNode(string);
		else
			head.insert(string);
	}
}
