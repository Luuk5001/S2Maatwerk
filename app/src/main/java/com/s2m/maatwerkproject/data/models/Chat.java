package com.s2m.maatwerkproject.data.models;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.MediaExtractor;

import com.google.firebase.database.Exclude;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Chat {
    public static final String CHAT_MODEL_KEY = "chat_model";

    private String id;
    private String name;
    private Bitmap picture;
    private List<Message> messages;

    public Chat() {
    }

    public Chat(String name, Bitmap picture, List<Message> messages) {
        this.name = name;
        this.picture = picture;
        this.messages = messages;
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

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Chat){
            Chat chat = (Chat) obj;
            return chat.getId().equals(id);
        }
        return false;
    }
}