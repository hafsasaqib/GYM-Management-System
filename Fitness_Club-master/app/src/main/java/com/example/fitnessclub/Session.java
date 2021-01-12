package com.example.fitnessclub;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    //String firstName, String lastName, String date,String gender, String bio

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUserStatus(String status ){ //1 for Admin , 2 for Trainee
        prefs.edit().putString("status", status).commit();
    }

    public String getUserStatus() {
        String status = prefs.getString("status","");
        return status;
    }


    public void setEmail(String email) {
        prefs.edit().putString("email", email).commit();
    }

    public String getEmail() {
        String email = prefs.getString("email","");
        return email;
    }

    public void setMonday(String mon_train) {
        prefs.edit().putString("mon_train", mon_train).commit();
    }

    public String getMonday() {
        String mon_train = prefs.getString("mon_train","");
        return mon_train;
    }
    public void setTuesday(String tue_train) {
        prefs.edit().putString("tue_train", tue_train).commit();
    }

    public String getTuesday() {
        String tue_train = prefs.getString("tue_train","");
        return tue_train;
    }
    public void setWednesday(String wed_train) {
        prefs.edit().putString("wed_train", wed_train).commit();
    }

    public String getWednesday() {
        String wed_train = prefs.getString("wed_train","");
        return wed_train;
    }
}
