package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.s2m.maatwerkproject.data.models.User;

import java.util.List;

public class UserRepository extends BaseRepository<User> implements IUserRepository {

	public UserRepository(DatabaseReference reference) {
		super(reference);
	}

	public List<User> searchUsers(String keyWords) {
		throw new UnsupportedOperationException();
	}
}