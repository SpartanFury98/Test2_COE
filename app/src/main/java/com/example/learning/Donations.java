package com.example.learning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learning.Donate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Donations extends AppCompatActivity {
    EditText Amount;
    Date currentTime = Calendar.getInstance().getTime();
    Button btninc;
    Button btndec;
    Button btnDonate;
    Button btnViewold;
    Donate dony;
    Toolbar toolbar;
    Users Login;
    DatabaseReference users1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);
       toolbar = findViewById(R.id.toolbarmenu);
        Amount = findViewById(R.id.AmountDisplay);
        btninc = findViewById(R.id.increase);
        btndec = findViewById(R.id.decrease);
        btnDonate = findViewById(R.id.Donate);
        users1 = FirebaseDatabase.getInstance().getReference("Users");
        btnViewold = findViewById(R.id.previousdon);
        Amount.setText("0");

        btninc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int incr= Integer.parseInt(Amount.getText().toString());
               incr= incr+5;
               Amount.setText(""+incr);
            }
        });

        btndec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(Amount.getText().toString())!=0){
                    int dec= Integer.parseInt(Amount.getText().toString());
                    dec= dec-5;
                    Amount.setText(""+dec);
                }
                else  { AlertDialog.Builder alertDialog = new AlertDialog.Builder(Donations.this);
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Cannot Donate less than 0!");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();}
            }
        });

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                users1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(MainActivity.email_user).exists()){
//                            if(Integer.parseInt(Amount.getText().toString())!=0){
                            Toast.makeText(Donations.this, "test Donation", Toast.LENGTH_SHORT).show();
                                users1.child(MainActivity.email_user).child("Donating").child(""+currentTime).setValue(Amount.getText().toString());
                                Toast.makeText(Donations.this, "Successfful Donation", Toast.LENGTH_SHORT).show();
//                            }
//                            else  {
//                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Donations.this);
//                                alertDialog.setTitle("Error");
//                                alertDialog.setMessage("Cannot Donate  0!");
//                                alertDialog.setCancelable(false);
//                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//
//                                    }
//                                });
//                                alertDialog.show();}

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });




        btnViewold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Donations.this,ViewMyDonations.class);
                startActivity(intent);
            }
        });
//        setSupportActionBar(toolbar);
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);

    }
    public void Donation( String amount, String email,  String password, View view){

    }
}