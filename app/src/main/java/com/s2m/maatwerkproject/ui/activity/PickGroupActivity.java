package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.repository.GroupRepository;
import com.s2m.maatwerkproject.data.repository.IRepoCallback;
import com.s2m.maatwerkproject.ui.adapter.ICheckableGroup;
import com.s2m.maatwerkproject.ui.adapter.PickGroupListAdapter;
import com.s2m.maatwerkproject.ui.view.EmptyRecyclerView;
import com.s2m.maatwerkproject.utils.NonDuplicateList;

import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickGroupActivity extends AppCompatActivity implements ICheckableGroup, IRepoCallback<Group> {

    public static final int GROUP_REQUEST_CODE = 102;

    @BindView(R.id.recyclerViewPickGroup)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.textViewPickGroupEmptyView)
    TextView emptyView;
    @BindView(R.id.textViewPickedGroups)
    TextView textViewPickedGroups;

    private List<Group> groups;
    private List<Group> selectedGroups;
    private GroupRepository groupRepo;
    private PickGroupListAdapter pickGroupListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_group);
        ButterKnife.bind(this);

        groups = new NonDuplicateList<>();
        selectedGroups = new NonDuplicateList<>();

        groupRepo = new GroupRepository(this);
        groupRepo.searchGroups(null);

        pickGroupListAdapter = new PickGroupListAdapter(groups, this);

        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(pickGroupListAdapter);
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
    public void onClickImageViewShowGroupInfo(Group group) {
        Intent intent = new Intent(this, GroupShortInfoActivity.class);
        intent.putExtra(Group.GROUP_MODEL_KEY, Parcels.wrap(group));
        startActivity(intent);
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
            Bundle bundle = new Bundle();
            bundle.putParcelable(Group.GROUP_MODEL_KEY, Parcels.wrap(selectedGroups));
            data.putExtras(bundle);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override
    public void single(Group obj, String callKey) {

    }

    @Override
    public void list(List<Group> obj, String callKey) {
        if(callKey.equals(GroupRepository.KEY_SEARCH_GROUPS)){
            groups = obj;
            pickGroupListAdapter.refreshData(groups);
        }
    }

    @Override
    public void error() {

    }
}
