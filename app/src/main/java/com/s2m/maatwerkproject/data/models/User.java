package com.s2m.maatwerkproject.data.models;

import com.google.firebase.database.Exclude;

import org.parceler.Parcel;

@Parcel
public class User {

    public static final String USER_MODEL_KEY = "user_model";

    private String id;
    private String name;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User user = (User) obj;
            return user.getId().equals(id);
        }
        return false;
    }
}
