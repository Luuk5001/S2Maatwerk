package com.s2m.maatwerkproject;


import com.s2m.maatwerkproject.Models.Chat;
import com.s2m.maatwerkproject.Models.Group;
import com.s2m.maatwerkproject.Models.Message;
import com.s2m.maatwerkproject.Models.User;

public class testData {
    public static User[] users = new User[]{
            new User("User1", null),
            new User("User2", null),
            new User("User3", null),
            new User("User4", null),
            new User("User5", null),
            new User("User6", null),
            new User("User7", null),
            new User("User8", null),
            new User("User9", null)};
    public static Group[] groups = new Group[]{
            new Group("Group1", null, null, new User[]{users[0], users[1], users[2]}),
            new Group("Group2", null, null, new User[]{users[3], users[4], users[5]}),
            new Group("Group3", null, null, new User[]{users[6], users[7], users[8]}),
            new Group("Group4", null, null, new User[]{users[8], users[3], users[4]}),
            new Group("Group5", null, null, new User[]{users[6], users[0], users[5]}),
            new Group("TEST", null, null, null),
            new Group("TEST", null, null, null),
            new Group("TEST", null, null, null),
            new Group("TEST", null, null, null),
            new Group("TEST", null, null, null),
            new Group("TEST", null, null, null),
            new Group("TEST", null, null, null),
            new Group("TEST", null, null, null)
    };

    public static Message[] messages = new Message[]{
            new Message("Message1", 500, groups[0]),
            new Message("Message1", 500, groups[1]),
            new Message("Message1", 500, groups[3]),
            new Message("Message1", 500, groups[4]),
            new Message("Message1", 500, groups[0]),
            new Message("Message1", 500, groups[2]),
            new Message("Message1", 500, groups[0]),
            new Message("Message1", 500, groups[1]),
            new Message("Message1", 500, groups[2]),
            new Message("Message1", 500, groups[4]),
            new Message("Message1", 500, groups[1]),
    };

    public static Chat[] chats = new Chat[]{
            new Chat("Chat1", new Group[]{groups[0], groups[1], groups[3], groups[4]}, new Message[]{messages[0], messages[1], messages[2], messages[3]}),
            new Chat("Chat2", new Group[]{groups[0], groups[2], groups[3], groups[4]}, new Message[]{messages[4], messages[5]}),
            new Chat("Chat3", new Group[]{groups[0], groups[1], groups[2], groups[4]}, new Message[]{messages[6], messages[7], messages[8], messages[9], messages[10]}),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null),
            new Chat("TEST", null, null)
    };
}
