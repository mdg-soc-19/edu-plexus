package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Mathematics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics);
        View v1;
        v1 = findViewById(R.id.cardViewIntegration);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mathematics.this, Integration.class);
                startActivity(i);
            }
        });
        View v2;
        v2 = findViewById(R.id.cardViewProbability);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mathematics.this, Probability.class);
                startActivity(i);
            }
        });

        View v3;
        v3 = findViewById(R.id.cardView3dGeometry);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mathematics.this, Geometry3D.class);
                startActivity(i);
            }
        });
        View v4;
        v4 = findViewById(R.id.cardViewTrigonometry);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mathematics.this, trigonometry.class);
                startActivity(i);
            }
        });



    }
}