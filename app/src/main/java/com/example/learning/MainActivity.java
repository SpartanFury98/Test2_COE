package com.example.learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learning.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class MainActivity extends AppCompatActivity {
public static String email_user;
public static  String password_user;

    Button login;
    Button signup;

    ProgressBar circularBar;
    DatabaseReference reff;
    DatabaseReference users1;
    FirebaseDatabase database;
    Users Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signUp);
        EditText pass = findViewById(R.id.password);
         EditText username= findViewById(R.id.userName);

        circularBar=findViewById(R.id.loadingCircle);
        users1 = FirebaseDatabase.getInstance().getReference("Users");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TextView user = findViewById(R.id.userName);

                //Toast.makeText(MainActivity.this,"Test" ,Toast.LENGTH_LONG).show();
                SignInAuth(username.getText().toString(), pass.getText().toString(),v);

            }
        });
    }

//        if (user.getText().toString().equals("") || pass.getText().toString().equals("")) // do checks here for database login stuff
//            Toast.makeText(MainActivity.this, "Credential Missing", Toast.LENGTH_LONG).show();
//        else {
//            //Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
////            login.setVisibility(view.GONE);
////            signup.setVisibility(view.GONE);
////            circularBar.setVisibility(view.VISIBLE);

   // }
    private void SignInAuth( String username,   String password, View view){
        users1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(username).exists()){
                    //Toast.makeText(MainActivity.this, "Sucesss Log In", Toast.LENGTH_LONG).show();
                    if(!username.isEmpty()){
                        //Toast.makeText(MainActivity.this, "email is not empty", Toast.LENGTH_LONG).show();
                         Users Login = snapshot.child(username).getValue(Users.class);

                        if(Login.getPassword().equals(password)) {
                            email_user = Login.getUsername();
                            password_user = Login.getPassword();
                            Toast.makeText(MainActivity.this, "Logged In!", Toast.LENGTH_LONG).show();
                            login.setVisibility(view.GONE);
                            signup.setVisibility(view.GONE);
                            circularBar.setVisibility(view.VISIBLE);
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    SystemClock.sleep(1000);
                                    Intent intent = new Intent(MainActivity.this, Donations.class);
                                    startActivity(intent);
                                        }
                 });
                                thread.start();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Password is Wrong", Toast.LENGTH_LONG).show();
                        }

                //}

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
