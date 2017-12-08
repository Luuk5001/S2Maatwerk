package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.s2m.maatwerkproject.ICheckableGroup;
import com.s2m.maatwerkproject.IClickableGroup;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.adapters.GroupListAdapter;
import com.s2m.maatwerkproject.adapters.PickGroupListAdapter;
import com.s2m.maatwerkproject.models.Group;
import com.s2m.maatwerkproject.testData;
import com.s2m.maatwerkproject.utils.EmptyRecyclerView;

import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickGroupActivity extends AppCompatActivity implements ICheckableGroup {

    //TODO get groups that user searched
    private Group[] groups = testData.groups;
    private ArrayList<Group> selectedGroups;

    @BindView(R.id.recyclerViewPickGroup)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewPickGroupEmptyView)
    TextView emptyView;
    @BindView(R.id.textViewPickedGroups)
    TextView textViewPickedGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_group);
        ButterKnife.bind(this);

        selectedGroups = new ArrayList<>();

        recyclerView.setEmptyView(emptyView);
        PickGroupListAdapter pickGroupListAdapter = new PickGroupListAdapter(groups, this);
        recyclerView.setAdapter(pickGroupListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onCheckPickGroupItem(Group group) {
        if(selectedGroups.contains(group)){
            selectedGroups.remove(group);
        }
        else{
            selectedGroups.add(group);
        }
        updateTextViewPickedGroups();
    }

    private void updateTextViewPickedGroups() {
        int i = 0;
        String[] groupNames = new String[selectedGroups.size()];
        for (Group group : selectedGroups){
            groupNames[i] = group.getName();
            i++;
        }
        textViewPickedGroups.setText(StringUtils.join(groupNames, ", "));
    }

    @OnClick(R.id.buttonSavePickedGroups)
    public void onClickButtonSavePickedGroups(View view){
        if(selectedGroups.size() <= 0){
            Toast.makeText(this, "You have to pick at least one group", Toast.LENGTH_LONG).show();
        }
        else{
            Intent data = new Intent();
            data.putExtra(Group.GROUP_MODEL_KEY, Parcels.wrap(selectedGroups));
            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override
    public void onClickImageViewShowGroupInfo(Group group) {
        Intent intent = new Intent(this, GroupShortInfoActivity.class);
        intent.putExtra(Group.GROUP_MODEL_KEY, Parcels.wrap(group));
        startActivity(intent);
    }
}
