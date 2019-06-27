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

import java.util.Calendar;

import static java.lang.Integer.parseInt;

public class CaloriesFragment extends Fragment {
    TextView calc,war;
    EditText intake;
    DatabaseHelper myDb;
    Button sub;
    int f=0;
    int month,day;
    Calendar now;
    String display;
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
        String type=myDb.getPetType(FirebaseAuth.getInstance().getCurrentUser().getUid());
        calories=Integer.parseInt(myDb.getPetWeight(FirebaseAuth.getInstance().getCurrentUser().getUid()));
        p=0.75;
        c=Math.pow(calories,p)*70;
        Log.e("Calcul",myDb.getPetWeight(FirebaseAuth.getInstance().getCurrentUser().getUid()));
        Log.e("animal","Cal: "+ f + " Type" + type);
       if(type.equals("Cat"))
       {
           f=(int)(c*1.4);
           Log.e("animal","Cal: "+ f + " Type" + type);
           display=String.valueOf(f);
       }
       else
           if(type.equals("Dog")) {
               f = (int) (c * 2.0);
               Log.e("animal","Cal: "+ f + " Type" + type);
               display=String.valueOf(f);
           }

        war.setText("Let`s see how are you doing");

        calc.setText(display);
        sub.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String today1=intake.getText().toString();
                 int today=Integer.parseInt(today1);
                now= Calendar.getInstance();
                month=now.get(now.MONTH)+1;
                day=now.get(now.DAY_OF_MONTH);
                 myDb.updateCal(FirebaseAuth.getInstance().getCurrentUser().getUid(),today,day,month);
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
