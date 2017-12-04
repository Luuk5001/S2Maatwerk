package com.s2m.maatwerkproject.models;


public class Group {
    private String name;
    private String description;
    private String location;
    private User[] users;

    public Group(String name, String description, String location, User[] users) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }
}
