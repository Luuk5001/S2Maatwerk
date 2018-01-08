package com.s2m.maatwerkproject.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.User;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupListItemViewHolder> implements IUpdatableAdapter<Group> {

    private List<Group> groups;
    private final IClickableGroup listener;

    public GroupListAdapter(List<Group> groups, IClickableGroup listener){
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
        holder.bindGroupListItem(groups.get(position));
    }

    @Override
    public int getItemCount() {
        return groups == null ? 0 : groups.size();
    }

    @Override
    public void refreshData(List<Group> data) {
        groups.clear();
        groups.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void addItem(Group item) {
        groups.add(item);
        notifyDataSetChanged();
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
                String[] userNames = new String[group.getUsers().size()];
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

    private void onGroupSelected(Group group){
        listener.onClickGroupItem(group);
    }
}
