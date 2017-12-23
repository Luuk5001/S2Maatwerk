package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.s2m.maatwerkproject.data.models.Group;

import java.util.List;

public class GroupRepository extends BaseRepository<Group> implements IGroupRepository {

	public GroupRepository(DatabaseReference reference) {
		super(reference);
	}

	public List<Group> searchGroups(String keyWords) {
		throw new UnsupportedOperationException();
	}
}