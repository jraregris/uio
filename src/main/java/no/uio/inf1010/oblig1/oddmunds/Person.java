package no.uio.inf1010.oblig1.oddmunds;

class Person {

    public Person(String username, String name) {
        this.name = name;
        this.username = username;
        friends = new FriendList();
    }

    private String name;
    private String username;
    private FriendList friends;

    private Person next;

    void add(Person person) {
        if (hasNext()) {
            next.add(person);
        } else {
            next = person;
        }
    }

    public Person getNext() {
        if (hasNext()) {
            return next;
        } else {
            return null;
        }
    }

    private boolean hasNext() {
        if (next != null){
            return true;
        } else {
            return false;
        }
    }

    String getName() {
        return name;
    }

    String getUsername() {
        return username;
    }

    FriendList getFriends() {
        return friends;
    }

    void addFriend(Person person) {
        friends.addFriend(person);
    }

}