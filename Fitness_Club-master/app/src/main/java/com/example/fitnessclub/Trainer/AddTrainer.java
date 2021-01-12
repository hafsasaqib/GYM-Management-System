package com.example.fitnessclub.Trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnessclub.AdminLog;
import com.example.fitnessclub.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrainer extends AppCompatActivity {
    Button add;
    //Spinner ExcerciseTypeSpinner,timeSpinner;
    EditText name,email,phone_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trainer);

//        ExcerciseTypeSpinner = (Spinner) findViewById(R.id.ExcerciseTypeSpinner);
//        timeSpinner = (Spinner) findViewById(R.id.timeSpinner);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone_no = findViewById(R.id.phone_no);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//
//                String excerciseType = "Cardio-Squates-Lunges";
//                excerciseType = String.valueOf(ExcerciseTypeSpinner.getSelectedItem());
//                String timeslot = "0100-0200 pm";
//                timeslot = String.valueOf(timeSpinner.getSelectedItem());
//
//                Toast.makeText(AddTrainer.this,
//                        "OnClickListener : " + excerciseType +" "+ timeslot ,
//                        Toast.LENGTH_SHORT).show();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Trainers");

                myRef.push().setValue(new ManageTrainerData(name.getText().toString(),phone_no.getText().toString(),email.getText().toString()));

                Intent intent = new Intent(AddTrainer.this, AdminLog.class);
                startActivity(intent);
            }
        });
    }
}