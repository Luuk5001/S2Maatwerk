package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.utils.NonDuplicateList;

import java.util.List;

public class UserRepository extends BaseRepository<User> implements IUserRepository {

    public static final String KEY_GET_USER = "check_if_user_exists";
    public static final String KEY_SEARCH_USERS = "search_users";

    public UserRepository(IRepoCallback<User> callback) {
        super(User.class, callback, Firebase.getDatabase().getReference().child("user"));
    }

    @Override
    public void addUser(User user) {
        reference.child(user.getId()).setValue(user);
    }

    @Override
    public void searchUsers(String keywords) {
        reference.limitToFirst(50).startAt(keywords).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> list = new NonDuplicateList<>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    User user = child.getValue(User.class);
                    user.setId(child.getKey());
                    list.add(user);
                }
                callback.list(list, KEY_SEARCH_USERS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void setChildEventListener(String userId) {

    }
}
