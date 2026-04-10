package dk.easv.easvticket;

import dk.easv.easvticket.be.User;

import java.util.ArrayList;
import java.util.List;

public class MockModule {

    public static List<User> userList() {
        ArrayList<User> userArrayList = new ArrayList<User>();
        userArrayList.add(new User("Bob", "bob123", "bob@example.com", 1));
        userArrayList.add(new User("Alice", "aliceAdmin", "alice@example.com", 1));
        userArrayList.add(new User("Mark", "markA", "mark@example.com", 1));
        userArrayList.add(new User("Sarah", "sarahAdmin", "sarah@example.com", 1));
        userArrayList.add(new User("Tom", "tomCoord", "tom@example.com", 2));
        userArrayList.add(new User("Emma", "emmaC", "emma@example.com", 2));
        userArrayList.add(new User("Lucas", "lucasCoord", "lucas@example.com", 2));
        userArrayList.add(new User("Nina", "ninaC", "nina@example.com", 2));
        return userArrayList;
    }
}
