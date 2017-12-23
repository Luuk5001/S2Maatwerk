package com.s2m.maatwerkproject.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.Group;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupShortInfoActivity extends AppCompatActivity {

    @BindView(R.id.textViewGroupShortInfoName)
    TextView textViewName;
    @BindView(R.id.textViewGroupShortInfoDescription)
    TextView textViewDescription;
    @BindView(R.id.textViewGroupShortInfoLocation)
    TextView textViewLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_short_info);
        ButterKnife.bind(this);

        Group group = Parcels.unwrap(getIntent().getParcelableExtra(Group.GROUP_MODEL_KEY));

        textViewName.setText(group.getName());
        textViewDescription.setText(group.getDescription());
        textViewLocation.setText(group.getLocation());
    }
}
