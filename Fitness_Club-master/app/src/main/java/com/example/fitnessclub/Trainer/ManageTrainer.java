package com.example.fitnessclub.Trainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.example.fitnessclub.Edit.EditTrainer;
import com.example.fitnessclub.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ManageTrainer extends AppCompatActivity implements MyRvAdapterManageTrainer.ManageTimeAdapterListen {
    RecyclerView rv;
    Button add;
    List<ManageTrainerData> contacts;
    //List<String,String,String> contacts;

    MyRvAdapterManageTrainer adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_trainer);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManageTrainer.this, "floatng button Clicked", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), AddTrainer.class);
                startActivity(i);
            }
        });
        contacts = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Trainers");

        ChildEventListener childEventListener = myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ManageTrainerData c1 = dataSnapshot.getValue(ManageTrainerData.class);

                contacts.add(new ManageTrainerData(c1.getName() , c1.getPhno() , c1.getEmail()));
                adapter.setContactList(contacts);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                ManageTrainerData c1 = dataSnapshot.getValue(ManageTrainerData.class);
                contacts.add(new ManageTrainerData(c1.getName() , c1.getPhno() , c1.getEmail()));
                adapter.setContactList(contacts);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ManageTrainerData c1 = dataSnapshot.getValue(ManageTrainerData.class);
                contacts.add(new ManageTrainerData(c1.getName() , c1.getPhno() , c1.getEmail()));
                adapter.setContactList(contacts);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



//        contacts.add(new ManageTrainerData("Hafsa Saqib" , "03335109701" , "hafsa.saqib@nu.edu.pk"));
//        contacts.add(new ManageTrainerData("Saqib Elahi" , "03335109701" , "saqib.elahi@nu.edu.pk"));
//        contacts.add(new ManageTrainerData("Ayaan Saqib" , "03335109701" , "ayaan.saqib@nu.edu.pk"));
//        contacts.add(new ManageTrainerData("Asad Bokhari", "03335109701" , "asad.bukhari@nu.edu.pk"));
//        contacts.add(new ManageTrainerData("Nazia Ehsan" , "03335109701" , "nazia.ehsan@nu.edu.pk"));

        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        adapter = new MyRvAdapterManageTrainer(contacts, this, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemDeleteClick(ManageTrainerData deletItem) {
        Toast.makeText(this, "Delete clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemEditClick(ManageTrainerData editItem) {
        //Intent intent = new Intent(ManageTrainer.this, EditTrainer.class);
        //startActivity(intent);
    }
}
