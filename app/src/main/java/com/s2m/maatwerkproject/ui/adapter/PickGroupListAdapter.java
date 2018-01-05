package com.s2m.maatwerkproject.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.Group;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickGroupListAdapter extends RecyclerView.Adapter<PickGroupListAdapter.PickGroupListItemViewHolder> implements IUpdatableAdapter<Group> {

    private List<Group> groups;
    private final ICheckableGroup listener;

    public PickGroupListAdapter(List<Group> groups, ICheckableGroup listener){
        this.groups = groups;
        this.listener = listener;
    }

    @Override
    public PickGroupListAdapter.PickGroupListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pick_group_list, parent, false);
        PickGroupListAdapter.PickGroupListItemViewHolder viewHolder = new PickGroupListAdapter.PickGroupListItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PickGroupListAdapter.PickGroupListItemViewHolder holder, int position) {
        holder.bindGroupListItem(groups.get(position));
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    @Override
    public void refreshData(List<Group> data) {
        groups.clear();
        groups.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void addItem(Group item) {

    }

    public class PickGroupListItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @BindView(R.id.textViewPickGroupName)
        TextView textViewGroupName;
        @BindView(R.id.textViewPickGroupLocation)
        TextView textViewGroupLocation;
        @BindView(R.id.checkBoxPickGroup)
        CheckBox checkBoxPickGroup;
        @BindView(R.id.imageViewPickGroupShowInfo)
        ImageView imageViewPickGroupShowInfo;

        private Group group;

        PickGroupListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            checkBoxPickGroup.setOnClickListener(this);
            imageViewPickGroupShowInfo.setOnClickListener(this);
        }

        void bindGroupListItem(Group group){
            this.group = group;
            textViewGroupLocation.setText(group.getLocation());
            textViewGroupName.setText(group.getName());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.checkBoxPickGroup:
                    onCheckPickGroupItem(group);
                    break;
                case R.id.imageViewPickGroupShowInfo:
                    onClickImageViewShowGroupInfo(group);
                    break;
                default:
                    checkBoxPickGroup.toggle();
                    onCheckPickGroupItem(group);
                    break;
            }
        }
    }

    private void onCheckPickGroupItem(Group group){
        listener.onCheckPickGroupItem(group);
    }

    private void onClickImageViewShowGroupInfo(Group group){
        listener.onClickImageViewShowGroupInfo(group);
    }
}
