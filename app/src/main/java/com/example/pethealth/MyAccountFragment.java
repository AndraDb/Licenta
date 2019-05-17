package com.example.pethealth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MyAccountFragment extends Fragment {
    private Button modify;
    private EditText name,breed,type,age,weight,med;
    private TextView username;
    DatabaseHelper db;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.my_account_frag, container, false);
       modify=(Button) view.findViewById(R.id.ModifyBtn);
       name=(EditText)view.findViewById(R.id.petName);
       breed=(EditText)view.findViewById(R.id.petBreed);
       type=(EditText)view.findViewById(R.id.petType);
       age=(EditText)view.findViewById(R.id.petAge);
       weight=(EditText)view.findViewById(R.id.petWeight);
       med=(EditText)view.findViewById(R.id.petMed);
       username=(TextView) view.findViewById(R.id.usernameView);
       db=new DatabaseHelper(getContext());
       username.setText(db.getUsername(FirebaseAuth.getInstance().getCurrentUser().getUid()));
       name.setText(db.getPetName(FirebaseAuth.getInstance().getCurrentUser().getUid()));
       breed.setText(db.getPetBreed(FirebaseAuth.getInstance().getCurrentUser().getUid()));
       type.setText(db.getPetType(FirebaseAuth.getInstance().getCurrentUser().getUid()));
       age.setText(db.getPetAge(FirebaseAuth.getInstance().getCurrentUser().getUid()));
       weight.setText(db.getPetWeight(FirebaseAuth.getInstance().getCurrentUser().getUid()));
       med.setText(db.getPetMed(FirebaseAuth.getInstance().getCurrentUser().getUid()));


       modify.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               boolean ver= db.updatePetData(FirebaseAuth.getInstance().getCurrentUser().getUid(),type.getText().toString(), breed.getText().toString(), name.getText().toString()
                       , Integer.parseInt(age.getText().toString()),
                       Integer.parseInt(weight.getText().toString()),med.getText().toString());
               if (ver==true)
                   Log.e("MyAccount","S-a modificat numa bine");
               else
                   Log.e("MyAccount","nuuuuuuu");
           }
       });


        return view;

    }
}
