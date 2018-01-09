package com.s2m.maatwerkproject.data.repository;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupRepository extends BaseRepository<Group> implements GroupRepositoryInterface, ChildEventListener {

    public GroupRepository(RepositoryCallback callback) {
		super(Group.class, callback, Firebase.getDatabaseInstance().getReference().child("group"));
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
                callback.list(list, KEY_GROUPS_FOUND);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void setChildEventListener(String userId) {
        reference.orderByChild("users/"+userId).equalTo(true).addChildEventListener(this);
    }

    @Override
    public void createGroup(Group group){
        writeChanges(reference.push().getKey(), group, KEY_GROUP_CREATED);
    }

    @Override
    public void updateGroup(Group group) {
        writeChanges(group.getId(), group, KEY_GROUP_UPDATED);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        updateGroupList(dataSnapshot);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        updateGroupList(dataSnapshot);
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        Group group = dataSnapshot.getValue(Group.class);
        group.setId(dataSnapshot.getKey());
        callback.single(group, KEY_GROUP_DELETED);
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    private void writeChanges(String id, final Group group, final String callKey){
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
                    callback.single(group, callKey);
                }
                else{
                    //TODO handle error correctly
                    callback.error("ERROR");
                }
            }
        });
    }

    private void updateGroupList(DataSnapshot dataSnapshot){
        final Group group = dataSnapshot.getValue(Group.class);
        group.setId(dataSnapshot.getKey());
        for(DataSnapshot userChild : dataSnapshot.child("users").getChildren()) {
            reference.getParent().child("user/"+userChild.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    user.setId(dataSnapshot.getKey());
                    group.addUser(user);
                    callback.single(group, KEY_GROUP_LISTENER);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //TODO handle error correctly
                    callback.error("ERROR");
                }
            });
        }
    }
}