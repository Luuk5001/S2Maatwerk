package com.s2m.maatwerkproject.UI.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.UI.Fragment.ChatListFragment;
import com.s2m.maatwerkproject.UI.Fragment.GroupListFragment;

public class MainActivity extends AppCompatActivity {

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
