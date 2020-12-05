package com.example.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button signup;
    ProgressBar circularBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signUp);
        circularBar=findViewById(R.id.loadingCircle);
    }
    public void signIn(View view) {
        TextView user = findViewById(R.id.userName);
        EditText pass = findViewById(R.id.password);
        if (user.getText().toString().equals("") || pass.getText().toString().equals("")) // do checks here for database login stuff
            Toast.makeText(MainActivity.this, "Credential Missing", Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
            login.setVisibility(view.GONE);
            signup.setVisibility(view.GONE);
            circularBar.setVisibility(view.VISIBLE);
        }

    }
    public void signUp(View view)
    {
        Intent intent = new Intent(this,CreateAccount.class);
        startActivity(intent);
    }
}