package com.s2m.maatwerkproject.ui.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.User;
import com.s2m.maatwerkproject.data.Firebase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListItemViewHolder> implements UpdatableAdapterInterface<User> {

    public interface UserListListener {
        void onDeleteIconClick(User user);
    }

    private List<User> users;
    private UserListListener listener;

    public UserListAdapter(List<User> users, UserListListener listener){
        this.users = users;
        this.listener = listener;
    }

    @Override
    public void refreshData(List<User> data) {
        users = data;
        notifyDataSetChanged();
    }

    @Override
    public void addItem(User item) {

    }

    @Override
    public void removeItem(User item) {

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
        return users == null ? 0 : users.size();
    }

    class UserListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textViewUserListName)
        TextView textViewUserName;
        @BindView(R.id.imageViewUserListDeleteIcon)
        ImageView imageViewDeleteUser;

        private User user;

        UserListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindUser(User user){
            this.user = user;
            if(user.getId().equals(Firebase.getAuthInstance().getCurrentUser().getUid())){
                textViewUserName.setText("You");
            }
            else{
                textViewUserName.setText(user.getName());
                imageViewDeleteUser.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            listener.onDeleteIconClick(user);
        }
    }
}
