package com.example.fitnessclub.Schedule;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessclub.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


public class MyRvAdapterViewSchedule extends RecyclerView.Adapter<MyRvAdapterViewSchedule.MyViewHolder> {
    List<ViewSceduleData> ls;
    Context c;

    MyRvAdapterViewSchedule.ManageTimeAdapterListen listener;



    public MyRvAdapterViewSchedule(List<ViewSceduleData> ls, Context c, MyRvAdapterViewSchedule.ManageTimeAdapterListen listener) {
        this.c = c;
        this.ls = ls;
        this.listener= listener;
    }

    @NonNull
    @Override
    public MyRvAdapterViewSchedule.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow = LayoutInflater.from(c).inflate(R.layout.view_schedule_row, parent, false);
        return new MyRvAdapterViewSchedule.MyViewHolder(itemrow);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final MyRvAdapterViewSchedule.MyViewHolder holder, final int position) {

        final ViewSceduleData currentItem =ls.get(position);

        holder.time.setText(ls.get(position).getTime());
        holder.name.setText(ls.get(position).getName());
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(c, position + "", Toast.LENGTH_SHORT).show();
            }
        });

        holder.attended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(c, "Marked Attended", Toast.LENGTH_SHORT).show();
                listener.onItemAttendedClick(currentItem, position);
            }
        });

        holder.ivPic.setImageDrawable(c.getDrawable(ls.get(position).getPic()));
    }

    public void setContactList( List<ViewSceduleData> contactList){
        // ls.clear();
        ls = contactList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, time;
        RelativeLayout row;
        ImageView ivPic;
        Button attended;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            row = itemView.findViewById(R.id.row);
            ivPic = itemView.findViewById(R.id.iv_pic);
            attended = itemView.findViewById(R.id.attended);
        }
    }
    public interface  ManageTimeAdapterListen{
        void onItemAttendedClick(ViewSceduleData attendedItem, int pos);
    }
}

