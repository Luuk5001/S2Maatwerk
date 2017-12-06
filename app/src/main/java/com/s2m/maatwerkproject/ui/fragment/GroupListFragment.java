package com.s2m.maatwerkproject.ui.fragment;

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
import com.s2m.maatwerkproject.models.Group;
import com.s2m.maatwerkproject.utils.EmptyRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.s2m.maatwerkproject.testData.groups;

public class GroupListFragment extends Fragment {

    public interface OnGroupSelectedInterface{
        void onGroupSelected(Group group);
    }

    @BindView(R.id.recyclerViewGroupList)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewNoGroups)
    TextView emptyView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        OnGroupSelectedInterface listener = (OnGroupSelectedInterface) getActivity();
        View view = inflater.inflate(R.layout.fragment_group_list, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setEmptyView(emptyView);
        GroupListAdapter chatListAdapter = new GroupListAdapter(groups, listener);
        recyclerView.setAdapter(chatListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }
}
