package com.example.fitnessclub.Schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

//import com.example.fitnessclub.Edit.EditTrainer;
import com.example.fitnessclub.R;
import com.example.fitnessclub.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ViewSchedule extends AppCompatActivity implements MyRvAdapterViewSchedule.ManageTimeAdapterListen{
    RecyclerView rv;
    Button add;
    List<ViewSceduleData> contacts;
    FirebaseUser user;
    String uid;
    DatabaseReference databaseReference;
    MyRvAdapterViewSchedule adapter;

    Boolean mon_done = false;
    Boolean tue_done = false;
    Boolean wed_done = false;


    String monTrain="";
    String tueTrain="";
    String wedTrain="";

    String time="";
    FirebaseAuth m_firebaseAuth =  FirebaseAuth.getInstance();
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        contacts = new ArrayList<>();

        session = new Session(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Trainees");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                monTrain = snapshot.child(uid).child("mon_train").getValue(String.class);
                tueTrain = snapshot.child(uid).child("tue_train").getValue(String.class);
                wedTrain = snapshot.child(uid).child("wed_train").getValue(String.class);
                time = snapshot.child(uid).child("selectTimeSlot").getValue(String.class);

                contacts.add(new ViewSceduleData(monTrain, time,R.drawable.ic_monday_calendar_page));
                contacts.add(new ViewSceduleData(tueTrain, time,R.drawable.ic_tuesday_daily_calendar_page));
                contacts.add(new ViewSceduleData(wedTrain, time,R.drawable.ic_wednesday_calendar_daily_page));

                //contacts.setNoti
                adapter.setContactList(contacts);

                Log.d("View Schedule","before putting values" + mon_done + tue_done + wed_done);



                //Toast.makeText(ViewSchedule.this, monTrain + tueTrain + wedTrain + time, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });




//        adapter.setContactList(contacts);

        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        adapter = new MyRvAdapterViewSchedule(contacts, this,this);
        rv.setAdapter(adapter);
    }


    @Override
    public void onItemAttendedClick(ViewSceduleData attendedItem, int pos) {
        //saveSchedule(pos);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Trainees");
        String userId = m_firebaseAuth.getCurrentUser().getUid();

                //mon_attend, tue_attend, wed_attend

//        Log.d("in attended clicked","before putting values" + mon_done + tue_done + wed_done);
        if(pos == 0)
        {
//            mon_done = true;
//            session.setMonday("true");
            Log.d("in attended clicked","in " + pos );
            myRef.child(userId).child("mon_attend").setValue("true");
        }
        if(pos == 1) {
//            tue_done = true;
//            session.setTuesday("true");
            Log.d("in attended clicked","in " + pos );
            myRef.child(userId).child("tue_attend").setValue("true");
        }
        if(pos == 2){
//            wed_done = true;
//            session.setWednesday("true");
            Log.d("in attended clicked","in " + pos );
            myRef.child(userId).child("wed_attend").setValue("true");
        }
//
//        Toast.makeText(this, pos + " clicked", Toast.LENGTH_SHORT).show();
//        Log.d("in attended clicked","after putting values" + mon_done + tue_done + wed_done);

        Toast.makeText(this, session.getMonday() + " " + session.getTuesday() + " " + session.getWednesday(), Toast.LENGTH_SHORT).show();
//
//        if(session.getMonday().equals("true")) myRef.child(userId).child("mon_attend").setValue("true");
//        if(session.getTuesday().equals("true")) myRef.child(userId).child("tue_attend").setValue("true");
//        if(session.getWednesday().equals("true")) myRef.child(userId).child("wed_attend").setValue("true");

    }


}
