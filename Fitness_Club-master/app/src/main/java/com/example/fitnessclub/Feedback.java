package com.example.fitnessclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnessclub.Trainee.TraineeLog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    EditText message;
    Button submit;

    FirebaseAuth m_firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        message = findViewById(R.id.message);
        submit = findViewById(R.id.submit);

        m_firebaseAuth =  FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Trainees");
                String userId = m_firebaseAuth.getCurrentUser().getUid();

                myRef.child(userId).child("feedback").setValue(message.getText().toString());

                Intent intent = new Intent(Feedback.this, TraineeLog.class);
                startActivity(intent);

            }
        });
    }
}