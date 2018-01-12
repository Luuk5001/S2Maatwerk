package com.s2m.maatwerkproject.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.data.repository.GroupRepository;
import com.s2m.maatwerkproject.data.repository.UserRepository;
import com.s2m.maatwerkproject.ui.fragment.ChatListFragment;
import com.s2m.maatwerkproject.ui.fragment.GroupListFragment;

import java.util.List;

public class MainActivity extends MainOptionsMenuActivity implements GroupRepository.GroupRepositoryCallback, UserRepository.UserRepositoryCallback {

    private ChatListFragment chatListFragment;
    private GroupListFragment groupListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroupRepository groupRepo = new GroupRepository(this);
        groupRepo.getMyGroups(Firebase.getAuthInstance().getCurrentUser().getUid());

        chatListFragment = new ChatListFragment();
        groupListFragment = new GroupListFragment();

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

    @Override
    public void singleUser(User user, String callKey) {

    }

    @Override
    public void userList(List<User> users, String callKey) {

    }

    @Override
    public void singleGroup(Group group, String callKey) {
        groupListFragment.addGroup(group);
        chatListFragment.addGroup(group);
    }

    @Override
    public void groupList(List<Group> groups, String callKey) {

    }

    @Override
    public void error(String errorMessage, String callKey) {

    }
}
