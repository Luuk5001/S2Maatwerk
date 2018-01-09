package com.s2m.maatwerkproject.data.repository;

import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.Message;

import java.util.List;

public interface ChatRepositoryInterface {

	void sendMessage(Message message, Chat chat);
	void createChat(Chat chat, List<Group> groups);
	void getMyChats(String userId);
}