package com.example.mdg_soc_eduplexus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText Name;
    private EditText UserName, Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.editText1);
        UserName = findViewById(R.id.editText2);
        Password = findViewById(R.id.editText3);
        Info = findViewById(R.id.textView2);
        Login = findViewById(R.id.button);
        Info.setText("No of attempts remaining: 5");
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,HomeMain.class);
                st=Name.getText().toString();
                i.putExtra("Value",st);
                validate(UserName.getText().toString(),Password.getText().toString());
            }
        });

    }

     private void validate(String username, String userPassword){
        if(username.equals("Admin")&& userPassword.equals("1234"))
        {
              Intent i = new Intent(MainActivity.this,SplashActivity.class);
              startActivity(i);
        }
         else if(username.equals("User")&& userPassword.equals("5678"))
         {
             Intent i = new Intent(MainActivity.this,SplashActivity.class);
             startActivity(i);
         }
        else{
            counter -- ;
            Info.setText("No of attempts remaining :" + counter);

        }
        if(counter==0){
            Login.setEnabled(false);
        }
}}
