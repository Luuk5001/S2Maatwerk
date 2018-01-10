package com.s2m.maatwerkproject.data.models;

import com.google.firebase.database.Exclude;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Chat {
    public static final String CHAT_MODEL_KEY = "chat_model";

    private String id;
    private String name;
    private List<Message> messages;

    public Chat() {
    }

    public Chat(String id, String name, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.messages = messages;
    }

    public Chat(String name, List<Message> messages) {
        this.name = name;
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

    @Exclude
    public List<Message> getMessages() {
        return messages;
    }

    @Exclude
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