package no.uio.inf1010.oblig1.oddmunds;

public class Link {
		private Link next;
		
		public boolean hasNext(){
			if(next != null){
				return true;
			} else {
				return false;
			}
		}
		
		public Link getNext(){
			if(hasNext()){
				return next;
			} else {
				return null;
			}
		}
		
		public void add(Link l){
			if(hasNext()){
				next.add(l);
			} else {
				next = l;
			}
		}
}
