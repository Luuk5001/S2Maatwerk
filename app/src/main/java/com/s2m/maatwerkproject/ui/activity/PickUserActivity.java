package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.s2m.maatwerkproject.ICheckableUser;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.adapters.PickGroupListAdapter;
import com.s2m.maatwerkproject.adapters.PickUserListAdapter;
import com.s2m.maatwerkproject.models.Group;
import com.s2m.maatwerkproject.models.User;
import com.s2m.maatwerkproject.testData;
import com.s2m.maatwerkproject.utils.EmptyRecyclerView;

import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickUserActivity extends AppCompatActivity implements ICheckableUser {

    //TODO get users that user searched
    private User[] users = testData.users;
    private ArrayList<User> selectedUsers;

    @BindView(R.id.recyclerViewPickUser)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewPickUserEmptyView)
    TextView emptyView;
    @BindView(R.id.textViewPickedUsers)
    TextView textViewPickedUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_user);
        ButterKnife.bind(this);

        selectedUsers = new ArrayList<>();

        recyclerView.setEmptyView(emptyView);
        PickUserListAdapter pickGroupListAdapter = new PickUserListAdapter(users, this);
        recyclerView.setAdapter(pickGroupListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void updateTextViewPickedGroups() {
        int i = 0;
        String[] userNames = new String[selectedUsers.size()];
        for (User user : selectedUsers){
            userNames[i] = user.getName();
            i++;
        }
        textViewPickedUsers.setText(StringUtils.join(userNames, ", "));
    }

    @OnClick(R.id.buttonSavePickedUsers)
    public void onClickButtonSavePickedUsers(View view){
        if(selectedUsers.size() <= 0){
            Toast.makeText(this, "You have to pick at least one user", Toast.LENGTH_LONG).show();
        }
        else{
            Intent data = new Intent();
            data.putExtra(User.USER_MODEL_KEY, Parcels.wrap(selectedUsers));
            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override
    public void onCheckPickUserItem(User user) {
        if(selectedUsers.contains(user)){
            selectedUsers.remove(user);
        }
        else{
            selectedUsers.add(user);
        }
        updateTextViewPickedGroups();
    }
}
