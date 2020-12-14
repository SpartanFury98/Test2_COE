package com.example.learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends AppCompatActivity {
EditText name;
EditText lastname;
EditText emailedit;
Button btnUpdate;
Button btnDelete;
Users Login;
DatabaseReference users1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        name = findViewById(R.id.txtName);
        lastname= findViewById(R.id.txtlastname);
        emailedit = findViewById(R.id.txtEmail);
        btnDelete = findViewById(R.id.Delete);
        btnUpdate = findViewById(R.id.Update);
        users1 = FirebaseDatabase.getInstance().getReference("Users");
        SharedPreferences sp = getSharedPreferences("USERINFO" , Context.MODE_PRIVATE);
        SharedPreferences sp2 = getSharedPreferences("USERPASS" , Context.MODE_PRIVATE);
        String username  = sp.getString("username","UNKNOWN");
        String passwo  = sp2.getString("passwo","UNKNOWN");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateProfile(name.getText().toString(), lastname.getText().toString(),username, emailedit.getText().toString(),passwo,v);
            }
        });


    }
    public void UpdateProfile( String name,  String lastname, String username,  String email,String password, View view){
        users1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(username).exists()){
                    //Toast.makeText(MainActivity.this, "Sucesss Log In", Toast.LENGTH_LONG).show();
                    if(!username.isEmpty()){
                        //Toast.makeText(MainActivity.this, "email is not empty", Toast.LENGTH_LONG).show();
                        Users Update = snapshot.child(username).getValue(Users.class);

                        if(Update.getPassword().equals(password)){
//                            Toast.makeText(MyProfile.this, "Success In", Toast.LENGTH_LONG).show();
//                            Update = new Users( name,
//                                    lastname,
//                                    emailedit.getText().toString(),
//                                    MainActivity.password_user,email);
//                            //users1.child(email).setValue(Update);
                            users1.child(username).child("email").setValue( email);
                            users1.child(username).child("name").setValue( name);
                            users1.child(username).child("lastName").setValue( lastname);
                            Toast.makeText(MyProfile.this, "Successfful Update", Toast.LENGTH_SHORT).show();


//                            Thread thread = new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    SystemClock.sleep(1000);
//                                    Intent intent = new Intent(MyProfile.this, CreateAccount.class);
//                                    startActivity(intent);
//                                }
//                            });
//                            thread.start();

                        }
                        else
                        {
                            Toast.makeText(MyProfile.this, "Password is Wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else  {
                    Toast.makeText(MyProfile.this, "Username is Not Registered", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}