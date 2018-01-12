package com.s2m.maatwerkproject.data.repository;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.utils.NonDuplicateList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupRepository implements GroupRepositoryInterface {

    public interface GroupRepositoryCallback{
        void singleGroup(Group group, String callKey);
        void groupList(List<Group> groups, String callKey);
        void error(String errorMessage, String callKey);
    }

    private DatabaseReference reference;
    private GroupRepositoryCallback callback;

    public GroupRepository(GroupRepositoryCallback callback) {
        this.callback = callback;
        reference = Firebase.getDatabaseInstance().getReference().child("group");
	}

    @Override
    public void getMyGroups(final String userId){
        reference.orderByChild("users/"+userId).equalTo(true)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot groupChild : dataSnapshot.getChildren()) {
                    String name = (String)groupChild.child("name").getValue();
                    String description = (String)groupChild.child("description").getValue();
                    String location = (String)groupChild.child("location").getValue();
                    HashMap<String, Boolean> userMap = (HashMap)groupChild.child("users").getValue();
                    HashMap<String, Boolean> chatMap = (HashMap)groupChild.child("chats").getValue();
                    String[] userIds = (userMap == null) ? new String[0] : userMap.keySet().toArray(new String[0]);
                    String[] chatIds = (chatMap == null) ? new String[0] : chatMap.keySet().toArray(new String[0]);
                    getUserDetails(createGroupObject(groupChild.getKey(), name, description, location, userIds, chatIds));
                }
            }

            private void getUserDetails(final Group group){
                for(final User user : group.getUsers()){
                    reference.getParent().child("user").child(user.getId())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User tempUser = new User();
                            tempUser.setId(dataSnapshot.getKey());
                            tempUser.setName((String)dataSnapshot.child("name").getValue());
                            group.getUsers().add(tempUser);
                            callback.singleGroup(group, KEY_MY_GROUPS);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO handle error
                callback.error("ERROR", KEY_MY_GROUPS);
            }
        });
    }

    @Override
    public void searchGroups(String keywords) {
        reference.limitToFirst(50).startAt(keywords).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Group> list = new ArrayList<>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Group group = child.getValue(Group.class);
                    group.setId(child.getKey());
                    list.add(group);
                }
                callback.groupList(list, KEY_SEARCH_GROUPS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void createGroup(Group group){
        writeChangesToDatabase(reference.push().getKey(), group, KEY_CREATE_GROUP);
    }

    @Override
    public void updateGroup(Group group) {
        writeChangesToDatabase(group.getId(), group, KEY_UPDATE_GROUP);
    }

    private void writeChangesToDatabase(String id, final Group group, final String callKey){
        DatabaseReference groupRef = reference.child(id);
        groupRef.setValue(group);
        groupRef = groupRef.child("users");
        // Add users manually to set key to their existing one
        HashMap<String, Boolean> users = new HashMap<>();
        for (User user : group.getUsers()) {
            users.put(user.getId(), true);
        }
        groupRef.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    callback.singleGroup(group, callKey);
                }
                else{
                    //TODO handle error correctly
                    callback.error("ERROR", callKey);
                }
            }
        });
    }

    private Group createGroupObject(String id, String name, String description, String location, String[] userIds, String[] chatIds){
        List<User> users = new NonDuplicateList<>();
        List<Chat> chats = new NonDuplicateList<>();
        for (String userId : userIds) {
            users.add(new User(userId, null));
        }
        for (String chatId : chatIds){
            chats.add(new Chat(chatId, null, null));
        }
        return new Group(id, name, description, location, users, chats);
    }
}