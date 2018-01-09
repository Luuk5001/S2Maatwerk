package com.s2m.maatwerkproject.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public abstract class Firebase {

    private static FirebaseDatabase database;
    private static FirebaseAuth auth;

    public static FirebaseDatabase getDatabaseInstance() {
        if (database == null) {
            database = FirebaseDatabase.getInstance();
            //database.setPersistenceEnabled(true);
        }
        return database;
    }

    public static FirebaseAuth getAuthInstance(){
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
            //database.setPersistenceEnabled(true);
        }
        return auth;
    }
}
