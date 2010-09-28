package no.uio.ifi.inf2220.h10.oblig1.oddmunds;

public class DictNode {
	DictNode left;
	DictNode right;
	String data;
	
	public DictNode(String string) {
		data = new String(string);
	}
	public boolean has(String string) {
		int i = data.compareToIgnoreCase(string);
		if(i==0)
			return true;
		if(i<0 && hasleft())
			return left.has(string);
		else if (hasright())
			return right.has(string);
		return false;
	}
	private boolean hasright() {
		if(right != null)
			return true;
		return false;
	}
	private boolean hasleft() {
		if(left != null)
			return true;
		return false;
	}
	public void insert(String string){
		int i = data.compareToIgnoreCase(string);
		if(i==0)
			return;
		if(i<0)
			if(hasleft())
				left.insert(string);
			else
				left = new DictNode(string);
		else
			if(hasright())
				right.insert(string);
			else
				right = new DictNode(string);
	}
}