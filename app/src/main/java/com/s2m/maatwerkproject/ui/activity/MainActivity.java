package com.s2m.maatwerkproject.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.repository.GroupRepository;
import com.s2m.maatwerkproject.data.repository.RepositoryCallback;
import com.s2m.maatwerkproject.ui.fragment.ChatListFragment;
import com.s2m.maatwerkproject.ui.fragment.GroupListListenerListFragment;
import com.s2m.maatwerkproject.utils.NonDuplicateList;

import java.util.List;

public class MainActivity extends MainOptionsMenuActivity implements RepositoryCallback<Group> {

    private List<Group> groups;
    private ChatListFragment chatListFragment;
    private GroupListListenerListFragment groupListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groups = new NonDuplicateList<>();

        GroupRepository groupRepo = new GroupRepository(this);
        groupRepo.setChildEventListener(Firebase.getAuthInstance().getCurrentUser().getUid());

        chatListFragment = new ChatListFragment();
        groupListFragment = new GroupListListenerListFragment();

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
    public void single(Group obj, String callKey) {
        if(callKey.equals(GroupRepository.KEY_GROUP_LISTENER)){
            groupListFragment.addGroup(obj);
        }
        else if(callKey.equals(GroupRepository.KEY_GROUP_DELETED)){
            groupListFragment.removeGroup(obj);
        }
    }

    @Override
    public void list(List obj, String callKey) {

    }

    @Override
    public void error(String errorMessage) {

    }
}
