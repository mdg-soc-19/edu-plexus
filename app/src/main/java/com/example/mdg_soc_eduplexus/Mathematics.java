package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Mathematics extends AppCompatActivity {
    static int mi1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics);
        View v1;
        v1 = findViewById(R.id.cardViewIntegration);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_integration);
                mi1 = 1;
            }
        });
        View v2;
        v2 = findViewById(R.id.cardViewProbability);//This is for Algebra Part.
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_probability);
                mi1 = 1;
            }
        });

        View v3;
        v3 = findViewById(R.id.cardView3dGeometry);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_geometry3_d);
                mi1 = 1;
            }
        });
        View v4;
        v4 = findViewById(R.id.cardViewTrigonometry);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setContentView(R.layout.activity_trigonometry);
               mi1 = 1;
            }
        });
        View v5;
        v5 = findViewById(R.id.cardViewConicSection);
        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setContentView(R.layout.activity_conic_section);
               mi1 = 1;
            }
        });
        View v6;
        v6 = findViewById(R.id.cardViewDifferentialCalculus);
        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_differential_calculus);
                mi1 = 1;
            }
        });
    }
    public void onBackPressed() {
        if(mi1 == 1){
            Intent i = new Intent(this,Mathematics.class);
            startActivity(i);
            mi1 = 0;
        }
        else{
            Intent i = new Intent(this,FormulaDeck.class);
            startActivity(i);
        }

    }
}