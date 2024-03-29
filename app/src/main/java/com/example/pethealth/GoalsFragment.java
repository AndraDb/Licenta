package com.example.pethealth;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.TimeZone;

public class GoalsFragment extends Fragment {
    private EditText goals;
    private Calendar now;
    private Button sub;
    private int day,month;
    DatabaseHelper myDb;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.goals_frag, container, false);
        sub=view.findViewById(R.id.Submit);
        goals = view.findViewById(R.id.goalF);
        goals.setText("0");
       // now = Calendar.getInstance(TimeZone.getDefault());
        myDb = new DatabaseHelper(getContext());
        now= Calendar.getInstance();
        month=now.get(now.MONTH)+1;
        day=now.get(now.DAY_OF_MONTH);

sub.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String goal = goals.getText().toString();
        if (!goal.equals("0")){
            int f = Integer.parseInt(goal);
           boolean test= myDb.updateGoals(FirebaseAuth.getInstance().getCurrentUser().getUid(), f, day, month);
           if(test==true)
               Log.e("TestG","S-a dat update la goals");
           else
               Log.e("TestG","Nu s-a dat update ");
            Log.e("Zi","Goals update :  "+ f +"Get Goals:  "+ myDb.getGoals(FirebaseAuth.getInstance().getCurrentUser().getUid(),month, day));


        }
    }
});



            return view;


        }
    }