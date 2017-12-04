package com.s2m.maatwerkproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2m.maatwerkproject.models.Group;
import com.s2m.maatwerkproject.models.User;
import com.s2m.maatwerkproject.R;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupListItemViewHolder> {

    private Group[] groups;

    public GroupListAdapter(Group[] groups){
        this.groups = groups;
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

    public class GroupListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewGroupListImage)
        ImageView imageViewGroupImage;
        @BindView(R.id.textViewGroupListName)
        TextView textViewGroupName;
        @BindView(R.id.textViewGroupListUsers)
        TextView textViewGroupUsers;

        public GroupListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindGroupListItem(Group group){
            //TODO bind remaining data
            //TODO do not allow nulls in user array
            //TODO remove users own name from group users
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
    }
}
