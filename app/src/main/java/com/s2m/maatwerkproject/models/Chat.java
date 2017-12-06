package com.s2m.maatwerkproject.models;

import org.parceler.Parcel;

@Parcel
public class Chat {
    public static final String CHAT_MODEL_KEY = "chat_model";

    private String name;
    private Group[] groups;
    private Message[] messages;

    public Chat() {
    }

    public Chat(String name, Group[] groups, Message[] messages) {
        this.name = name;
        this.groups = groups;
        this.messages = messages;
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

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }
}