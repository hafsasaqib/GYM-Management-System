package com.example.fitnessclub.Trainee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnessclub.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrainee extends AppCompatActivity {
    Button add;
    EditText name,email,phone_no,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trainee);

        add = findViewById(R.id.add);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone_no = findViewById(R.id.phone_no);
        address = findViewById(R.id.address);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //fitnessclub-f74fe

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Trainees");
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                String userId=user.getUid();
                myRef.child(userId).setValue(new ManageTraineeData(name.getText().toString(),phone_no.getText().toString(),email.getText().toString(),address.getText().toString(),"","","","","","","","","",""));

                Intent intent = new Intent(AddTrainee.this, ManageTrainee.class);
                startActivity(intent);
            }
        });
    }
}