package com.s2m.maatwerkproject.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.Firebase;
import com.s2m.maatwerkproject.data.models.Group;
import com.s2m.maatwerkproject.data.models.User;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupListItemViewHolder> implements UpdatableAdapterInterface<Group> {

    public interface GroupListListener {
        void onClickGroupItem(Group group);
    }

    private List<Group> groups;
    private final GroupListListener listener;

    public GroupListAdapter(List<Group> groups, GroupListListener listener){
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

    @Override
    public void removeItem(Group item) {
        groups.remove(item);
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
