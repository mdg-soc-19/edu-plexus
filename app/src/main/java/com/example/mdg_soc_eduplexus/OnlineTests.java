package com.example.mdg_soc_eduplexus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;


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
        final View v3 = findViewById(R.id.cardViewTest3);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionAnswer q2 =new QuestionAnswer();
                q2.SetTestNo(3);
                Intent i = new Intent(OnlineTests.this, q2.getClass());
                startActivity(i);
            }
        });
        final View v4 = findViewById(R.id.cardViewTest4);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionAnswer q2 =new QuestionAnswer();
                q2.SetTestNo(4);
                Intent i = new Intent(OnlineTests.this, q2.getClass());
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sign_out:
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(OnlineTests.this, MainActivity.class);
                startActivity(intToMain);
                return true;
            case R.id.show_scorebaoard:
                Intent i = new Intent(OnlineTests.this,ScoreBoard.class);
                return true;
        }
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeMain.class);
        startActivity(intent);
        finish();
    }
}