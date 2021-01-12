package com.example.fitnessclub.Reservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fitnessclub.R;
import com.example.fitnessclub.Trainee.ManageTraineeData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ReservationInfo extends AppCompatActivity {
    RecyclerView rv;
    //Button add;
    List<ReservationInfoData> contacts;
    MyRvAdapterReservationInfo adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_info);

        contacts = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Trainees");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot D : snapshot.getChildren()){

                    ManageTraineeData obj = D.getValue(ManageTraineeData.class);

                    if(obj.getMembershiptype().equals("Gold")){
                        contacts.add(new ReservationInfoData(obj.getName(),obj.getMembershiptype() + " Membership","Start: "+obj.getMembershipStartDate(),"End: "+obj.getMembershipEndDate(), R.drawable.ic_trophy_gold));
                    }
                    if(obj.getMembershiptype().equals("Silver")){
                        contacts.add(new ReservationInfoData(obj.getName(),obj.getMembershiptype() + " Membership","Start: "+obj.getMembershipStartDate(),"End: "+obj.getMembershipEndDate(), R.drawable.ic_trophy_bronze));
                    }
                    if(obj.getMembershiptype().equals("Bronze")){
                        contacts.add(new ReservationInfoData(obj.getName(),obj.getMembershiptype() + " Membership","Start: "+obj.getMembershipStartDate(),"End: "+obj.getMembershipEndDate(), R.drawable.ic_trophy_silver));
                    }

                    adapter.setContactList(contacts);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        contacts.add(new ReservationInfoData("Noor Saqib"  , "GOLD Membersip"  , "Start: 25th aug, 2020" , "End: 25th dec, 2020"  , R.drawable.ic_trophy_gold));
//        contacts.add(new ReservationInfoData("Sami Bukhari", "BRONZE Membersip", "Start: 25th oct, 2020" , "End: 25th nov, 2020"  , R.drawable.ic_trophy_bronze));
//        contacts.add(new ReservationInfoData("Sohail Ahmed", "SILVER Membersip", "Start: 25th jul, 2020" , "End: 25th oct, 2020"  , R.drawable.ic_trophy_silver));

        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
         adapter = new MyRvAdapterReservationInfo(contacts, this);
        rv.setAdapter(adapter);
    }
}