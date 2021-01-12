package com.example.fitnessclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.fitnessclub.Reservation.ReservationInfo;
import com.example.fitnessclub.Trainee.ManageTrainee;
import com.example.fitnessclub.Trainer.ManageTrainer;
import com.example.fitnessclub.Transection.TransectionInfo;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLog extends AppCompatActivity {

    Button manageTrainer;
    Button manageTrainee;
    //Button manageTimeslot;
    Button reservationInfo;
    Button transectionInfo;
    Button sign_out;

    private FirebaseAuth.AuthStateListener m_AuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log);

        manageTrainer = findViewById(R.id.manage_trainer);
        manageTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminLog.this, "Hello", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), ManageTrainer.class);
                startActivity(i);

            }
        });
        manageTrainee = findViewById(R.id.manage_trainee);
        manageTrainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminLog.this, "Hello", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), ManageTrainee.class);
                startActivity(i);

            }
        });
//        manageTimeslot = findViewById(R.id.manage_timeslots);
//        manageTimeslot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AdminLog.this, "Hello", Toast.LENGTH_SHORT).show();
//
//                Intent i = new Intent(getApplicationContext(), ManageTimeslot.class);
//                startActivity(i);
//
//            }
//        });
        reservationInfo = findViewById(R.id.reservation_info);
        reservationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminLog.this, "Hello", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), ReservationInfo.class);
                startActivity(i);

            }
        });
        transectionInfo = findViewById(R.id.transection_info);
        transectionInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminLog.this, "Hello", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), TransectionInfo.class);
                startActivity(i);

            }
        });

        sign_out = findViewById(R.id.sign_out);

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AdminLog.this,LogIn.class);
                startActivity(intent);

            }
        });


    }
}