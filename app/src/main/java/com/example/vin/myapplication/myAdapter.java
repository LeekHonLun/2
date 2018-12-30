package com.example.vin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {
    String dDate,dLocation,dTitle,dDescription;
    Context context;
    ArrayList<Reports> reports;
    AnnouncementList announcementList;

    public myAdapter(Context c, ArrayList<Reports> p){
        context = c;
        reports = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            myViewHolder.date.setText(reports.get(i).getDate());
            myViewHolder.location.setText(reports.get(i).getLocation());
            myViewHolder.title.setText(reports.get(i).getTitle());
            myViewHolder.description.setText(reports.get(i).getDescription());
            if(reports.get(i).getPermission()) {
                myViewHolder.btn.setVisibility(View.VISIBLE);
                myViewHolder.onClick(i);
            }
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView date,location,title,description;
        Button btn;
        public MyViewHolder( View itemView) {
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.rDate);
            location = (TextView) itemView.findViewById(R.id.aLocation);
            title = (TextView) itemView.findViewById(R.id.rTitle);
            description = (TextView) itemView.findViewById(R.id.rDescription);
            btn = (Button)itemView.findViewById(R.id.btncheckDetails);
        }
        public void onClick(final int position){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dDate = reports.get(position).getDate();
                    dLocation = reports.get(position).getLocation();
                    dTitle = reports.get(position).getTitle();
                    dDescription = reports.get(position).getDescription();
                    openAnnouncementDetail();
                }
            });
        }
    }

    public void openAnnouncementDetail(){
        String passDate = dDate.toString();
        String passLocation = dLocation.toString();
        String passTitle = dTitle.toString();
        String passDes = dDescription.toString();
        Intent intent = new Intent(announcementList,AnnouncementDetail.class);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_DATE",passDate);
        extras.putString("EXTRA_LOCATION",passLocation);
        extras.putString("EXTRA_DTITLE",passTitle);
        extras.putString("EXTRA_DES",passDes);

    }
}
