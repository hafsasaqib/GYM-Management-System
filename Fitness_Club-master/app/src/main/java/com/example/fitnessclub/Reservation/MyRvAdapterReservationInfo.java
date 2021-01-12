package com.example.fitnessclub.Reservation;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessclub.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


    public class MyRvAdapterReservationInfo extends RecyclerView.Adapter<MyRvAdapterReservationInfo.MyViewHolder> {
        List<ReservationInfoData> ls;
        Context c;

        public MyRvAdapterReservationInfo(List<ReservationInfoData> ls, Context c) {
            this.c = c;
            this.ls = ls;
        }

        @NonNull
        @Override
        public MyRvAdapterReservationInfo.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemrow = LayoutInflater.from(c).inflate(R.layout.reservation_info_row, parent, false);
            return new MyRvAdapterReservationInfo.MyViewHolder(itemrow);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(@NonNull MyRvAdapterReservationInfo.MyViewHolder holder, final int position) {
            holder.membership.setText(ls.get(position).getMembership());
            holder.startDate.setText(ls.get(position).getStartDate());
            holder.endDate.setText(ls.get(position).getEndDate());
            holder.name.setText(ls.get(position).getName());
            holder.row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, position + "", Toast.LENGTH_SHORT).show();
                }
            });

            holder.ivPic.setImageDrawable(c.getDrawable(ls.get(position).getPic()));
        }

        public void setContactList( List<ReservationInfoData> contactList){
            // ls.clear();
            ls = contactList;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return ls.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name, membership, startDate,endDate;
            RelativeLayout row;
            ImageView ivPic;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                membership = itemView.findViewById(R.id.membership);
                startDate = itemView.findViewById(R.id.startDate);
                endDate = itemView.findViewById(R.id.endDate);
                row = itemView.findViewById(R.id.row);
                ivPic = itemView.findViewById(R.id.iv_pic);
            }
        }
    }