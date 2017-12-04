package com.s2m.maatwerkproject.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2m.maatwerkproject.Models.Chat;
import com.s2m.maatwerkproject.Models.Group;
import com.s2m.maatwerkproject.R;

import org.apache.commons.lang3.StringUtils;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListItemViewHolder> {

    private Chat[] chats;

    public ChatListAdapter(Chat[] chats){
        this.chats = chats;
    }

    @Override
    public ChatListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_list, parent, false);
        ChatListItemViewHolder viewHolder = new ChatListItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatListItemViewHolder holder, int position) {
        holder.bindChatListItem(chats[position]);
    }

    @Override
    public int getItemCount() {
        return chats.length;
    }

    public class ChatListItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView chatImage;
        private TextView chatName;
        private TextView groupName;
        private TextView groupNames;

        public ChatListItemViewHolder(View itemView) {
            super(itemView);
            chatImage = itemView.findViewById(R.id.imageViewChatListImage);
            chatName = itemView.findViewById(R.id.textViewChatListName);
            groupName = itemView.findViewById(R.id.textViewChatListMyGroup);
            groupNames = itemView.findViewById(R.id.textViewChatListOtherGroups);
        }

        void bindChatListItem(Chat chat){
            //TODO set image
            int i = 0;
            int x = 0;
            chatName.setText(chat.getName());
            if(chat.getGroups() != null){
                String[] foreignGroups = new String[chat.getGroups().length - 1];
                for(Group group : chat.getGroups()){
                    //TODO get my group
                    if(i == 0){
                        groupName.setText(group.getName());
                    }
                    else{
                        foreignGroups[x] = group.getName();
                        x++;
                    }
                    i++;
                }
                groupNames.setText(StringUtils.join(foreignGroups, "; "));
            }
        }
    }

}
