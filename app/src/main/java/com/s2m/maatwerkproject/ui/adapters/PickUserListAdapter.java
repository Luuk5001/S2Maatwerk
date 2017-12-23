package com.s2m.maatwerkproject.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickUserListAdapter extends RecyclerView.Adapter<PickUserListAdapter.PickUserListItemViewHolder> {
    private User[] users;
    private final ICheckableUser listener;

    public PickUserListAdapter(User[] users, ICheckableUser listener){
        this.users = users;
        this.listener = listener;
    }

    @Override
    public PickUserListAdapter.PickUserListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pick_user_list, parent, false);
        PickUserListAdapter.PickUserListItemViewHolder viewHolder = new PickUserListAdapter.PickUserListItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PickUserListAdapter.PickUserListItemViewHolder holder, int position) {
        holder.bindUserListItem(users[position]);
    }

    @Override
    public int getItemCount() {
        return users.length;
    }

    public class PickUserListItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @BindView(R.id.textViewPickUserName)
        TextView textViewPickUserName;
        @BindView(R.id.checkBoxPickUser)
        CheckBox checkBoxPickUser;

        private User user;

        PickUserListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            checkBoxPickUser.setOnClickListener(this);
        }

        void bindUserListItem(User user){
            this.user = user;
            textViewPickUserName.setText(user.getName());
        }

        @Override
        public void onClick(View view) {
            if(view.getId() != R.id.checkBoxPickUser){
                checkBoxPickUser.toggle();
            }
            onCheckPickUserItem(user);
        }
    }

    private void onCheckPickUserItem(User user){
        listener.onCheckPickUserItem(user);
    }
}
