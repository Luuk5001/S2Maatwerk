package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.s2m.maatwerkproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNewChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_chat);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonCreateChatPickGroups)
    public void onClickButtonCreateChatPickGroups(View view){
        Intent intent = new Intent(this, PickGroupActivity.class);
        startActivity(intent);
    }
}
