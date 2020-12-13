package com.example.learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    Button login;
    Button signup;
    Button signin;
    ProgressBar circularBar;
    DatabaseReference reff;
    FirebaseDatabase database;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signUp);
        circularBar=findViewById(R.id.loadingCircle);
        reff = FirebaseDatabase.getInstance().getReference().child("Users");
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
            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reff=FirebaseDatabase.getInstance().getReference().child("Users");
                    SignInAuth(user.getText().toString(),
                    pass.getText().toString());
                }
            });


        }

    }
    public void SignInAuth(final String Email,  final String Pass){
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(Email).exists()){
                    if(!Email.isEmpty()){
                        Users Login = snapshot.child(Email).getValue(Users.class);
                        if(Login.getPassword().equals(Pass)){
                        Toast.makeText(MainActivity.this, "Sucesss", Toast.LENGTH_LONG).show();
                        }
                        else
                        {Toast.makeText(MainActivity.this, "Password is Wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                   else  {
                        Toast.makeText(MainActivity.this, "Username is Not Registered", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void signUp(View view)
    {
        Intent intent = new Intent(this,CreateAccount.class);
        startActivity(intent);
    }
}