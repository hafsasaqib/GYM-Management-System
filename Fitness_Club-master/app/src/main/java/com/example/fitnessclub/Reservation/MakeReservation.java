package com.example.fitnessclub.Reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fitnessclub.R;
import com.example.fitnessclub.Trainee.TraineeLog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MakeReservation extends AppCompatActivity {

    Spinner membership_type;
    EditText starting_date,ending_date;
    Button reservation;
    String date;

    FirebaseAuth m_firebaseAuth;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        membership_type = (Spinner) findViewById(R.id.membership_type);
        starting_date =findViewById(R.id.starting_date);
        ending_date = findViewById(R.id.ending_date);
        reservation = findViewById(R.id.reservation);

        m_firebaseAuth =  FirebaseAuth.getInstance();

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Trainees");
                String userId = m_firebaseAuth.getCurrentUser().getUid();

                String membership_type_temp = "Gold";
                membership_type_temp = String.valueOf(membership_type.getSelectedItem());

                myRef.child(userId).child("membershiptype").setValue(membership_type_temp);
                myRef.child(userId).child("membershipStartDate").setValue(starting_date.getText().toString());
                myRef.child(userId).child("membershipEndDate").setValue(ending_date.getText().toString());

                Intent intent = new Intent(MakeReservation.this, TraineeLog.class);
                startActivity(intent);

            }
        });
    }
    public void onClickDate1(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(MakeReservation.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                date = dayOfMonth + "/" + month + "/" + year;
                //birthDate = dayOfMonth;
                //birthMonth = month;
                //birthYear = year;
                starting_date.setText(date);
            }
        };
    }
    public void onClickDate2(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(MakeReservation.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                date = dayOfMonth + "/" + month + "/" + year;
                //birthDate = dayOfMonth;
                //birthMonth = month;
                //birthYear = year;
                ending_date.setText(date);
            }
        };
    }
}