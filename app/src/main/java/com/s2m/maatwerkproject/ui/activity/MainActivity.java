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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.data.repository.IRepoCallback;
import com.s2m.maatwerkproject.data.repository.UserRepository;
import com.s2m.maatwerkproject.ui.fragment.ChatListFragment;
import com.s2m.maatwerkproject.ui.fragment.GroupListFragment;
import com.s2m.maatwerkproject.data.Firebase;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends MainOptionsMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
