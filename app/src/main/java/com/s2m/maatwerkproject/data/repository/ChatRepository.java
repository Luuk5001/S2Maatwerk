package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.Message;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.User;

import java.util.List;

public class ChatRepository extends BaseRepository<Chat> implements IChatRepository {

	public ChatRepository(IRepoCallback<Chat> callback) {
		super(Chat.class, callback, Firebase.getDatabase().getReference().child("chat"));
	}

	public void sendMessage(Message message, Chat chat) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void createChat(Chat chat, List<Group> groups) {
	    //Add a new chat
	    DatabaseReference chatRef = reference.push();
	    String key = chatRef.getKey();
	    chatRef.setValue(chat);
	    //Add the chat key to all participating groups
	    DatabaseReference groupRef = reference.getParent().child("group");
        for (Group group : groups) {
            groupRef.child(group.getId()).child("chats").child(key).setValue(true);
        }
    }

    @Override
    public void getMyChats(String userId) {

    }

	@Override
	public void setChildEventListener(String userId) {

	}
}