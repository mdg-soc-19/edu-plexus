package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EduPlanner extends AppCompatActivity {

    CustomCalendarView customCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_planner);

        customCalendarView = findViewById(R.id.custom_calendar_view);
    }
}
