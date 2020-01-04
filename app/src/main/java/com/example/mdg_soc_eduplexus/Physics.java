package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Physics extends AppCompatActivity {
    static int pi1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics);
        View v1;
        v1 = findViewById(R.id.cardViewMechanics);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_mechanics);
                pi1 = 1;
            }
        });
        View v2;
        v2 = findViewById(R.id.cardViewThermodynamics);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_thermodynamics);
                pi1 = 1;
            }
        });

        View v3;
        v3 = findViewById(R.id.cardViewMagnetism);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_magnetism);
                pi1 = 1;
            }
        });
        View v4;
        v4 = findViewById(R.id.cardViewWaveTheory);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_wave_theory);
                pi1 = 1;
            }
        });
        View v5;
        v5 = findViewById(R.id.cardViewElectrostatics);
        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_elelctrostatics_and_electricity);
                pi1 = 1;
            }
        });
        View v6;
        v6 = findViewById(R.id.cardViewModernPhysics);
        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_modern_physics);
                pi1 = 1;
            }
        });
    }
    public void onBackPressed() {
        if(pi1 == 1){
            Intent i = new Intent(this,Physics.class);
            startActivity(i);
            pi1 = 0;
        }
        else{
            Intent i = new Intent(this,FormulaDeck.class);
            startActivity(i);
        }

    }
}
