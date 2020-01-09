package com.example.mdg_soc_eduplexus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapterForChats extends ArrayAdapter<ChatMessage> {

    private Activity context;
    private List<ChatMessage> ChatsList;

    public CustomAdapterForChats(Activity context, List<ChatMessage> chatsList) {
        super(context, R.layout.list_item, chatsList);
        this.context = context;
        ChatsList = chatsList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_item,null,true);
        ChatMessage chatMessage = ChatsList.get(position);

        TextView t1 = view.findViewById(R.id.message_text);
        TextView t2 = view.findViewById(R.id.message_time);
        TextView t3 = view.findViewById(R.id.message_user);

        long UnixTime =chatMessage.getMessageTime();
        java.util.Date d = new java.util.Date(UnixTime);
        String s1 = String.valueOf(d);
        t1.setText(chatMessage.getMessageText());
        t2.setText(chatMessage.getMessageUser());
        t3.setText(s1);
        return view;
    }
}
