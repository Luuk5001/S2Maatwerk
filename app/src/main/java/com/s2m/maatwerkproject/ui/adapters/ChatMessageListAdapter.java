package com.s2m.maatwerkproject.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.models.Message;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatMessageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SENT_MESSAGE = 0;
    private static final int RECEIVED_MESSAGE = 1;

    private List<Message> messages;

    public ChatMessageListAdapter(List<Message> messages){
        this.messages = messages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO throw correct exception on default
        View view;
        switch (viewType) {
            case SENT_MESSAGE:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_chat_message_sent, parent, false);
                return new ChatMessageListAdapter.SentMessageViewHolder(view);
            case RECEIVED_MESSAGE:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_chat_message_recieved, parent, false);
                return new ChatMessageListAdapter.ReceivedMessageViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //TODO throw correct exception on default
        switch (holder.getItemViewType()) {
            case SENT_MESSAGE:
                ChatMessageListAdapter.SentMessageViewHolder sentMessageViewHolder = (ChatMessageListAdapter.SentMessageViewHolder) holder;
                sentMessageViewHolder.bindMessage(messages.get(position));
                break;
            case RECEIVED_MESSAGE:
                ChatMessageListAdapter.ReceivedMessageViewHolder receivedMessageViewHolder = (ChatMessageListAdapter.ReceivedMessageViewHolder) holder;
                receivedMessageViewHolder.bindMessage(messages.get(position));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        final String groupID = "group1";
        //TODO throw correct exception on default
        //TODO get right group by ID
        switch (messages.get(position).getGroup().getName()){
            case groupID:
                return SENT_MESSAGE;
            default:
                return RECEIVED_MESSAGE;
        }
    }

    class SentMessageViewHolder extends RecyclerView.ViewHolder {

        /*
        @BindView(R.id.textViewChatMessageSentContent)
        TextView textViewMessage;
        @BindView(R.id.textViewChatMessageSentTime)
        TextView textViewTime;
        */

        private TextView textViewMessage;
        private TextView textViewTime;

        public SentMessageViewHolder(View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.textViewChatMessageSentContent);
            textViewTime = itemView.findViewById(R.id.textViewChatMessageSentTime);
        }

        public void bindMessage(Message message){
            //TODO convert timestamp to actual time
            textViewMessage.setText(message.getText());
            textViewTime.setText(message.getDateTimeString(itemView.getContext()));
        }
    }

    class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewChatMessageGroupName)
        TextView textViewGroupName;
        @BindView(R.id.textViewChatMessageContent)
        TextView textViewMessage;
        @BindView(R.id.textViewChatMessageTime)
        TextView textViewTime;

        public ReceivedMessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindMessage(Message message){
            //TODO convert timestamp to actual time
            textViewGroupName.setText(message.getGroup().getName());
            textViewMessage.setText(message.getText());
            textViewTime.setText(message.getDateTimeString(itemView.getContext()));
        }
    }
}
