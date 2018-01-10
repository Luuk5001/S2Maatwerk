package com.s2m.maatwerkproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.data.repository.ChatRepository;
import com.s2m.maatwerkproject.data.repository.GroupRepository;
import com.s2m.maatwerkproject.data.repository.RepositoryCallback;
import com.s2m.maatwerkproject.ui.activity.GroupInfoActivity;
import com.s2m.maatwerkproject.ui.activity.MainActivity;
import com.s2m.maatwerkproject.ui.adapter.GroupListAdapter;
import com.s2m.maatwerkproject.ui.view.EmptyRecyclerView;
import com.s2m.maatwerkproject.utils.NonDuplicateList;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupListFragment extends Fragment implements GroupListAdapter.GroupListListener {

    @BindView(R.id.recyclerViewGroupList)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewNoGroups)
    TextView emptyView;

    private GroupListAdapter groupListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_list, container, false);
        ButterKnife.bind(this, view);

        groupListAdapter = new GroupListAdapter(new NonDuplicateList<Group>(), this);

        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(groupListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    @Override
    public void onClickGroupItem(Group group) {
        Intent intent = new Intent(getContext(), GroupInfoActivity.class);
        intent.putExtra(Group.GROUP_MODEL_KEY, Parcels.wrap(group));
        startActivity(intent);
    }

    public void addGroup(Group group){
        groupListAdapter.addItem(group);
    }
}
