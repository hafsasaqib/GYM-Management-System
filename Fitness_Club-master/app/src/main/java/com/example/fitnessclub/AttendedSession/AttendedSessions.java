package com.example.fitnessclub.AttendedSession;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.fitnessclub.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AttendedSessions extends AppCompatActivity {
    RecyclerView rv;
    List<AttentedSessionData> contacts;
    MyRvAdapterAttendedSession adapter;

    FirebaseUser user;
    String uid;
    DatabaseReference databaseReference;

    String mon_chk="";
    String tue_chk="";
    String wed_chk="";

    String monTrain="";
    String tueTrain="";
    String wedTrain="";

    String time="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attended_sessions);
        contacts = new ArrayList<>();

//        user = FirebaseAuth.getInstance().getCurrentUser();
//        uid = user.getUid();
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("Trainees");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                mon_chk = snapshot.child(uid).child("mon_train").getValue(String.class);
//                tue_chk = snapshot.child(uid).child("tue_train").getValue(String.class);
//                wed_chk = snapshot.child(uid).child("wed_train").getValue(String.class);
//                time = snapshot.child(uid).child("selectTimeSlot").getValue(String.class);
//
//                //mon_attend, tue_attend, wed_attend
//
////                contacts.add(new ViewSceduleData(mon_attend, time));
////                contacts.add(new ViewSceduleData(tue_attend, time));
////                contacts.add(new ViewSceduleData(wed_attend, time));
//
////                if (mon_chk.equals("true")) contacts.add(new AttentedSessionData(monTrain, time));
////                if (tue_chk.equals("true")) contacts.add(new AttentedSessionData(tueTrain, time));
////                if (wed_chk.equals("true")) contacts.add(new AttentedSessionData(wedTrain, time));
//
//                //contacts.setNoti
//                adapter.setContactList(contacts);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//
//            }
//        });

        //mon_attend,
        //tue_attend,
        //wed_attend

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Trainees");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                monTrain = snapshot.child(uid).child("mon_train").getValue(String.class);
                tueTrain = snapshot.child(uid).child("tue_train").getValue(String.class);
                wedTrain = snapshot.child(uid).child("wed_train").getValue(String.class);
                mon_chk = snapshot.child(uid).child("mon_attend").getValue(String.class);
                tue_chk = snapshot.child(uid).child("tue_attend").getValue(String.class);
                wed_chk = snapshot.child(uid).child("wed_attend").getValue(String.class);
                time = snapshot.child(uid).child("selectTimeSlot").getValue(String.class);


                if(mon_chk.equals("true")){
                    Log.d("View Schedule","mon_chk :" + mon_chk);
                    contacts.add(new AttentedSessionData(monTrain, time));
                }

                if(tue_chk.equals("true")){
                    Log.d("View Schedule","tue_chk"+ tue_chk);
                    contacts.add(new AttentedSessionData(tueTrain, time));
                }

                if(wed_chk.equals("true")){
                    Log.d("View Schedule","wed_chk :" +wed_chk);
                    contacts.add(new AttentedSessionData(wedTrain, time));
                }


                //contacts.setNoti
                adapter.setContactList(contacts);

                Log.d("View Schedule","Values" + mon_chk + tue_chk + wed_chk);
                Log.d("View Schedule","Values" + monTrain + tueTrain + wedTrain + time);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //adapter.setContactList(contacts);

        //contacts.add(new AttentedSessionData("RUNNING", "14:00 p.m - 17:00 p.m"));

        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        adapter = new MyRvAdapterAttendedSession(contacts, this);
        rv.setAdapter(adapter);
    }
}