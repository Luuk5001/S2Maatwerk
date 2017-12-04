package com.s2m.maatwerkproject.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.ui.fragment.AccountSettingsFragment;
import com.s2m.maatwerkproject.ui.fragment.ChatSettingsFragment;
import com.s2m.maatwerkproject.ui.fragment.NotificationSettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    public static final String[] SETTINGS_FRAGMENTS_NAMES = new String[]{"Account", "Chats", "Notifications"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final AccountSettingsFragment accountSettingsFragment = new AccountSettingsFragment();
        final ChatSettingsFragment chatSettingsFragment = new ChatSettingsFragment();
        final NotificationSettingsFragment notificationSettingsFragment = new NotificationSettingsFragment();

        ViewPager viewPager = findViewById(R.id.viewPagerSettings);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //TODO appropriate exception on default
                switch (position){
                    case 0:
                        return accountSettingsFragment;
                    case 1:
                        return chatSettingsFragment;
                    case 2:
                        return notificationSettingsFragment;
                    default:
                        return null;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return SETTINGS_FRAGMENTS_NAMES[position];
            }

            @Override
            public int getCount() {
                return SETTINGS_FRAGMENTS_NAMES.length;
            }
        });

        TabLayout tabLayout = findViewById(R.id.tabLayoutSettings);

        tabLayout.setupWithViewPager(viewPager);
    }
}
