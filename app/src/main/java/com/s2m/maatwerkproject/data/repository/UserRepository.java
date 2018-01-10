package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.utils.NonDuplicateList;

import java.util.List;

public class UserRepository extends BaseRepository<User> implements UserRepositoryInterface {

    public static final String KEY_USER = "user";
    public static final String KEY_USERS_FOUND = "users_found";
    public static final String KEY_SINGED_IN_USER = "singed_in_user";

    public UserRepository(RepositoryCallback<User> callback) {
        super(User.class, callback, Firebase.getDatabaseInstance().getReference().child("user"));
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
                    //Exclude self from searches
                    if(!child.getKey().equals(Firebase.getAuthInstance().getCurrentUser().getUid())){
                        User user = child.getValue(User.class);
                        user.setId(child.getKey());
                        list.add(user);
                    }
                }
                callback.list(list, KEY_USERS_FOUND);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
