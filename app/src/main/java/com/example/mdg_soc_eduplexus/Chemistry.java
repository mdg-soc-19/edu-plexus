package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Chemistry extends AppCompatActivity {
    static int ci1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemistry);
        View v1;
        v1 = findViewById(R.id.cardViewOrganic);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.organic_chemistry);
                ci1 = 1;
            }
        });
        View v2;
        v2 = findViewById(R.id.cardViewInorganic);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.inorganic_chemistry);
                ci1 = 1;
            }
        });

        View v3;
        v3 = findViewById(R.id.cardViewPhysical);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.physical_chemistry);
                ci1 = 1;
            }
        });
    }
    public void onBackPressed() {
        if(ci1 == 1){
            Intent i = new Intent(this,Mathematics.class);
            startActivity(i);
            ci1 = 0;
        }
        else{
            Intent i = new Intent(this,FormulaDeck.class);
            startActivity(i);
        }

    }
}
