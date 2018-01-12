package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.utils.NonDuplicateList;

import java.util.List;

public class UserRepository implements UserRepositoryInterface {

    public interface UserRepositoryCallback{
        void singleUser(User user, String callKey);
        void userList(List<User> users, String callKey);
        void error(String errorMessage, String callKey);
    }

    public static final String KEY_USER_BY_ID = "user_by_id";
    public static final String KEY_SEARCH_USERS = "search_users";

    private DatabaseReference reference;
    private UserRepositoryCallback callback;

    public UserRepository(UserRepositoryCallback callback) {
        this.callback = callback;
        reference = Firebase.getDatabaseInstance().getReference().child("user");
    }

    public void getUserById(String userId){
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userId = dataSnapshot.getKey();
                String userName = (String)dataSnapshot.child("name").getValue();
                User user = new User(userId, userName);
                callback.singleUser(user, KEY_USER_BY_ID);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.error("ERROR", KEY_USER_BY_ID);
            }
        });
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
                callback.userList(list, KEY_SEARCH_USERS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void createUser(User user) {
        reference.child(user.getId()).setValue(user);
    }
}
