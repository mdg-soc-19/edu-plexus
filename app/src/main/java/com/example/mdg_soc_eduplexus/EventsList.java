package com.example.mdg_soc_eduplexus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class EventsList extends ArrayAdapter<Events> {
    private Activity context;
    private List<Events> eventsList;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final FirebaseUser user2 = mAuth.getCurrentUser();

    public EventsList(Activity context,List<Events> eventsList){
        super(context,R.layout.list_layout,eventsList);
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =  context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewEventName = listViewItem.findViewById(R.id.textViewEventName);
        TextView textViewTime = listViewItem.findViewById(R.id.textViewTime);
        TextView textViewDuration = listViewItem.findViewById(R.id.textViewDuration);
        TextView textViewDate = listViewItem.findViewById(R.id.textViewTime);

        Events events = eventsList.get(position);
        String email = events.getEmail();
        String emailCompare = user2.getEmail();
        if(!email.equals(emailCompare)){
            View nameBar = listViewItem;
            ((ViewGroup) nameBar.getParent()).removeView(nameBar);
        }
        else {
            textViewEventName.setText(events.getEventName());
            textViewTime.setText(events.getTime());
            textViewDuration.setText(events.getDuration());
            textViewDate.setText(events.getDate());
        }
        return listViewItem;
    }
}
