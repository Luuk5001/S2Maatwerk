package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.data.Firebase;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository extends BaseRepository<Group> implements IGroupRepository {

    public static final String KEY_MY_GROUPS = "my_groups";
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

    /**
     * Returns all groups the user is a member of one at a time
     * @param userId uuid of the user
     */
    @Override
    public void getMyGroups(String userId) {
        reference.orderByChild("users/"+userId).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot groupChild: dataSnapshot.getChildren()) {
                    final Group group = groupChild.getValue(Group.class);
                    group.setId(groupChild.getKey());
                    for(DataSnapshot userChild : groupChild.child("users").getChildren()) {
                        reference.getParent().child("user/"+userChild.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                group.addUser(dataSnapshot.getValue(User.class));
                                callback.single(group, KEY_MY_GROUPS);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void createGroup(Group group){
	    // Add group
        DatabaseReference groupRef = reference.push();
        groupRef.setValue(group);
        groupRef = groupRef.child("users");
        // Add users manually to set their key to their existing one
        for (User user : group.getUsers()) {
            groupRef.child(user.getId()).setValue(true);
        }
    }
}