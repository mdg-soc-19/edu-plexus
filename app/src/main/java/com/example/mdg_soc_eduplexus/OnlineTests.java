package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;


public class OnlineTests extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_tests);

        final View v1 = findViewById(R.id.cardViewTest1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionAnswer q1 = new QuestionAnswer();
                q1.SetTestNo(1);
                Intent i = new Intent(OnlineTests.this, q1.getClass());
                startActivity(i);
            }
        });
        final View v2 = findViewById(R.id.cardViewTest2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionAnswer q2 =new QuestionAnswer();
                q2.SetTestNo(2);
                Intent i = new Intent(OnlineTests.this, q2.getClass());
                startActivity(i);
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeMain.class);
        startActivity(intent);
        finish();
    }
}