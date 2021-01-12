package com.example.fitnessclub.Reservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessclub.R;
import com.example.fitnessclub.Trainee.TraineeLog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CancelReservation extends AppCompatActivity {

    TextView membership_type,starting_date,end_date;

    private Context mContext;
    FirebaseUser user;
    String uid;
    DatabaseReference databaseReference;

    Button cancel;

    String membershipType="";
    String startDate="";
    String endDate="";
    FirebaseAuth m_firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);
        membership_type = findViewById(R.id.membership_type);
        starting_date = findViewById(R.id.starting_date);
        end_date = findViewById(R.id.end_date);

        m_firebaseAuth =  FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();





        databaseReference = FirebaseDatabase.getInstance().getReference("Trainees");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                membershipType = snapshot.child(uid).child("membershiptype").getValue(String.class);
                startDate = snapshot.child(uid).child("membershipStartDate").getValue(String.class);
                endDate = snapshot.child(uid).child("membershipEndDate").getValue(String.class);

                membership_type.setText(membershipType + "  Membership");
                end_date.setText( "End: " + startDate);
                starting_date.setText("Start: " + endDate);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


        cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(CancelReservation.this);
                dialog.setTitle("Do you want to delete this?");
                dialog.setMessage("Are you sure?");
                dialog.setPositiveButton("Keep", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Trainees");
                        String userId = m_firebaseAuth.getCurrentUser().getUid();

                        myRef.child(userId).child("membershiptype").setValue("");
                        myRef.child(userId).child("membershipStartDate").setValue("");
                        myRef.child(userId).child("membershipEndDate").setValue("");

                        membership_type.setText("No  Membership");
                        end_date.setText( "End: ");
                        starting_date.setText("Start: ");

                        Intent intent = new Intent(CancelReservation.this, TraineeLog.class);
                        startActivity(intent);

                        //Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(CancelReservation.this, "Cancelled", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(CancelReservation.this,CancelReservation.class));

                    }
                });
                AlertDialog dialog1 = dialog.create();
                dialog1.show();
            }
        });
    }
}