package com.s2m.maatwerkproject;


import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.Message;
import com.s2m.maatwerkproject.data.models.User;

import java.util.ArrayList;
import java.util.List;

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
            new Group("Group5", null, null, new User[]{users[6], users[0], users[5]})
    };
}
