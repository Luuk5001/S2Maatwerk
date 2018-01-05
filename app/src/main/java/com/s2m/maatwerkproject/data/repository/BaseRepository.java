package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public abstract class BaseRepository<T> implements IBaseRepository<T> {

    protected DatabaseReference reference;
    protected IRepoCallback callback;

    private final Class<T> type;

	BaseRepository(Class<T> type, IRepoCallback callback, DatabaseReference reference) {
	    this.type = type;
	    this.reference = reference;
	    this.callback = callback;
	}

	public void insert(T entity) {
		throw new UnsupportedOperationException();
	}

	public void update(T entity) {
		throw new UnsupportedOperationException();
	}

	public void delete(T entity) {
		throw new UnsupportedOperationException();
	}

	public void getById(String id, final String callKey) {
        reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.single(dataSnapshot.getValue(type), callKey);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
	}
}