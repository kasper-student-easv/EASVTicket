package dk.easv.easvticket.be;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private int type;
    private List<Event> events;
    public User() {
        this.events = new ArrayList<>();
    }
    public User(String name, String userName, String email, int type) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.type = type;
        this.events = new ArrayList<>();
    }
    public User(int id, String name, String userName, String email, int type) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.type = type;
        this.events = new ArrayList<>();
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getType() { return type; }
    public void setType(int type) { this.type = type; }
    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
}
