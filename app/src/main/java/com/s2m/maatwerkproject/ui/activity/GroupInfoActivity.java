package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.ui.adapters.UserListAdapter;
import com.s2m.maatwerkproject.data.models.Group;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupInfoActivity extends AppCompatActivity {

    @BindView(R.id.textViewGroupInfoGroupName)
    TextView textViewGroupName;
    @BindView(R.id.textViewGroupInfoLocation)
    TextView textViewLocation;
    @BindView(R.id.textViewGroupInfoUserCount)
    TextView textViewUserCount;
    @BindView(R.id.recyclerViewGroupMembers)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        ButterKnife.bind(this);

        Group group = Parcels.unwrap(getIntent().getParcelableExtra(Group.GROUP_MODEL_KEY));

        UserListAdapter userListAdapter = new UserListAdapter(group.getUsers());
        recyclerView.setAdapter(userListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        textViewGroupName.setText(group.getName());
        textViewLocation.setText(group.getLocation());
        textViewUserCount.setText(String.format("%S Users", String.valueOf(group.getUsers().length)));
    }

    @OnClick(R.id.imageViewGroupInfoEditIcon)
    public void onClickImageViewGroupInfoEditIcon(View view){
        Intent intent = new Intent(this, EditGroupActivity.class);
        startActivity(intent);
    }
}
