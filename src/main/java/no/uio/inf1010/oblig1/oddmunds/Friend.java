package no.uio.inf1010.oblig1.oddmunds;

public class Friend {

    private Person friend;

    private Friend next;

    public Friend(Person person) {
        this.friend = person;
    }

    public void add(Person person) {
        if (hasNext()) {
            next.add(person);
        } else {
            next = new Friend(person);
        }

    }

    public Person getFriend() {
        return friend;
    }

    public Friend getNext() {
        return next;
    }

    public boolean hasNext() {
        if (next != null) {
            return true;
        } else {
            return false;
        }
    }

}
