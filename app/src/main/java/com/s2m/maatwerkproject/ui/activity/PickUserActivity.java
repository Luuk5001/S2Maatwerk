package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.s2m.maatwerkproject.data.repository.IRepoCallback;
import com.s2m.maatwerkproject.data.repository.UserRepository;
import com.s2m.maatwerkproject.ui.adapter.ICheckableUser;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.ui.adapter.PickUserListAdapter;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.ui.view.EmptyRecyclerView;

import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickUserActivity extends AppCompatActivity implements ICheckableUser, IRepoCallback<User> {

    public static final int USER_REQUEST_CODE = 101;

    @BindView(R.id.recyclerViewPickUser)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewPickUserEmptyView)
    TextView emptyView;
    @BindView(R.id.textViewPickedUsers)
    TextView textViewPickedUsers;

    private List<User> users;
    private ArrayList<User> selectedUsers;
    private UserRepository userRepo;
    private PickUserListAdapter pickUserListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_user);
        ButterKnife.bind(this);

        users = new ArrayList<>();
        selectedUsers = new ArrayList<>();

        userRepo = new UserRepository(this);
        userRepo.searchUsers(null);

        pickUserListAdapter = new PickUserListAdapter(users, this);

        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(pickUserListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public void onCheckPickUserItem(User user) {
        if(selectedUsers.contains(user)){
            selectedUsers.remove(user);
        }
        else{
            selectedUsers.add(user);
        }
        updateTextViewPickedUsers();
    }

    private void updateTextViewPickedUsers() {
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
            Bundle bundle = new Bundle();
            bundle.putParcelable(User.USER_MODEL_KEY, Parcels.wrap(selectedUsers));
            data.putExtras(bundle);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override
    public void single(User obj, String callbackKey) {

    }

    @Override
    public void list(List<User> obj, String callbackKey) {
        if(callbackKey.equals(UserRepository.KEY_SEARCH_USERS)){
            users = obj;
            pickUserListAdapter.refreshData(users);
        }
    }

    @Override
    public void error() {

    }
}
