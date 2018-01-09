package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.repository.ChatRepository;
import com.s2m.maatwerkproject.data.repository.RepositoryCallback;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNewChatActivity extends AppCompatActivity implements RepositoryCallback<Chat> {

    @BindView(R.id.editTextCreateChatName)
    EditText editTextChatName;
    @BindView(R.id.imageViewCreateChatPicture)
    ImageView imageViewChatPicture;

    private ChatRepository chatRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_chat);
        ButterKnife.bind(this);

        chatRepo = new ChatRepository(this);
    }

    @OnClick(R.id.buttonCreateChatPickGroups)
    public void onClickButtonCreateChatPickGroups(View view){
        Intent intent = new Intent(this, PickGroupActivity.class);
        startActivityForResult(intent, PickGroupActivity.GROUP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PickGroupActivity.GROUP_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                List<Group> groups = Parcels.unwrap(bundle.getParcelable(Group.GROUP_MODEL_KEY));
                createChat(groups);
            }
        }
    }

    private void createChat(List<Group> groups) {
        Chat chat = new Chat(editTextChatName.getText().toString(),
                null,null);
        chatRepo.createChat(chat, groups);
    }

    @Override
    public void single(Chat obj, String callKey) {

    }

    @Override
    public void list(List<Chat> obj, String callKey) {

    }

    @Override
    public void error(String errorMessage) {

    }
}
