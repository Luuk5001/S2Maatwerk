package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.repository.GroupRepository;
import com.s2m.maatwerkproject.data.repository.IRepoCallback;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditGroupActivity extends ValidationActivity implements IRepoCallback<Group> {

    public static final String TAG = EditGroupActivity.class.getSimpleName();

    @Length(min = 2, max = 50, message = "Must be between 2 en 50 characters long")
    @BindView(R.id.editTextEditGroupName)
    EditText editTextGroupName;
    @Length(max = 1000, message = "Cannot be longer than 1000 characters")
    @BindView(R.id.editTextEditGroupDescription)
    EditText editTextGroupDescription;
    @NotEmpty
    @BindView(R.id.editTextEditGroupLocation)
    EditText editTextGroupLocation;
    @BindView(R.id.buttonEditGroupSave)
    Button buttonSave;

    private Group group;
    private boolean buttonClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);
        ButterKnife.bind(this);

        buttonClicked = false;
        editTextGroupName.addTextChangedListener(this);
        editTextGroupDescription.addTextChangedListener(this);

        group = Parcels.unwrap(getIntent().getParcelableExtra(Group.GROUP_MODEL_KEY));

        if(group == null){
            Log.e(TAG, "supplied group was null, activity closed");
            finish();
        }

        updateUI();
    }

    @Override
    public void validationSuccess() {
        buttonSave.setEnabled(true);
        if(buttonClicked){
            buttonSave.setEnabled(false);
            group.setName(editTextGroupName.getText().toString());
            group.setDescription(editTextGroupDescription.getText().toString());
            group.setLocation(editTextGroupLocation.getText().toString());
            GroupRepository groupRepository = new GroupRepository(this);
            groupRepository.updateGroup(group);
        }
    }

    @Override
    public void validationFailure() {
        buttonSave.setEnabled(false);
        buttonClicked = false;
    }

    @OnClick(R.id.buttonEditGroupSave)
    public void onClickButtonEditGroupSave(View view) {
        buttonClicked = true;
        validator.validate();
    }

    @Override
    public void single(Group obj, String callKey) {
        if(callKey.equals(GroupRepository.KEY_GROUP_UPDATED)){
            Intent intent = new Intent();
            intent.putExtra(Group.GROUP_MODEL_KEY, Parcels.wrap(group));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void list(List<Group> obj, String callKey) {

    }

    @Override
    public void error(String errorMessage) {

    }

    private void updateUI() {
        editTextGroupName.setText(group.getName());
        editTextGroupDescription.setText(group.getDescription());
        editTextGroupLocation.setText(group.getLocation());
    }
}
