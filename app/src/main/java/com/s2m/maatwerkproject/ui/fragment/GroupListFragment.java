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
import com.s2m.maatwerkproject.adapters.GroupListAdapter;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.ui.activity.GroupInfoActivity;
import com.s2m.maatwerkproject.IClickableGroup;
import com.s2m.maatwerkproject.utils.EmptyRecyclerView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.s2m.maatwerkproject.testData.groups;

public class GroupListFragment extends Fragment implements IClickableGroup {

    @BindView(R.id.recyclerViewGroupList)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewNoGroups)
    TextView emptyView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_list, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setEmptyView(emptyView);
        GroupListAdapter chatListAdapter = new GroupListAdapter(groups, this);
        recyclerView.setAdapter(chatListAdapter);
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
}
