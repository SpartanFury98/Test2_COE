package com.example.learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateAccount extends AppCompatActivity {
    TextView hyperLink;
    EditText name;
    EditText familyName;
    EditText password;
    EditText verifyPassword;
    EditText email;
    EditText usern;
    long maxid=0;
    CheckBox termsAndCond;
    Button createAccount;
    DatabaseReference reff;
    Users user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //definition of views
        name=findViewById(R.id.name);
        familyName=findViewById(R.id.lastName);
        password=findViewById(R.id.password);
        verifyPassword=findViewById(R.id.verifyPassword);
        email=findViewById(R.id.email);
        usern= findViewById(R.id.username);
        termsAndCond=findViewById(R.id.termsCheckBox);
        createAccount=findViewById(R.id.createAcc);
        //reff = FirebaseDatabase.getInstance().getReference().child("Users");
        reff = FirebaseDatabase.getInstance().getReference("Users");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists())
//                    maxid=(snapshot.getChildrenCount());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        /*createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new Users( name.getText().toString(),
              familyName.getText().toString(),
              email.getText().toString(),
                password.getText().toString());
                
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(user.getEmail()).exists()){
                            Toast.makeText(CreateAccount.this, "This Email is Already Used", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            reff.child(user.getEmail()).setValue(user);
                            Toast.makeText(CreateAccount.this, "Sucessfful Creation", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//                Intent intent = new Intent(CreateAccount.this, Game.class);
//                startActivity(intent);
            }
        });*/

        // make the back button work
        Toolbar signUpToolBar = findViewById(R.id.signUpToolBar);
        setSupportActionBar(signUpToolBar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //make the button only be available when all data is entered
        /*if(name.getText().toString().equals("") || familyName.getText().toString().equals("") || password.getText().toString().equals("") || verifyPassword.getText().toString().equals("") || email.getText().toString().equals("") || !termsAndCond.isChecked() )
        {
            createAccount.setAlpha(0.5f);
            createAccount.setClickable(false);
        }
        else
            {
            createAccount.setAlpha(1f);
            createAccount.setClickable(true);
        }*/

        //underline hyperlink text
        hyperLink=findViewById(R.id.hyperlink);
        String udata="terms and conditions";
        SpannableString content = new SpannableString(udata);
        content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
        hyperLink.setText(content);
    }
    public void openWebView(View view)
    {
        Intent intent = new Intent(this,WebPageForTAC.class);
        startActivity(intent);
    }
    public void signUpErrorMessage(View view)
    {
        name=findViewById(R.id.name);
        familyName=findViewById(R.id.lastName);
        password=findViewById(R.id.password);
        verifyPassword=findViewById(R.id.verifyPassword);
        email=findViewById(R.id.email);
        usern= findViewById(R.id.username);
        termsAndCond=findViewById(R.id.termsCheckBox);
        createAccount=findViewById(R.id.createAcc);

        if(name.getText().toString().equals("") || familyName.getText().toString().equals("") || password.getText().toString().equals("") || verifyPassword.getText().toString().equals("") || email.getText().toString().equals("") || !termsAndCond.isChecked() )
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Please fill all fields and accept the terms of conditions");
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();


        }
        else if (!password.getText().toString().equals(verifyPassword.getText().toString()))
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Passwords don't match");
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }
        else if (password.getText().toString().length()<6) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Passwords too short");
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();

        }
        else
        {
            user = new Users( name.getText().toString(),
                    familyName.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString(), usern.getText().toString());

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(user.getUsername()).exists()){
                        Toast.makeText(CreateAccount.this, "This UserName is Already Used", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        reff.child(user.getUsername()).setValue(user);
                        Toast.makeText(CreateAccount.this, "Successful Creation", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


    }

}