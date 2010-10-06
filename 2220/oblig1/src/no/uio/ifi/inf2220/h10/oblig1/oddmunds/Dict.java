public class Dict {
	DictNode head;

	public boolean has(String... strings) {
		for (String s : strings) {
			if (head == null)
				return false;
			return head.has(s);
		}
		return false;
	}

	public void insert(String... strings) {
		for (String s : strings) {
			if (head == null)
				head = new DictNode(s);
			else
				head.insert(s);
		}
	}

	public void remove(String s) {
		if (head.data.compareToIgnoreCase(s) == 0) {
			if (head.left == null && head.right == null)
				head = null;
		} else
			head.remove(s);
	}
}
