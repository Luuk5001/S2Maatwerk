package com.s2m.maatwerkproject.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListItemViewHolder> {

    private List<User> users;

    public UserListAdapter(List<User> users){
        this.users = users;
    }

    @Override
    public UserListAdapter.UserListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_list, parent, false);
        return new UserListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserListAdapter.UserListItemViewHolder holder, int position) {
        holder.bindUser(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserListItemViewHolder extends RecyclerView.ViewHolder {

        //TODO set the on click listener
        @BindView(R.id.textViewUserListName)
        TextView textViewUserName;
        @BindView(R.id.textViewUserListId)
        TextView textViewUserId;

        UserListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindUser(User user){
            textViewUserName.setText(user.getName());
            textViewUserId.setText(user.getId());
        }
    }
}
