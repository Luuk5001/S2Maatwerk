package com.s2m.maatwerkproject.models;


public class Chat {
    private String name;
    private Group[] groups;
    private Message[] messages;

    public Chat(String name, Group[] groups, Message[] messages) {
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

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }
}