package com.s2m.maatwerkproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.s2m.maatwerkproject.data.repository.UserRepository;
import com.s2m.maatwerkproject.ui.adapters.ChatListAdapter;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.ui.activity.ChatActivity;
import com.s2m.maatwerkproject.ui.adapters.IClickableChat;
import com.s2m.maatwerkproject.utils.EmptyRecyclerView;
import com.s2m.maatwerkproject.utils.Firebase;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatListFragment extends Fragment implements IClickableChat, ChildEventListener {

    @BindView(R.id.recyclerViewChatList)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewNoChats)
    TextView emptyView;

    private DatabaseReference mChatsDatabaseReference;
    private ChatListAdapter chatListAdapter;
    private List<Chat> chats;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        ButterKnife.bind(this, view);

        mChatsDatabaseReference = Firebase.getDatabase().getReference().child("chat");
        mChatsDatabaseReference.addChildEventListener(this);

        chats = new ArrayList<>();

        /*
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("group1", "testdescription", "testlocation", null));
        groups.add(new Group("group2", "testdescription", "testlocation", null));
        groups.add(new Group("group3", "testdescription", "testlocation", null));
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("message1", 5000, groups.get(0)));
        messages.add(new Message("message2", 5000, groups.get(2)));
        messages.add(new Message("message3", 5000, groups.get(1)));
        messages.add(new Message("message4", 5000, groups.get(2)));
        messages.add(new Message("message5", 5000, groups.get(0)));


        mChatsDatabaseReference.push().setValue(new Chat("chatname", groups, messages));
        */

        recyclerView.setEmptyView(emptyView);
        chatListAdapter = new ChatListAdapter(chats, this);
        recyclerView.setAdapter(chatListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    @Override
    public void onClickChatItem(Chat chat) {
        Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.putExtra(Chat.CHAT_MODEL_KEY, Parcels.wrap(chat));
        startActivity(intent);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Chat chat = dataSnapshot.getValue(Chat.class);
        chat.setId(dataSnapshot.getKey());
        chatListAdapter.addItem(chats.size(), chat);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
