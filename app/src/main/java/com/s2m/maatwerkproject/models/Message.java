package com.s2m.maatwerkproject.models;


public class Message {
    private String text;
    private int timeStamp;
    private Group group;

    public Message(String text, int timeStamp, Group group) {
        this.text = text;
        this.timeStamp = timeStamp;
        this.group = group;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
