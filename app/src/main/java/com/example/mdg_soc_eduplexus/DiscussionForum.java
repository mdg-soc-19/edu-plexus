package com.example.mdg_soc_eduplexus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.format.DateFormat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
public class DiscussionForum extends AppCompatActivity {
    private  static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListOptions<ChatMessage> listOptions;
    private FirebaseListAdapter<ChatMessage> adapter = new FirebaseListAdapter<ChatMessage>(listOptions)
    {
        @Override
        protected void populateView(View v, ChatMessage model, int position) {

            //Get references to the views of list_item.xml
            TextView messageText, messageUser, messageTime;
            messageText =  v.findViewById(R.id.message_text);
            messageUser =  v.findViewById(R.id.message_user);
            messageTime =  v.findViewById(R.id.message_time);

            messageText.setText(model.getMessageText());
            messageUser.setText(model.getMessageUser());
            messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));

        }
    };
    RelativeLayout activity_discussion_forum;
    FloatingActionButton fab;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Snackbar.make(activity_discussion_forum,"SuccessFully Signed in. Welcome to Discussion Forum",Snackbar.LENGTH_SHORT).show();
                displayChatMessage();
            }
            else {
                Snackbar.make(activity_discussion_forum,"We couldn't sign you in.Please try again later",Snackbar.LENGTH_SHORT ).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_forum);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(input.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
            }
        });
        activity_discussion_forum = findViewById(R.id.activity_discussion_forum);
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        }
        else{
            Snackbar.make(activity_discussion_forum,"Welcome"+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
        }
        displayChatMessage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_sign_out)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                   Snackbar.make(activity_discussion_forum,"You have been signed out.",Snackbar.LENGTH_SHORT).show();
                   finish();
                }
            });
        }
        return true;
    }

    private void displayChatMessage() {

        ListView listOfMessage = findViewById(R.id.list_of_messages);
        listOfMessage.setAdapter(adapter);
    }
}
