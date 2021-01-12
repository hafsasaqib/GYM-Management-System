package com.example.fitnessclub.Trainer;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fitnessclub.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


public class MyRvAdapterManageTrainer extends RecyclerView.Adapter<MyRvAdapterManageTrainer.MyViewHolder> {
    List<ManageTrainerData> ls;
    Context c;
    ManageTimeAdapterListen listener;

    public MyRvAdapterManageTrainer(List<ManageTrainerData> ls, Context c,ManageTimeAdapterListen listener ) {
        this.c = c;
        this.ls = ls;
        this.listener= listener;
    }

    @NonNull
    @Override
    public MyRvAdapterManageTrainer.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow = LayoutInflater.from(c).inflate(R.layout.manage_trainer_row, parent, false);
        return new MyViewHolder(itemrow);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull MyRvAdapterManageTrainer.MyViewHolder holder, final int position) {

        final ManageTrainerData currentItem =ls.get(position);


        holder.name.setText(currentItem.getName());
        holder.phno.setText(currentItem.getPhno());
        holder.email.setText(currentItem.getEmail());
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, position + "", Toast.LENGTH_SHORT).show();
            }
        });

//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onItemEditClick(currentItem);
//            }
//        });
//
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onItemDeleteClick(currentItem);
//            }
//        });
    }

    public void setContactList( List<ManageTrainerData> contactList){
        // ls.clear();
        ls = contactList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, phno, email;
        //ImageView delete,edit;
        RelativeLayout row;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phno = itemView.findViewById(R.id.phno);
            email = itemView.findViewById(R.id.email);
            row = itemView.findViewById(R.id.row);
//            delete = itemView.findViewById(R.id.delete);
//            edit = itemView.findViewById(R.id.edit);
        }
    }

    public interface  ManageTimeAdapterListen{
        void onItemDeleteClick(ManageTrainerData deletItem);
        void onItemEditClick(ManageTrainerData editItem);
    }
}

