package com.example.pethealth;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private  EditText usernameField,passField;
    private static int RC_SIGN_IN = 100;
    private Button login,register;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //public static final String TAG = "Andra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usernameField = (EditText) findViewById(R.id.username);
        passField = (EditText) findViewById(R.id.password);
        login=(Button)findViewById(R.id.loginBtn);
        register=(Button)findViewById(R.id.registerBtn);
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            if(firebaseAuth.getCurrentUser()!=null)
            {

                startActivity(new Intent(LoginActivity.this,MainApp.class));
                //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            }

            }
        };

     login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            startSignIn();
         }
     });
     register.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(LoginActivity.this,Register1Activity.class));
         }
     });
        mAuth = FirebaseAuth.getInstance();
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn()
    {
        String username=usernameField.getText().toString();
        String pass=passField.getText().toString();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(username, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();

                    }
                }

            });
        }
        

    }
/*mAuth.signInWithEmailAndPassword(username,pass)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                //updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                updateUI(null);
            }

            // ...
        }
    });*/
}
