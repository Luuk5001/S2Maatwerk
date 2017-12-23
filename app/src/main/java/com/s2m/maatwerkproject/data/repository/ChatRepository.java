package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.DatabaseReference;

import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.Message;

public class ChatRepository extends BaseRepository<Chat> implements IChatRepository {

	public ChatRepository(DatabaseReference reference) {
		super(reference);
	}

	public void sendMessage(Message message, Chat chat, Group group) {
		throw new UnsupportedOperationException();
	}
}