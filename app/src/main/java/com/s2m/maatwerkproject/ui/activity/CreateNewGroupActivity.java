package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.s2m.maatwerkproject.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_group);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonCreateGroupPickUsers)
    public void onClickButtonCreateGroupPickUsers(View view){
        Intent intent = new Intent(this, PickUserActivity.class);
        startActivity(intent);
    }
}
