package com.example.vin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnnouncementDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_detail);

        Intent intent =getIntent();
        Bundle extras = intent.getExtras();
        String date = extras.getString("EXTRA_DATE");
        String location = extras.getString("EXTRA_LOCATION");
        String title = extras.getString("EXTRA_TITLE");
        String des = extras.getString("EXTRA_DES");

        TextView sDate = (TextView)findViewById(R.id.dDate);
        TextView sLocation = (TextView)findViewById(R.id.dLocation);
        TextView sTitle = (TextView)findViewById(R.id.dTitle);
        TextView sDes = (TextView)findViewById(R.id.dDescription);

        sDate.setText(date);
        sLocation.setText(location);
        sTitle.setText(title);
        sDes.setText(des);
    }
}
