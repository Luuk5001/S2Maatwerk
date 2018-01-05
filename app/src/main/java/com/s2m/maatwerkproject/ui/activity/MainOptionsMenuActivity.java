package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.s2m.maatwerkproject.R;


public abstract class MainOptionsMenuActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menuMainSettings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.menuMainCreateChat:
                intent = new Intent(this, CreateNewChatActivity.class);
                startActivity(intent);
                break;
            case R.id.menuMainCreateGroup:
                intent = new Intent(this, CreateNewGroupActivity.class);
                startActivity(intent);
                break;
            case  R.id.menuMainSingOut:
                singOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void singOut(){
        AuthUI.getInstance().signOut(this);
        Intent intent = new Intent(this, SingInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
