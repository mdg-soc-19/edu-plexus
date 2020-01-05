package com.example.mdg_soc_eduplexus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Events> {

    private Activity context;
    private List<Events> eventsList;

    public CustomAdapter( Activity context, List<Events> eventsList) {
         super(context, R.layout.list_layout, eventsList);
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.list_layout,null,true);
        Events events = eventsList.get(position);
        TextView t1 = view.findViewById(R.id.textViewEventName);
        TextView t2 = view.findViewById(R.id.textViewDuration);
        TextView t3 = view.findViewById(R.id.textViewTime);
        TextView t4 = view.findViewById(R.id.DateTextView);

        t1.setText(events.getEventName());
        t2.setText(events.getDuration());
        t3.setText(events.getTime());
        t4.setText(events.getDate());
        return view;
    }
}
