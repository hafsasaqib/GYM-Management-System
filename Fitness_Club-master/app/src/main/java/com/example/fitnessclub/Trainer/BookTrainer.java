package com.example.fitnessclub.Trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.fitnessclub.R;
import com.example.fitnessclub.Trainee.TraineeLog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BookTrainer extends AppCompatActivity {

    Button book;
    Spinner ExcerciseTypeSpinner,timeSpinner,trainerSpinner;

    FirebaseAuth m_firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trainer);

        ExcerciseTypeSpinner = (Spinner) findViewById(R.id.ExcerciseTypeSpinner);
        timeSpinner = (Spinner) findViewById(R.id.timeSpinner);
        trainerSpinner = (Spinner) findViewById(R.id.trainerSpinner);
        book = findViewById(R.id.book);

        m_firebaseAuth =  FirebaseAuth.getInstance();

        addItemsOnTrainerSpinner();

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Trainees");
                String userId = m_firebaseAuth.getCurrentUser().getUid();

                String excerciseType = "Cardio-Squates-Lunges";
                excerciseType = String.valueOf(ExcerciseTypeSpinner.getSelectedItem());
                String timeslot = "0100-0200 pm";
                timeslot = String.valueOf(timeSpinner.getSelectedItem());
                String trainer = "Hamza";
                trainer = String.valueOf(trainerSpinner.getSelectedItem());

                String mon="";
                String tue="";
                String wed="";
                int count=0;

                StringTokenizer st = new StringTokenizer(excerciseType,"-");
                while (st.hasMoreTokens()) {
                    if(count==0) mon=st.nextToken();
                    if(count==1) tue=st.nextToken();
                    if(count==2) wed=st.nextToken();
                    count++;

                }

                myRef.child(userId).child("selectTimeSlot").setValue(timeslot);
                myRef.child(userId).child("excerciseType").setValue(excerciseType);
                myRef.child(userId).child("trainerName").setValue(trainer);
                myRef.child(userId).child("mon_train").setValue(mon);
                myRef.child(userId).child("tue_train").setValue(tue);
                myRef.child(userId).child("wed_train").setValue(wed);
                myRef.child(userId).child("mon_attend").setValue("false");
                myRef.child(userId).child("tue_attend").setValue("false");
                myRef.child(userId).child("wed_attend").setValue("false");

//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("Trainers");

                Intent intent = new Intent(BookTrainer.this, TraineeLog.class);
                startActivity(intent);
            }
        });

    }

    private void addItemsOnTrainerSpinner() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Trainers");
        trainerSpinner = (Spinner) findViewById(R.id.trainerSpinner);
        final List<String> list = new ArrayList<String>();
        list.add("Select a trainer");

        ChildEventListener childEventListener = myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ManageTrainerData c1 = dataSnapshot.getValue(ManageTrainerData.class);
                list.add(c1.getName());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                ManageTrainerData c1 = dataSnapshot.getValue(ManageTrainerData.class);
                list.add(c1.getName());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trainerSpinner.setAdapter(dataAdapter);
    }
}