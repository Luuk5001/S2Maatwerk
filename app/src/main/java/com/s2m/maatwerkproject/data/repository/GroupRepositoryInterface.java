package com.s2m.maatwerkproject.data.repository;

import com.s2m.maatwerkproject.data.models.Group;

public interface GroupRepositoryInterface {
	String KEY_MY_GROUPS = "my_groups";
	String KEY_SEARCH_GROUPS = "search_groups";
	String KEY_CREATE_GROUP = "create_group";
	String KEY_UPDATE_GROUP = "update_group";
    void getMyGroups(String userId);
	void searchGroups(String keywords);
	void createGroup(Group group);
	void updateGroup(Group group);
}