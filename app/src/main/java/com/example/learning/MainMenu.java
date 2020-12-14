package com.example.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    Button play;
    Button donate;
    Button myProfile;
    TextView welcometxt;
    TextView logouttxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        play=findViewById(R.id.playGame);
        donate=findViewById(R.id.donate);
        myProfile=findViewById(R.id.myProfile);
        welcometxt=findViewById(R.id.welcome);
        logouttxt=findViewById(R.id.logout);
        SharedPreferences sp = getSharedPreferences("USERINFO" , Context.MODE_PRIVATE);
        String username  = sp.getString("username","UNKNOWN");
        welcometxt.setText("Welcome, "+username);
    }
    public void launchGame(View view)
        {
            // launch the game
        }
        public void launchDonation(View view)
        {
            // launch donation
        }
        public void launchProfile(View view)
        {
            // launch my profile
        }
        public void logout(View view)
        {
            // logout back to main page
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
}