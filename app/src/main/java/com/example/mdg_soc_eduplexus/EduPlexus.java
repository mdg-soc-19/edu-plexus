package com.example.mdg_soc_eduplexus;

import android.app.Application;

import com.firebase.client.Firebase;

public class EduPlexus extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
