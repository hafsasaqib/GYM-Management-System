package com.example.fitnessclub.Trainee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitnessclub.AttendedSession.AttendedSessions;
import com.example.fitnessclub.Trainer.BookTrainer;
import com.example.fitnessclub.Feedback;
import com.example.fitnessclub.LogIn;
import com.example.fitnessclub.R;
import com.example.fitnessclub.Reservation.CancelReservation;
import com.example.fitnessclub.Reservation.MakeReservation;
import com.example.fitnessclub.Schedule.ViewSchedule;
import com.example.fitnessclub.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TraineeLog extends AppCompatActivity {

    Button makeReservation;
    Button cancelReservation;
    Button bookTrainer;
    Button viewSession;
    Button viewSchedule;
    Button giveFeedback;
    Button sign_out;

    Session session;

    FirebaseAuth m_firebaseAuth =  FirebaseAuth.getInstance();

    private FirebaseAuth.AuthStateListener m_AuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee_log);

        session = new Session(this);

        //saveSchedule();

    makeReservation = findViewById(R.id.make_reservation);
        makeReservation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(TraineeLog.this, "Hello", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), MakeReservation.class);
            startActivity(i);

        }
    });
        cancelReservation = findViewById(R.id.cancel_reservation);
        cancelReservation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(TraineeLog.this, "Hello", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), CancelReservation.class);
            startActivity(i);

        }
    });
        bookTrainer = findViewById(R.id.book_trainer);
        bookTrainer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(TraineeLog.this, "Hello", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), BookTrainer.class);
            startActivity(i);

        }
    });
        viewSession = findViewById(R.id.view_session);
        viewSession.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(TraineeLog.this, "Hello", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), AttendedSessions.class);
            startActivity(i);

        }
    });
        viewSchedule = findViewById(R.id.view_schedule);
        viewSchedule.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(TraineeLog.this, "Hello", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), ViewSchedule.class);
            startActivity(i);

        }
    });
        giveFeedback = findViewById(R.id.give_feedback);
        giveFeedback.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(TraineeLog.this, "Hello", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), Feedback.class);
            startActivity(i);

        }
    });

        sign_out = findViewById(R.id.sign_out);

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TraineeLog.this, LogIn.class);
                startActivity(intent);

            }
        });

    }
    public void saveSchedule(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Trainees");
        String userId = m_firebaseAuth.getCurrentUser().getUid();

        //Toast.makeText(this, session.getMonday() + " " + session.getTuesday() + " " + session.getWednesday(), Toast.LENGTH_SHORT).show();

        if(session.getMonday().equals("true")) myRef.child(userId).child("mon_attend").setValue("true");
        if(session.getTuesday().equals("true")) myRef.child(userId).child("tue_attend").setValue("true");
        if(session.getWednesday().equals("true")) myRef.child(userId).child("wed_attend").setValue("true");

    }
}