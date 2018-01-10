package com.s2m.maatwerkproject.data.repository;

import com.s2m.maatwerkproject.data.models.Group;

public interface GroupRepositoryInterface {
	String KEY_GROUP = "group_listener";
	String KEY_GROUPS_FOUND = "groups_found";
	String KEY_GROUP_CREATED = "group_created";
	String KEY_GROUP_UPDATED = "group_updated";
	String KEY_GROUP_DELETED = "delete_group";
	void searchGroups(String keywords);
	void getMyGroups(String userId);
	void createGroup(Group group);
	void updateGroup(Group group);
}