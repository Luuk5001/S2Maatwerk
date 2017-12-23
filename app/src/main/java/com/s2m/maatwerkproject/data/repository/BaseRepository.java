package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.DatabaseReference;

public abstract class BaseRepository<T> implements IBaseRepository<T> {
	private DatabaseReference reference;

	public BaseRepository(DatabaseReference reference) {
		throw new UnsupportedOperationException();
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

	public T getById(String id) {
		throw new UnsupportedOperationException();
	}
}