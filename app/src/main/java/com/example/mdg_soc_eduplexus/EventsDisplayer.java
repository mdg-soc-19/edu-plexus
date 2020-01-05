package com.example.mdg_soc_eduplexus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventsDisplayer extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    private List<Events> EventsList;
    private  CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_displayer);

        databaseReference =  FirebaseDatabase.getInstance().getReference("Events");
        EventsList = new ArrayList<>();
        customAdapter = new CustomAdapter(EventsDisplayer.this,EventsList);
        listView = findViewById(R.id.listof_the_events);
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                EventsList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Events event = dataSnapshot1.getValue(Events.class);
                    EventsList.add(event);
                }
                listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
