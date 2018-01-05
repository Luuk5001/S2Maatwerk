package com.s2m.maatwerkproject.data.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.format.DateFormat;

import com.google.firebase.database.Exclude;

import org.parceler.Parcel;

import java.util.Calendar;
import java.util.Date;

@Parcel
public class Message {

    private int id;
    private long timeStamp;
    private String text;
    private Group group;

    public Message() {
    }

    public Message(long timeStamp, String text, Group group) {
        this.text = text;
        this.timeStamp = timeStamp;
        this.group = group;
    }

    @Exclude
    public int getId() {
        return id;
    }

    @Exclude
    public void setId(int id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getText() {
        return text;
    }

    public Group getGroup() {
        return group;
    }

    public String getDateTimeString(Context context){
        Date date = new Date(timeStamp * 1000L);
        return android.text.format.DateFormat.getMediumDateFormat(context).format(date) + " @ " + android.text.format.DateFormat.getTimeFormat(context).format(date);
    }
}
