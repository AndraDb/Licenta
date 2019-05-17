package com.example.pethealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register2Activity extends AppCompatActivity {
DatabaseHelper myDb;
private EditText breed,age,name,med,weight;
private TextView username;
private Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb=new DatabaseHelper(this);
        final RadioGroup type=(RadioGroup)findViewById(R.id.typeGroup);
        breed=(EditText)findViewById(R.id.breedR2);
        age=(EditText)findViewById(R.id.ageR2);
        name=(EditText)findViewById(R.id.petNameR2);
        med=(EditText)findViewById(R.id.medsR2);
        weight=(EditText)findViewById(R.id.petWeightR2);
        username=(TextView)findViewById(R.id.userView);
        username.setText(myDb.getUsername(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString());
        Log.e("Register 2","Am ajuns aici la register 2");

        done=(Button)findViewById(R.id.doneBtn);
        int radiogroupID=type.getCheckedRadioButtonId();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ver= myDb.insertDataPet(FirebaseAuth.getInstance().getCurrentUser().getUid(), "cat", breed.getText().toString(), name.getText().toString()
                        , Integer.parseInt(age.getText().toString()),
                        Integer.parseInt(weight.getText().toString()),med.getText().toString());
                if (ver==true)
                    Log.e("Pet","S-a inserat numa bine");
                else
                    Log.e("Pet","nuuuuuuu");
                startActivity(new Intent(Register2Activity.this, MainApp.class));
            }

        });




    }

}
