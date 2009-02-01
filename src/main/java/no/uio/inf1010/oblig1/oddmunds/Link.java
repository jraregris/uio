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
}
