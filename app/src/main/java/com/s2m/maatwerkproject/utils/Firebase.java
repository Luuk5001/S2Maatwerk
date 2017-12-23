package com.s2m.maatwerkproject.utils;

import com.google.firebase.database.FirebaseDatabase;

public abstract class Firebase {
    private static FirebaseDatabase database;

    public static FirebaseDatabase getDatabase() {
        if (database == null) {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }
        return database;
    }
}
