package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        View v1,v2,v3,v4;

        v1 = findViewById(R.id.DiscussionForum);
        v2 =  findViewById(R.id.EduPlanner);
        v3 = findViewById(R.id.OnlineTests);
        v4 = findViewById(R.id.FormulaDeck);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMain.this,DiscussionForum.class);
                startActivity(i);
            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMain.this,EduPlanner.class);
                startActivity(i);
            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMain.this,OnlineTests.class);
                startActivity(i);
            }
        });
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMain.this,FoemulaDeck.class);
                startActivity(i);
            }
        });
    }
}
