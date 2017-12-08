package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.s2m.maatwerkproject.OnGroupSelectedInterface;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.adapters.GroupListAdapter;
import com.s2m.maatwerkproject.models.Group;
import com.s2m.maatwerkproject.testData;
import com.s2m.maatwerkproject.utils.EmptyRecyclerView;

import org.parceler.Parcel;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.s2m.maatwerkproject.testData.groups;

public class PickGroupActivity extends AppCompatActivity implements OnGroupSelectedInterface{

    //TODO get groups that user searched
    private Group[] groups = testData.groups;

    @BindView(R.id.recyclerViewPickGroup)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewPickGroupEmptyView)
    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_group);
        ButterKnife.bind(this);

        recyclerView.setEmptyView(emptyView);
        GroupListAdapter chatListAdapter = new GroupListAdapter(groups, this);
        recyclerView.setAdapter(chatListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onGroupSelected(Group group) {
        Intent intent = new Intent(this, GroupShortInfoActivity.class);
        intent.putExtra(Group.GROUP_MODEL_KEY, Parcels.wrap(group));
        startActivity(intent);
    }
}
