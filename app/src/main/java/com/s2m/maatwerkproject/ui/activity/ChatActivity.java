package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.adapters.ChatMessageListAdapter;
import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.utils.EmptyRecyclerView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.recyclerViewChatMessageList)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewNoMessages)
    TextView emptyView;
    @BindView(R.id.toolbarChat)
    Toolbar toolbar;

    private Chat chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        chat = Parcels.unwrap(getIntent().getParcelableExtra(Chat.CHAT_MODEL_KEY));

        toolbar.setTitle(chat.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_background);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ChatInfoActivity.class);
        intent.putExtra(Chat.CHAT_MODEL_KEY, Parcels.wrap(chat));
        startActivity(intent);
    }
}
