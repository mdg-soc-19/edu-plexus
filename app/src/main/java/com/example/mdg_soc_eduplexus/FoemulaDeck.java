package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class FoemulaDeck extends AppCompatActivity {


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
                Intent i = new Intent(FoemulaDeck.this,Mathematics.class);
                startActivity(i);
            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FoemulaDeck.this,Physics.class);
                startActivity(i);
            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FoemulaDeck.this,Chemistry.class);
                startActivity(i);
            }
        });
    }
}
