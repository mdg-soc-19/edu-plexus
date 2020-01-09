package com.example.mdg_soc_eduplexus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventsList extends ArrayAdapter<Events> {

    private Activity activity;
    private List<Events> eventsList1234;

    public EventsList(Activity activity, List<Events> eventsList1234) {
        super(activity, R.layout.list_layout, eventsList1234);
        this.activity = activity;
        this.eventsList1234 = eventsList1234;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater  = activity.getLayoutInflater();

        View listitemView = inflater.inflate(R.layout.list_item, null,true);

        TextView textViewDuration = listitemView.findViewById(R.id.message_time);
        TextView textViewSubject = listitemView.findViewById(R.id.message_text);
        TextView textViewnew = listitemView.findViewById(R.id.message_user);

        Events events = eventsList1234.get(position);

        textViewSubject.setText(events.getSubject());
        textViewDuration.setText(events.getEventName());
        textViewnew.setText(events.getEventsID());

        return listitemView;
    }
}
