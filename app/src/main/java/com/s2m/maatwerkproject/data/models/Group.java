package com.s2m.maatwerkproject.data.models;

import com.google.firebase.database.Exclude;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Group {

    public static final String GROUP_MODEL_KEY = "group_model";

    private String id;
    private String name;
    private String description;
    private String location;
    private List<Group> chats;
    private List<User> users;

    public Group() {
    }

    public Group(String name, String description, String location, List<User> users) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.users = users;
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
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

    @Exclude
    public List<Group> getChats() {
        return chats;
    }

    public void setChats(List<Group> chats) {
        this.chats = chats;
    }

    @Exclude
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
