package com.s2m.maatwerkproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.models.Group;
import com.s2m.maatwerkproject.models.User;
import com.s2m.maatwerkproject.ui.fragment.GroupListFragment;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupListItemViewHolder> {

    private Group[] groups;
    private final GroupListFragment.OnGroupSelectedInterface listener;

    public GroupListAdapter(Group[] groups, GroupListFragment.OnGroupSelectedInterface listener){
        this.groups = groups;
        this.listener = listener;
    }

    @Override
    public GroupListAdapter.GroupListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group_list, parent, false);
        GroupListAdapter.GroupListItemViewHolder viewHolder = new GroupListAdapter.GroupListItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroupListAdapter.GroupListItemViewHolder holder, int position) {
        holder.bindGroupListItem(groups[position]);
    }

    @Override
    public int getItemCount() {
        return groups.length;
    }

    public class GroupListItemViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{

        @BindView(R.id.imageViewGroupListImage)
        ImageView imageViewGroupImage;
        @BindView(R.id.textViewGroupListName)
        TextView textViewGroupName;
        @BindView(R.id.textViewGroupListUsers)
        TextView textViewGroupUsers;

        private Group group;

        GroupListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindGroupListItem(Group group){
            //TODO bind remaining data
            //TODO do not allow nulls in user array
            //TODO remove users own name from group users
            this.group = group;
            textViewGroupUsers.setText("");
            textViewGroupName.setText(group.getName());
            if(group.getUsers() != null){
                int i = 0;
                String[] userNames = new String[group.getUsers().length];
                for(User user : group.getUsers()){
                    userNames[i] = user.getName();
                    i++;
                }
                textViewGroupUsers.setText(StringUtils.join(userNames, ", "));
            }
        }

        @Override
        public void onClick(View view) {
            onGroupSelected(group);
        }
    }

    private void onGroupSelected(Group group) {
        listener.onGroupSelected(group);
    }
}
