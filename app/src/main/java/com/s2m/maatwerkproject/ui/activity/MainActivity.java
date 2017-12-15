package com.s2m.maatwerkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.ui.fragment.ChatListFragment;
import com.s2m.maatwerkproject.ui.fragment.GroupListFragment;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int RC_SING_IN = 101;
    private FirebaseAuth firebaseAuth;
    private boolean singInActivityCalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(this);
    }

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

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user == null && !singInActivityCalled){
            singInActivityCalled = true;
            onSingedOutCleanup();
            startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(
                                    Arrays.asList(
                                            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()))
                            .build(),
                    RC_SING_IN);
        }
        else if(user != null){
            onSignedInInitialize(user.getDisplayName());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RC_SING_IN:
                if(resultCode == RESULT_OK){

                }
                else{
                    finish();
                }
                break;
        }
    }

    private void configureLayout(){
        final ChatListFragment chatListFragment = new ChatListFragment();
        final GroupListFragment groupListFragment = new GroupListFragment();

        ViewPager viewPager = findViewById(R.id.viewPagerMain);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position == 0 ? chatListFragment : groupListFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "Chats" : "Groups";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        TabLayout tabLayout = findViewById(R.id.tabLayoutMain);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void singOut(){
        singInActivityCalled = false;
        AuthUI.getInstance().signOut(this);
    }

    private void onSignedInInitialize(String displayName) {
        setContentView(R.layout.activity_main);
        configureLayout();
    }

    private void onSingedOutCleanup() {
        setContentView(R.layout.logo);
    }
}
