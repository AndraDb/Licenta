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

import org.w3c.dom.Text;

import static java.lang.Integer.parseInt;

public class CaloriesFragment extends Fragment {
    TextView calc,war;
    EditText intake;
    DatabaseHelper myDb;
    Button sub;
   // @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.calories_frag, container, false);
       myDb=new DatabaseHelper(getContext());
       calc= view.findViewById(R.id.Calculate);
       war=view.findViewById(R.id.Warning);
       intake=view.findViewById(R.id.intakeCal);
       sub=view.findViewById(R.id.calBtn);
       intake.setText("0");
       String today1=intake.getText().toString();
      final int today=Integer.parseInt(today1);
        double c=0;
        double p=0;
        int calories=0;
        calories=Integer.parseInt(myDb.getPetWeight(FirebaseAuth.getInstance().getCurrentUser().getUid()));
        p=0.75;
        c=Math.pow(calories,p)*70;
        Log.e("Calcul",myDb.getPetWeight(FirebaseAuth.getInstance().getCurrentUser().getUid()));
       final int f=(int)c;
        war.setText("Let`s see how are you doing");
        String display=String.valueOf(f);
        calc.setText(display);
        sub.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String today1=intake.getText().toString();
                 int today=Integer.parseInt(today1);
                if(today>f)
                    war.setText("WARNING!Too many calories today , try to cut off a bit");
                else
                if(today<f)
                    war.setText("Calories intake too low , please fed your pet more");
                else
                if(today==f)
                    war.setText("YAY! Perfect caloric intake ! Keep going !");
                else
                    war.setText("Let`s see how are you doing");
            }
        });

        return view;
    }
}
