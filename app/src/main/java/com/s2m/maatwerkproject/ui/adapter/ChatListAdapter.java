package com.s2m.maatwerkproject.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2m.maatwerkproject.data.models.Chat;
import com.s2m.maatwerkproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListItemViewHolder> implements UpdatableAdapterInterface<Chat> {

    public interface ChatListListener {
        void onClickChatItem(Chat chat);
    }

    private List<Chat> chats;
    private final ChatListListener listener;

    public ChatListAdapter(List<Chat> chats, ChatListListener listener){
        this.chats = chats;
        this.listener = listener;
    }

    @Override
    public void refreshData(List<Chat> data) {

    }

    @Override
    public void addItem(Chat item) {
        chats.add(item);
        notifyDataSetChanged();
    }

    @Override
    public void removeItem(Chat item) {

    }

    @Override
    public ChatListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_list, parent, false);
        return new ChatListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatListItemViewHolder holder, int position) {
        holder.bindChatListItem(chats.get(position));
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    class ChatListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageViewChatListImage)
        ImageView textViewChatImage;
        @BindView(R.id.textViewChatListName)
        TextView textViewChatName;
        @BindView(R.id.textViewChatListGroup)
        TextView textViewGroupName;
        @BindView(R.id.textViewChatListLastMessage)
        TextView textViewLastMessage;

        private Chat chat;

        ChatListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindChatListItem(Chat chat){
            //TODO bind remaining data
            //TODO get group user is participating with
            //TODO do not allow nulls in group array
            this.chat = chat;
            textViewChatName.setText(chat.getName());
        }

        @Override
        public void onClick(View v) {
            listener.onClickChatItem(chat);
        }
    }
}
