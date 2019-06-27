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
    private EditText breed, age, name, med, weight;
    private TextView username;
    private Button done;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseHelper(this);
        final RadioGroup type = (RadioGroup) findViewById(R.id.typeGroup);
        breed = (EditText) findViewById(R.id.breedR2);
        age = (EditText) findViewById(R.id.ageR2);
        name = (EditText) findViewById(R.id.petNameR2);
        med = (EditText) findViewById(R.id.medsR2);
        weight = (EditText) findViewById(R.id.petWeightR2);
        username = (TextView) findViewById(R.id.userView);
        username.setText(myDb.getUsername(FirebaseAuth.getInstance().getCurrentUser().getUid()));
        Log.e("Register 2", "Am ajuns aici la register 2");
        final RadioButton cat = (RadioButton) findViewById(R.id.catBtn);
        final RadioButton dog = (RadioButton) findViewById(R.id.dogBtn);
        //  int selectedId=type.getCheckedRadioButtonId();


        done = (Button) findViewById(R.id.doneBtn);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String breedT = breed.getText().toString();
                String ageT = age.getText().toString();
                String nameT = name.getText().toString();
                String weightT = weight.getText().toString();
                String medT = med.getText().toString();
                final int radiogroupID = type.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radiogroupID);
                Log.e("register", "Pet Type  " + String.valueOf(radioButton.getText()));
                if (!TextUtils.isEmpty(breedT) && !TextUtils.isEmpty(ageT) && !TextUtils.isEmpty(nameT) && !TextUtils.isEmpty(weightT) && !TextUtils.isEmpty(medT)) {
                    boolean ver = myDb.insertDataPet(FirebaseAuth.getInstance().getCurrentUser().getUid(), name.getText().toString(), breed.getText().toString(), String.valueOf(radioButton.getText())
                            , Integer.parseInt(age.getText().toString()),
                            Integer.parseInt(weight.getText().toString()), med.getText().toString());
                    if (ver == true)
                        Log.e("Pet", "S-a inserat numa bine");
                    else
                        Log.e("Pet", "nuuuuuuu");
                }


                startActivity(new Intent(Register2Activity.this, SplashScreen.class));

            }
        });
    }
}









