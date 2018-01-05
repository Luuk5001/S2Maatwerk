package com.s2m.maatwerkproject.data.repository;

import com.s2m.maatwerkproject.data.models.Group;

import java.util.List;

public interface IGroupRepository {

	void searchGroups(String keywords);
	void getMyGroups(String userId);
	void createGroup(Group group);
}