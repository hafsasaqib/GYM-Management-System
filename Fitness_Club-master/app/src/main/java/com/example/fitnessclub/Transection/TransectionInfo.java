package com.example.fitnessclub.Transection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.fitnessclub.R;
import com.example.fitnessclub.Trainee.ManageTraineeData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TransectionInfo extends AppCompatActivity {
    RecyclerView rv;
    Button add;
    List<TransectionInfoData> contacts;

    MyRvAdapterTransectionInfo adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transection_info);

        contacts = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Trainees");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot D : snapshot.getChildren()){

                    ManageTraineeData obj = D.getValue(ManageTraineeData.class);

                    //Log.d("status",obj.getExcerciseType());
                    Toast.makeText(TransectionInfo.this, "status : " + obj.getIstransPaid(), Toast.LENGTH_SHORT).show();

                    if(obj.getIstransPaid().equals("")){
                        contacts.add(new TransectionInfoData(obj.getName(),"email: " +obj.getEmail(),"Not Paid", R.drawable.ic_warning_sign));
                    }
                    else{
                        contacts.add(new TransectionInfoData(obj.getName(),"email: " +obj.getEmail(),"Paid", R.drawable.ic_tick_sign));
                    }

                    adapter.setContactList(contacts);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//
//        contacts.add(new TransectionInfoData("Noor Saqib"  , "id: 2382482928373","Paid", R.drawable.ic_tick_sign));
//        contacts.add(new TransectionInfoData("Sami Bukhari", "id: 2382482928373","Not Paid", R.drawable.ic_warning_sign));
//        contacts.add(new TransectionInfoData("Sohail Ahmed", "id: 2382482928373","Paid", R.drawable.ic_tick_sign));

        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        adapter = new MyRvAdapterTransectionInfo(contacts, this);
        rv.setAdapter(adapter);
    }
}