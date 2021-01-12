package com.example.fitnessclub.Trainee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

//import com.example.fitnessclub.Edit.EditTrainee;
import com.example.fitnessclub.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ManageTrainee extends AppCompatActivity implements MyRvAdapterManageTrainee.ManageTimeAdapterListen{
    RecyclerView rv;
    //Button add;


    List<ManageTraineeData> contacts;
    MyRvAdapterManageTrainee adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_trainee);
//        add = findViewById(R.id.add);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ManageTrainee.this, "floatng button Clicked", Toast.LENGTH_LONG).show();
//                Intent i = new Intent(getApplicationContext(), AddTrainee.class);
//                startActivity(i);
//            }
//        });

        contacts = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Trainees");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot D : snapshot.getChildren()){

                    //ManageTraineeData obj = ;
                    contacts.add(D.getValue(ManageTraineeData.class));
                    adapter.setContactList(contacts);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        contacts.add(new ManageTraineeData("Noor Saqib"    , "03335109701" , "Noor.saqib@nu.edu.pk"));
//        contacts.add(new ManageTraineeData("Zara Akhtar"   , "03335109701" , "Zara.akhtar@nu.edu.pk"));
//        contacts.add(new ManageTraineeData("Sohail Ahmed"  , "03335109701" , "Sohail.ahmed@nu.edu.pk"));
//        contacts.add(new ManageTraineeData("Sami Bokhari"  , "03335109701" , "Sami.bokhari@nu.edu.pk"));
//        contacts.add(new ManageTraineeData("Altaf Hussain" , "03335109701" , "Altaf.hussain@nu.edu.pk"));

        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        adapter = new MyRvAdapterManageTrainee(contacts, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemDeleteClick(ManageTraineeData deletItem) {
        Toast.makeText(this, "Delete clicked", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemEditClick(ManageTraineeData editItem) {
        //Intent intent = new Intent(ManageTrainee.this, EditTrainee.class);
        //startActivity(intent);
    }
}
