package com.example.havi5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class welcome extends AppCompatActivity {

    EditText text;
    Button Enter;
    DatabaseReference reference;
    user u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        text=(EditText)findViewById(R.id.hp_text);
        Enter = (Button) findViewById(R.id.hp_enter);
        reference = FirebaseDatabase.getInstance().getReference().child("user");
        u = new user();



        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u.setText(text.getText().toString().trim());
                Toast.makeText(welcome.this,"Data inserted successfully",Toast.LENGTH_LONG).show();
                reference.push().setValue(u);
            }
        });


    }
}