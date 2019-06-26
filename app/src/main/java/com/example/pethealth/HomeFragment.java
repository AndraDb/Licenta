package com.example.pethealth;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.TimeZone;

public class HomeFragment extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
private SharedPreferences settings;
private SharedPreferences.Editor mEditor;
private int day , month;
private Calendar now;
private ProgressBar pb;
     Sensor accelerometer,count;
     Context context;
     TextView stepC;
     double stepDetector,stepCounter,counterSteps;
     boolean isSensorPresent;
     DatabaseHelper myDb;
    private float f;
    int g;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       context=getContext();
       myDb=new DatabaseHelper(getContext());
       now=Calendar.getInstance(TimeZone.getDefault());
        View view= inflater.inflate(R.layout.home_frag, container, false);
        sensorManager=(SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        stepC=(TextView)view.findViewById(R.id.Steps);
        settings= PreferenceManager.getDefaultSharedPreferences(getContext());
        mEditor=settings.edit();
        pb=(ProgressBar)view.findViewById(R.id.progressBar2);
        //sensorManager.registerListener( HomeFragment.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
      //count=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        //if(count!=null)
          //  sensorManager.registerListener( HomeFragment.this,count,SensorManager.SENSOR_DELAY_UI);


        now= Calendar.getInstance();
        month=now.get(now.MONTH)+1;
        day=now.get(now.DAY_OF_MONTH);
        Log.e("Zi","ziua :"+day +"luna"+month);
        g=Integer.parseInt(myDb.getGoals(FirebaseAuth.getInstance().getCurrentUser().getUid(),month,day));
        pb.setMax(g);
Log.e("Zi","Goals :  "+ g);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
                != null)
        {
            count=
                    sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent = true;
        }
        else
        {
            isSensorPresent = false;
        }

        return view;
    }
   public void onResume() {
        super.onResume();

            sensorManager.registerListener(this, count,
                    SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
   public void onPause() {
        super.onPause();

            sensorManager.unregisterListener(this);

    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        if(f==0)
        {
            f=event.values[0];
        }
        String step;
        int add;
        step=Float.toString((event.values[0]-f)*2);
        add=(int)((event.values[0]-f)*2);
        Log.e("Sensor","Steps:"+step);
       //stepC.setText(step);
        mEditor.putString("key",step);
        mEditor.commit();
        String stepP=settings.getString("key","0");
         String add2=Integer.toString(add);
      stepC.setText(add2);
      pb.setProgress(add);
        if(myDb.getDate(FirebaseAuth.getInstance().getCurrentUser().getUid())==null) {
            myDb.insertDataMonitor(FirebaseAuth.getInstance().getCurrentUser().getUid(), 0, 0, 0, day, month);
            Log.e("Zi","S-o inserat");
        }
        else {
            boolean vedem = myDb.updateSteps(FirebaseAuth.getInstance().getCurrentUser().getUid(), add, day, month);
            Log.e("Zi","Numa isi da update");
            if (vedem == true)
                Log.e("Update", "true");
            else
                Log.e("Update", "true");
        }
      if(now.HOUR_OF_DAY<=24)
      {
          stepC.setText(add2);
      }
      else
      {
         stepC.setText("0");
      }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}