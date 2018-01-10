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

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.repository.ChatRepository;
import com.s2m.maatwerkproject.data.repository.RepositoryCallback;
import com.s2m.maatwerkproject.ui.activity.ChatActivity;
import com.s2m.maatwerkproject.ui.adapter.ChatListAdapter;
import com.s2m.maatwerkproject.ui.view.EmptyRecyclerView;
import com.s2m.maatwerkproject.utils.NonDuplicateList;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatListFragment extends Fragment implements ChatListAdapter.ChatListListener, RepositoryCallback<Chat> {

    @BindView(R.id.recyclerViewChatList)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewNoChats)
    TextView emptyView;

    private ChatRepository chatRepo;
    private ChatListAdapter chatListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        ButterKnife.bind(this, view);

        chatRepo = new ChatRepository(this);

        chatListAdapter = new ChatListAdapter(new NonDuplicateList<Chat>(), this);

        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(chatListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    public void addGroup(Group group){
        for(Chat chat : group.getChats()){
            chatRepo.getById(chat.getId(), "test");
        }
    }

    @Override
    public void onClickChatItem(Chat chat) {
        Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.putExtra(Chat.CHAT_MODEL_KEY, Parcels.wrap(chat));
        startActivity(intent);
    }

    @Override
    public void single(Chat obj, String callKey) {
        if (callKey.equals("test")){
            chatListAdapter.addItem(obj);
        }
    }

    @Override
    public void list(List<Chat> obj, String callKey) {

    }

    @Override
    public void error(String errorMessage) {

    }
}
