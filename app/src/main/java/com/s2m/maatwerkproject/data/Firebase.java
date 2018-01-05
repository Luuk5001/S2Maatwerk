package com.s2m.maatwerkproject.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public abstract class Firebase {

    private static FirebaseDatabase database;
    public static FirebaseUser currentUser;

    public static FirebaseDatabase getDatabase() {
        if (database == null) {
            database = FirebaseDatabase.getInstance();
            //database.setPersistenceEnabled(true);
        }
        return database;
    }
}
