package com.example.fitnessclub;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class offlineApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
