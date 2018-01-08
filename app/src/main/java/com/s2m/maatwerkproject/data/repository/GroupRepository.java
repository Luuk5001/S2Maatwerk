package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.data.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRepository extends BaseRepository<Group> implements IGroupRepository, ChildEventListener {

    public static final String KEY_GET_GROUPS = "my_groups";
    public static final String KEY_SEARCH_GROUPS = "search_groups";

	public GroupRepository(IRepoCallback callback) {
		super(Group.class, callback, Firebase.getDatabase().getReference().child("group"));
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
                callback.list(list, KEY_SEARCH_GROUPS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void setGroupListener(String userId) {
        reference.orderByChild("users/"+userId).equalTo(true).addChildEventListener(this);
    }

    @Override
    public void createGroup(Group group){;
        Map<String, User> users = new HashMap<>();
        for (User user : group.getUsers()) {
            users.put(user.getId(), user);
        }
	    /*
	    // Add group
        DatabaseReference groupRef = reference.push();
        groupRef.setValue(group);
        groupRef = groupRef.child("users");
        // Add users manually to set key to their existing one
        for (User user : group.getUsers()) {
            groupRef.child(user.getId()).setValue(true);
        }
        */
    }

    @Override
    public void onChildAdded(DataSnapshot groupChild, String s) {
        final Group group = groupChild.getValue(Group.class);
        group.setId(groupChild.getKey());
        for(DataSnapshot userChild : groupChild.child("users").getChildren()) {
            reference.getParent().child("user/"+userChild.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    user.setId(dataSnapshot.getKey());
                    group.addUser(user);
                    callback.single(group, KEY_GET_GROUPS);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}