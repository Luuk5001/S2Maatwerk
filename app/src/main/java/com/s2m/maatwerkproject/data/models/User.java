package com.s2m.maatwerkproject.data.models;

import org.parceler.Parcel;

@Parcel
public class User {
    public static final String USER_MODEL_KEY = "user_model";
    private String name;
    private Group[] groups;

    public User() {
    }

    public User(String name, Group[] groups) {
        this.name = name;
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group[] getGroups() {
        return groups;
    }

    public void setGroups(Group[] groups) {
        this.groups = groups;
    }
}