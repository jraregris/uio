package no.uio.ifi.inf2220.h10.oblig1.oddmunds;

public class DictNode {
	DictNode left;
	DictNode right;
	String data;
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
}
