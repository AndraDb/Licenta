package com.example.pethealth;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;

     Sensor accelerometer,count;
     Context context;
     TextView stepC;
     double stepDetector,stepCounter,counterSteps;
     boolean isSensorPresent;
     DatabaseHelper myDb;
    private float f;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       context=getContext();
       myDb=new DatabaseHelper(getContext());
        View view= inflater.inflate(R.layout.home_frag, container, false);
        sensorManager=(SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        stepC=(TextView)view.findViewById(R.id.Steps);
        //sensorManager.registerListener( HomeFragment.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
      //count=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        //if(count!=null)
          //  sensorManager.registerListener( HomeFragment.this,count,SensorManager.SENSOR_DELAY_UI);
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
        stepC.setText(step);

      boolean vedem=myDb.updateSteps(FirebaseAuth.getInstance().getCurrentUser().getUid(),add,6,6);
      if(vedem==true)
      Log.e("Update","true");
      else
          Log.e("Update","true");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}