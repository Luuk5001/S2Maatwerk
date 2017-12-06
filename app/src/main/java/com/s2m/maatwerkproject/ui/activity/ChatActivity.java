package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.adapters.ChatMessageListAdapter;
import com.s2m.maatwerkproject.models.Chat;
import com.s2m.maatwerkproject.utils.EmptyRecyclerView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewChatMessageList)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewNoMessages)
    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        Chat chat = Parcels.unwrap(getIntent().getParcelableExtra(Chat.CHAT_MODEL_KEY));

        setTitle(chat.getName());

        recyclerView.setEmptyView(emptyView);
        ChatMessageListAdapter chatMessageListAdapter = new ChatMessageListAdapter(chat.getMessages());
        recyclerView.setAdapter(chatMessageListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menuChatMuteChat:
                //TODO open mute chat dialog
                break;
            case R.id.menuChatEraseChat:
                //TODO open erase chat dialog
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
