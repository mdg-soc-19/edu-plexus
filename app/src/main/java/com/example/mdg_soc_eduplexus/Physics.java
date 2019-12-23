package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Physics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics);
        View v1;
        v1 = findViewById(R.id.cardViewMechanics);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Physics.this, Mechanics.class);
                startActivity(i);
            }
        });
        View v2;
        v2 = findViewById(R.id.cardViewThermodynamics);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Physics.this, Thermodynamics.class);
                startActivity(i);
            }
        });

        View v3;
        v3 = findViewById(R.id.cardViewMagnetism);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Physics.this, Magnetism.class);
                startActivity(i);
            }
        });
        View v4;
        v4 = findViewById(R.id.cardViewWaveTheory);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Physics.this, WaveTheory.class);
                startActivity(i);
            }
        });
        View v5;
        v5 = findViewById(R.id.cardViewElectrostatics);
        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Physics.this, ElelctrostaticsAndElectricity.class);
                startActivity(i);
            }
        });
        View v6;
        v6 = findViewById(R.id.cardViewModernPhysics);
        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Physics.this,ModernPhysics.class);
                startActivity(i);
            }
        });
    }
}
