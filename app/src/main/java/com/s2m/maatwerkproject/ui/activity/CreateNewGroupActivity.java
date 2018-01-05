package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.data.repository.GroupRepository;
import com.s2m.maatwerkproject.data.repository.IRepoCallback;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNewGroupActivity extends AppCompatActivity implements IRepoCallback<Group>{

    @BindView(R.id.editTextCreateGroupName)
    EditText editTextGroupName;
    @BindView(R.id.editTextCreateGroupDescription)
    EditText editTextGroupDescription;
    @BindView(R.id.editTextCreateGroupLocation)
    EditText editTextGroupLocation;

    private GroupRepository groupRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_group);
        ButterKnife.bind(this);

        groupRepo = new GroupRepository(this);
    }

    @OnClick(R.id.buttonCreateGroupPickUsers)
    public void onClickButtonCreateGroupPickUsers(View view){
        Intent intent = new Intent(this, PickUserActivity.class);
        startActivityForResult(intent, PickUserActivity.USER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PickUserActivity.USER_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                List<User> users = Parcels.unwrap(bundle.getParcelable(User.USER_MODEL_KEY));
                createGroup(users);
            }
        }
    }

    private void createGroup(List<User> users) {
        Group group = new Group(editTextGroupName.getText().toString(),
                editTextGroupDescription.getText().toString(),
                editTextGroupLocation.getText().toString(),
                users);
        groupRepo.createGroup(group);
    }

    @Override
    public void single(Group obj, String callbackKey) {

    }

    @Override
    public void list(List<Group> obj, String callbackKey) {

    }

    @Override
    public void error() {

    }
}
