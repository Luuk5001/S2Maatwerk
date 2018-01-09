package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.repository.GroupRepository;
import com.s2m.maatwerkproject.data.repository.RepositoryCallback;
import com.s2m.maatwerkproject.ui.adapter.UserListAdapter;
import com.s2m.maatwerkproject.data.models.Group;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupInfoActivity extends AppCompatActivity implements UserListAdapter.UserListListener, RepositoryCallback<Group> {

    private static final int RC_UPDATED_GROUP = 101;
    private static final int RC_PICKED_USERS = 102;

    @BindView(R.id.textViewGroupInfoGroupName)
    TextView textViewGroupName;
    @BindView(R.id.textViewGroupInfoLocation)
    TextView textViewLocation;
    @BindView(R.id.textViewGroupInfoUserCount)
    TextView textViewUserCount;
    @BindView(R.id.recyclerViewGroupMembers)
    RecyclerView recyclerView;

    private Group group;
    private GroupRepository groupRepository;
    private UserListAdapter userListAdapter;
    private boolean leavingGroup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        ButterKnife.bind(this);

        group = Parcels.unwrap(getIntent().getParcelableExtra(Group.GROUP_MODEL_KEY));

        groupRepository = new GroupRepository(this);

        userListAdapter = new UserListAdapter(group.getUsers(), this);

        recyclerView.setAdapter(userListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        updateUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RC_UPDATED_GROUP:
                if(resultCode == RESULT_OK){
                    group = Parcels.unwrap(data.getExtras().getParcelable(Group.GROUP_MODEL_KEY));
                    updateUI();
                }
                break;
            case RC_PICKED_USERS:
                if(resultCode == RESULT_OK){
                    List<User> users = Parcels.unwrap(data.getExtras().getParcelable(User.USER_MODEL_KEY));
                    Group tempGroup = group;
                    for (User user : users) {
                        tempGroup.addUser(user);
                    }
                    groupRepository.updateGroup(group);
                }
                break;
        }
    }

    @Override
    public void onDeleteIconClick(User user) {
        Group tempGroup = group;
        tempGroup.removeUser(user);
        groupRepository.updateGroup(tempGroup);
    }

    @Override
    public void single(Group obj, String callKey) {
        if(callKey.equals(GroupRepository.KEY_GROUP_UPDATED)){
            if(leavingGroup){
                finish();
            }
            else{
                this.group = obj;
                userListAdapter.refreshData(obj.getUsers());
                updateUI();
            }
        }
    }

    @Override
    public void list(List<Group> obj, String callKey) {

    }

    @Override
    public void error(String errorMessage) {
        leavingGroup = false;
    }

    @OnClick(R.id.imageViewGroupInfoEditIcon)
    public void onClickImageViewGroupInfoEditIcon(View view){
        Intent intent = new Intent(this, EditGroupActivity.class);
        intent.putExtra(Group.GROUP_MODEL_KEY, Parcels.wrap(group));
        startActivityForResult(intent, RC_UPDATED_GROUP);
    }

    @OnClick(R.id.buttonGroupInfoAdd)
    public void onClickButtonGroupInfoAdd(View view){
        Intent intent = new Intent(this, PickUserActivity.class);
        intent.putExtra(User.USER_MODEL_KEY, Parcels.wrap(group.getUsers()));
        startActivityForResult(intent, RC_PICKED_USERS);
    }

    @OnClick(R.id.buttonGroupInfoLeave)
    public void onClickButtonGroupInfoLeave(View view){
        User tempUser = new User();
        Group tempGroup = group;
        tempUser.setId(Firebase.getAuthInstance().getCurrentUser().getUid());
        tempGroup.removeUser(tempUser);
        groupRepository.updateGroup(tempGroup);
        leavingGroup = true;
    }

    private void updateUI(){
        textViewGroupName.setText(group.getName());
        textViewLocation.setText(group.getLocation());
        textViewUserCount.setText(String.format("%S Users", String.valueOf(group.getUsers().size())));
    }
}
