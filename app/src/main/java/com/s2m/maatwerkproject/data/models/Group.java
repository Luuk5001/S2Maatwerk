package com.s2m.maatwerkproject.data.models;

import org.parceler.Parcel;

@Parcel
public class Group {

    public static final String GROUP_MODEL_KEY = "group_model";

    private String name;
    private String description;
    private String location;
    private User[] users;

    public Group() {
    }

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
