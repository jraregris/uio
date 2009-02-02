package no.uio.inf1010.oblig1.oddmunds;

class Person {

    private String name;

    private String username;
    private FriendList friends;
    private Person next;

    public Person(String username, String name) {
        this.name = name;
        this.username = new String(username).toLowerCase();
        friends = new FriendList();
    }

    void add(Person person){
        if (hasNext()) {
            next.add(person);
        } else {
            next = person;
        }
    }

    void addFriend(Person person) {
        friends.addFriend(person);
    }

    void removeNext() {
        if (next != null) {
            next = null;
        }
    }

    public FriendList getFriends() {
        return friends;
    }

    public String getName() {
        return name;
    }

    public Person getNext() {
        if (hasNext()) {
            return next;
        } else {
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean hasNext() {
        if (next != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Person p) {
        if (this.username.equalsIgnoreCase(p.username)) {
            return true;
        } else {
            return false;
        }
    }

    public Person remove(String username2) {
        if(username.equals(username2)) {
            return next;
        } else {
            next = next.remove(username2);
            return this;
        }
        
    }

}