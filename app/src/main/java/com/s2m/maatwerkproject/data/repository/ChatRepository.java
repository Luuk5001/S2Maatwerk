package com.s2m.maatwerkproject.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.Message;

import java.util.List;

public class ChatRepository implements ChatRepositoryInterface {

    public interface ChatRepositoryCallback{
        void SingleChat(Chat chat, String callKey);
        void error(String errorMessage, String callKey);
    }

    public static final String KEY_CHAT_BY_ID = "chat_by_id";

    private DatabaseReference reference;
    private ChatRepositoryCallback callback;

	public ChatRepository(ChatRepositoryCallback callback) {
	    this.callback = callback;
        reference = Firebase.getDatabaseInstance().getReference().child("chat");
	}

	public void sendMessage(Message message, Chat chat) {
		throw new UnsupportedOperationException();
	}

	public void getChatById(String chatId){
        reference.child(chatId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String chatId = dataSnapshot.getKey();
                String chatName = (String)dataSnapshot.child("name").getValue();
                Chat chat = new Chat(chatId, chatName, null);
                callback.SingleChat(chat, KEY_CHAT_BY_ID);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.error("ERROR", KEY_CHAT_BY_ID);
            }
        });
    }

	@Override
	public void createChat(Chat chat, List<Group> groups) {
	    //Add a new chat
	    DatabaseReference chatRef = reference.push();
		chatRef.setValue(chat);
	    String key = chatRef.getKey();
	    //Add the chat key to all participating groups
	    DatabaseReference groupRef = reference.getParent().child("group");
        for (Group group : groups) {
            groupRef.child(group.getId()).child("chats").child(key).setValue(true);
        }
    }
}