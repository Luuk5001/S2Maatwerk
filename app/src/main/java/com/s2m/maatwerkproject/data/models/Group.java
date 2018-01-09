package com.s2m.maatwerkproject.data.models;

import android.util.Log;

import com.google.firebase.database.Exclude;
import com.s2m.maatwerkproject.utils.NonDuplicateList;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Group {

    public static final String TAG = Group.class.getSimpleName();
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Group){
            Group group = (Group) obj;
            return group.getId().equals(id);
        }
        return false;
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

    @Exclude
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        if(users == null){
            Log.d(TAG, "User list does not exist, creating one...");
            users = new NonDuplicateList<User>() {
            };
        }
        users.add(user);
        Log.d(TAG, "User added to list");
    }

    public void removeUser(User user){
        if(users != null){
            users.remove(user);
            Log.d(TAG, "User removed from list");
        }
        else{
            Log.e(TAG, "User list does not exist, cannot remove user");
        }
    }
}
