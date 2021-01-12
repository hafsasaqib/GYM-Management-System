package com.example.fitnessclub.AttendedSession;

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


public class MyRvAdapterAttendedSession extends RecyclerView.Adapter<MyRvAdapterAttendedSession.MyViewHolder> {
    List<AttentedSessionData> ls;
    Context c;

    public MyRvAdapterAttendedSession(List<AttentedSessionData> ls, Context c) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public MyRvAdapterAttendedSession.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow = LayoutInflater.from(c).inflate(R.layout.manage_attended_session_row, parent, false);
        return new MyViewHolder(itemrow);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull MyRvAdapterAttendedSession.MyViewHolder holder, final int position) {
        holder.time.setText(ls.get(position).getTime());
        holder.name.setText(ls.get(position).getName());
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, position + "", Toast.LENGTH_SHORT).show();
            }
        });

//        holder.ivPic.setImageDrawable(c.getDrawable(ls.get(position).getPic()));
    }
    public void setContactList( List<AttentedSessionData> contactList){
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
//        ImageView ivPic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            row = itemView.findViewById(R.id.row);
//            ivPic = itemView.findViewById(R.id.iv_pic);
        }
    }
}
