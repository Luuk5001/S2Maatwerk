package com.s2m.maatwerkproject.data.models;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.MediaExtractor;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Chat {
    public static final String CHAT_MODEL_KEY = "chat_model";

    private String id;
    private String name;
    private Bitmap picture;
    private List<Group> groups;
    private List<Message> messages;

    public Chat() {
    }

    public Chat(String name, Bitmap picture, List<Group> groups, List<Message> messages) {
        this.name = name;
        this.picture = picture;
        this.groups = groups;
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addGroup(Group group) {
        //TODO implement
        throw new UnsupportedOperationException();
    }

    public void removeGroup(Group group){
        //TODO implement
        throw new UnsupportedOperationException();
    }
}