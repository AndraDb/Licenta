package com.example.pethealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register1Activity extends AppCompatActivity {
    private EditText emailField, passField, passVField, usernameField;
    private Button next;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    public SharedPreferences prefs;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        emailField = (EditText) findViewById(R.id.emailR);
        passField = (EditText) findViewById(R.id.passR);
        usernameField = (EditText) findViewById(R.id.usernameR);
        passVField = (EditText) findViewById(R.id.passvR);
        next = (Button) findViewById(R.id.nextRBtn);
        mAuth = FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance().getReference();
        db=new DatabaseHelper(this);


     //prefs=getSharedPreferences("Licenta",MODE_PRIVATE);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailField.getText().toString();
                String pass=passField.getText().toString();
                final String username=usernameField.getText().toString();
                String passV=passVField.getText().toString();
                //prefs.edit().putString("username",username).commit();

                if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass)&&!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(passV))
                {
                    if(pass.equals(passV))
                    {
                        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {   boolean inserted=db.insertDataUser(FirebaseAuth.getInstance().getCurrentUser().getUid(),username);

                                    startActivity(new Intent(Register1Activity.this,Register2Activity.class));


                                    if(inserted==true)
                                        Log.e("Database","S-a inserat cu succes");
                                    else
                                        Log.e("Datbase","Opsie nope");

                                }
                                else
                                {
                                    Toast.makeText(Register1Activity.this, "Opsie, something went wrong, please try again :)", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });




                    }
                    else
                    {
                        Toast.makeText(Register1Activity.this, "Password doesn`t match", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {

                }
            }
        });
    }

    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            sendtoMain();
        }
    }

    private void sendtoMain() {
        Intent i=new Intent(Register1Activity.this,MainApp.class);
        startActivity(i);
        finish();
    }
}
