package com.example.vin.myapplication;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;
    String userID;
    public DatabaseReference userIDD;

    private DatabaseReference mDatabaseUser_name, mDatabaseUser_email;

    private DrawerLayout drawer;
    TextView txtUsername;
    TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //firebase
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        userID = firebaseUser.getUid();

        //navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        txtUsername = headerView.findViewById(R.id.HeaderUsername);
        txtEmail = headerView.findViewById(R.id.HeaderEmail);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mDatabaseUser_name = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("username");
        mDatabaseUser_email = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("email");

        mDatabaseUser_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtUsername.setText(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabaseUser_email.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtEmail.setText(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        if(firebaseUser == null){
            Intent intent = new Intent(this , Login.class);
            startActivity(intent);
            finish();
        }

    }


    //change to fragment base on what the user click
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,Login.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Testfragment()).commit();
                break;

            case R.id.nav_report:
                startActivity( new Intent(this,Report.class));
                break;
            case R.id.nav_annoucement:
                startActivity(new Intent(this,AnnouncementList.class));
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}
