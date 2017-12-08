package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2m.maatwerkproject.IClickableGroup;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.adapters.GroupListAdapter;
import com.s2m.maatwerkproject.models.Chat;
import com.s2m.maatwerkproject.models.Group;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatInfoActivity extends AppCompatActivity implements IClickableGroup {

    private Chat chat;

    @BindView(R.id.textViewChatInfoChatName)
    TextView textViewChatName;
    @BindView(R.id.textViewChatInfoGroupCount)
    TextView textViewGroupCount;
    @BindView(R.id.imageViewChatInfoChatPicture)
    ImageView imageViewChatPicture;
    @BindView(R.id.recyclerViewChatMembers)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_info);
        ButterKnife.bind(this);

        chat = Parcels.unwrap(getIntent().getParcelableExtra(Chat.CHAT_MODEL_KEY));

        textViewChatName.setText(chat.getName());
        textViewGroupCount.setText(String.format("%S Groups", chat.getGroups().length));

        GroupListAdapter groupListAdapter = new GroupListAdapter(chat.getGroups(), this);
        recyclerView.setAdapter(groupListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @OnClick(R.id.imageViewChatInfoEditIcon)
    public void onClickImageViewChatInfoEditIcon(View view){
        Intent intent = new Intent(this, EditChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickGroupItem(Group group) {
        Intent intent = new Intent(this, GroupShortInfoActivity.class);
        startActivity(intent);
    }
}
