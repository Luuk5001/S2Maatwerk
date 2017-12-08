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

import com.s2m.maatwerkproject.IClickableChat;
import com.s2m.maatwerkproject.adapters.ChatListAdapter;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.models.Chat;
import com.s2m.maatwerkproject.ui.activity.ChatActivity;
import com.s2m.maatwerkproject.utils.EmptyRecyclerView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.s2m.maatwerkproject.testData.chats;

public class ChatListFragment extends Fragment implements IClickableChat {

    @BindView(R.id.recyclerViewChatList)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewNoChats)
    TextView emptyView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setEmptyView(emptyView);
        ChatListAdapter chatListAdapter = new ChatListAdapter(chats, this);
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
}
