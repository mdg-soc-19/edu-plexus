package com.example.mdg_soc_eduplexus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Ref;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EduPlanner extends AppCompatActivity {
    private EditText eventname,time,duration;
    private CalendarView calendarView;
    private String selectedDate;
    private FirebaseAuth mAuth;
    private Button b1;
    TextView t1,t2;
    Button b1111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_planner);
        calendarView = findViewById(R.id.calendarView);
        eventname = findViewById(R.id.EventName);
        time = findViewById(R.id.timePeriod);
        duration = findViewById(R.id.duration);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        b1 =  findViewById(R.id.buttonSave);
        t1 = findViewById(R.id.DateToday);
        b1111 = findViewById(R.id.button111111);
        t2 = findViewById(R.id.DateToday);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        t1.setText(formattedDate);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference Ref =  database.getReference("Events");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String MONTH ="" ;
                switch (month) {
                    case 0:
                        MONTH = "Jan";
                        break;
                    case 1:
                        MONTH = "Feb";
                        break;
                    case 2:
                        MONTH = "Mar";
                        break;
                    case 3:
                        MONTH = "Apr";
                        break;
                    case 4:
                        MONTH = "May";
                        break;
                    case 5:
                        MONTH = "Jun";
                        break;
                    case 6:
                        MONTH = "Jul";
                        break;
                    case 7:
                        MONTH = "Aug";
                        break;
                    case 8:
                        MONTH = "Sep";
                        break;
                    case 9:
                        MONTH = "Oct";
                        break;
                    case 10:
                        MONTH = "Nov";
                        break;
                    case 11:
                        MONTH = "Dec";
                        break;
                        default:
                            break;
                }
                selectedDate = dayOfMonth + "/" + MONTH + "/" + year;
                t1.setText(selectedDate);
            }
        });
        b1111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EduPlanner.this,EventsDisplayer.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(time != null && duration != null && eventname != null && selectedDate != null ){
                    String duration1;
                    String EveName;
                    String Time;
                    String email;
                    duration1 = duration.getText().toString() + "hours";
                    EveName = eventname.getText().toString();
                    Time = time.getText().toString();
                    email = user.getEmail();
                    String key = Ref.push().getKey();
                    Events events = new Events(EveName,selectedDate,duration1,Time,email);
                    Ref.child(key).setValue(events);
                    Toast.makeText(EduPlanner.this,"Successful added",Toast.LENGTH_SHORT).show();
                    duration.setText("");
                    eventname.setText("");
                    time.setText("");
                }
                else {
                    Toast.makeText(EduPlanner.this,"Error Occurred! One or More Fields empty try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
