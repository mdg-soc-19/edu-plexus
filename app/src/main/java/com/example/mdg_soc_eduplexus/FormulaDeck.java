package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class FormulaDeck extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foemula_deck);
        CardView v1,v2,v3;
        v1 = findViewById(R.id.cardView);//Maths
        v2 = findViewById(R.id.cardView2);//Physics
        v3 = findViewById(R.id.cardView3);//Chemistry
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormulaDeck.this,Mathematics.class);
                startActivity(i);
            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormulaDeck.this,Physics.class);
                startActivity(i);
            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormulaDeck.this,Chemistry.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,HomeMain.class);
        startActivity(i);
    }
}
