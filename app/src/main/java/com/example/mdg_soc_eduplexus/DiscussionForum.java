package com.example.mdg_soc_eduplexus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.text.format.DateFormat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiscussionForum extends AppCompatActivity {

    private ImageButton postdatabutton;
    private EditText Messaging;
    private ListView listView;
    private List<ChatMessage> chatMessageList;
    private CustomAdapterForChats customAdapterForChats;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = mAuth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_forum);

        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
        listView = findViewById(R.id.list_of_messages);
        postdatabutton = findViewById(R.id.fab);
        Messaging = findViewById(R.id.input);
        chatMessageList = new ArrayList<>();
        customAdapterForChats = new CustomAdapterForChats(DiscussionForum.this, chatMessageList);

        postdatabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Messaging.setText("");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                chatMessageList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    ChatMessage chatMessage = dataSnapshot1.getValue(ChatMessage.class);
                    chatMessageList.add(chatMessage);
                }
                listView.setAdapter(customAdapterForChats);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void saveData(){
        String Message = Messaging.getText().toString();

        String key = databaseReference.push().getKey();
        ChatMessage chatMessage = new ChatMessage(Message,user.getEmail());

        databaseReference.child(key).setValue(chatMessage);
    }
}