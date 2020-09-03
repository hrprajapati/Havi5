package com.example.havi5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText hpEmail,hpPassword,hpConfirmpassword;
    Button Submit,Submit2;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hpEmail=(EditText)findViewById(R.id.hp_email);
        hpPassword=(EditText)findViewById(R.id.hp_password);
        hpConfirmpassword=(EditText)findViewById(R.id.hp_confirmpassword);
        Submit=(Button)findViewById(R.id.hp_submit);
        Submit2=(Button)findViewById(R.id.hp_submit2);
        firebaseAuth=FirebaseAuth.getInstance();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=hpEmail.getText().toString().trim();
                String password=hpPassword.getText().toString().trim();
                String confirmpassword=hpConfirmpassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this,"please enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this,"please enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword)){
                    Toast.makeText(MainActivity.this,"please enter confirmpassword",Toast.LENGTH_SHORT).show();
                    return;
                }


                if(password.length()<6){
                    Toast.makeText(MainActivity.this,"password too short",Toast.LENGTH_SHORT).show();
                }


                if(password.equals(confirmpassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),login.class));
                                        Toast.makeText(MainActivity.this,"Registration completed",Toast.LENGTH_SHORT).show();
                                    }

                                     else {
                                        Toast.makeText(MainActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                                    }



                                }
            });
        }
                Submit2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(),login.class));
                    }
                });
    }
});

    }
}