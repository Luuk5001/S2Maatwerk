package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.data.repository.GroupRepository;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNewGroupActivity extends ValidationActivity implements GroupRepository.GroupRepositoryCallback {

    private static final int RC_PICKED_USERS = 101;

    @Length(min = 2, max = 50, message = "Must be between 2 en 50 characters long")
    @BindView(R.id.editTextCreateGroupName)
    EditText editTextGroupName;
    @Length(max = 1000, message = "Cannot be longer than 1000 characters")
    @BindView(R.id.editTextCreateGroupDescription)
    EditText editTextGroupDescription;
    @NotEmpty
    @BindView(R.id.editTextCreateGroupLocation)
    EditText editTextGroupLocation;
    @BindView(R.id.buttonCreateGroupPickUsers)
    Button buttonPickUsers;

    private GroupRepository groupRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_group);
        ButterKnife.bind(this);

        buttonPickUsers.setEnabled(false);
        editTextGroupName.addTextChangedListener(this);
        editTextGroupDescription.addTextChangedListener(this);
        editTextGroupLocation.addTextChangedListener(this);

        groupRepo = new GroupRepository(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_PICKED_USERS){
            if(resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                List<User> users = Parcels.unwrap(bundle.getParcelable(User.USER_MODEL_KEY));
                users.add(new User(Firebase.getAuthInstance().getCurrentUser().getUid(), null));
                Group group = new Group(editTextGroupName.getText().toString(),
                        editTextGroupDescription.getText().toString(),
                        editTextGroupLocation.getText().toString(),
                        users, null);
                groupRepo.createGroup(group);
            }
        }
    }

    @Override
    public void validationSuccess() {
        buttonPickUsers.setEnabled(true);
    }

    @Override
    public void validationFailure() {
        buttonPickUsers.setEnabled(false);
    }


    @Override
    public void singleGroup(Group obj, String callKey) {
        if(callKey.equals(GroupRepository.KEY_CREATE_GROUP)){
            finish();
        }
    }

    @Override
    public void groupList(List<Group> obj, String callbackKey) {

    }

    @Override
    public void error(String errorMessage) {

    }

    @OnClick(R.id.buttonCreateGroupPickUsers)
    public void onClickButtonCreateGroupPickUsers(View view){
        Intent intent = new Intent(this, PickUserActivity.class);
        startActivityForResult(intent, RC_PICKED_USERS);
    }
}
