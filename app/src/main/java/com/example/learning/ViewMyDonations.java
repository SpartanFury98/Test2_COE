package com.example.learning;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learning.Donate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class ViewMyDonations extends AppCompatActivity {
    ScrollView listView;
    DatabaseReference users1;
    TextView txt1;
EditText multiline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_donations);
        listView = findViewById(R.id.scrolldata);
        users1 = FirebaseDatabase.getInstance().getReference("Users");
        txt1 = findViewById(R.id.textView8);
        multiline = findViewById(R.id.editTextTextMultiLine2);
        users1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {

                    if (snapshot.child(MainActivity.email_user).child("Donating").exists()) {

                        String milti;
                        for(DataSnapshot datas: snapshot.child(MainActivity.email_user).child("Donating").getChildren()){

                            String value = datas.getValue(String.class);
                            milti=datas.getKey()+" ---"+ value+"\n";
                            listView.
                            //txt1.setText(datas.getKey()+" ---"+ value);

                        }




                    }

                }


            @Override
            public void onCancelled(DatabaseError error) {

            }


//
//                //Creating an String array for the ListView
//                String[] donations = new String[heroList.size()];
//
//                //looping through all the heroes and inserting the names inside the string array
//                for (int i = 0; i < heroList.size(); i++) {
//                    heroes[i] = heroList.get(i).getWord();
//                }
//
//                //displaying the string array into listview
//                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
//            }
//
//            @Override
//            public void onFailure(Call<List<Words>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
        });
    }



}