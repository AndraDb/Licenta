package com.example.pethealth;

import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class StatsFragment extends Fragment {

    private TableLayout tableLayout;
    DatabaseHelper myDb;
    //Cursor c=myDb.data;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myDb=new DatabaseHelper(getContext());
        SQLiteDatabase database=myDb.getReadableDatabase();
        View view=inflater.inflate(R.layout.statistics_frag, container, false);
        myDb.insertDataMonitor(FirebaseAuth.getInstance().getCurrentUser().getUid(), 0, 1000,400,6,6);
       myDb.insertDataMonitor(FirebaseAuth.getInstance().getCurrentUser().getUid(), 0, 1500,410,5,6);
        myDb.insertDataMonitor(FirebaseAuth.getInstance().getCurrentUser().getUid(), 0, 2500,450,4,6);
        tableLayout=(TableLayout)view.findViewById(R.id.tableLayout);
         Cursor data=database.rawQuery("Select * from Pet_Monitor",null);


         data.moveToFirst();
      do {
          TableRow row= new TableRow(getContext());
          TextView day  = (TextView) view.findViewById(R.id.day);
          TextView month  = (TextView) view.findViewById(R.id.month);
          TextView steps  = (TextView) view.findViewById(R.id.steps);

          TextView calories  = (TextView) view.findViewById(R.id.calories);



          //day.setText(myDb.getDate(FirebaseAuth.getInstance().getCurrentUser().getUid()));
            month.setText("05");
           // steps.setText(myDb.getSteps(FirebaseAuth.getInstance().getCurrentUser().getUid()));
            //calories.setText(myDb.getCalories(FirebaseAuth.getInstance().getCurrentUser().getUid()));
   day.setText(data.getString(4));
         // month.setText(data.getString(4));
          steps.setText(data.getString(2));
          calories.setText(data.getString(3));
          row.removeAllViews();
      /* row.addView(day);
       row.addView(month);
       row.addView(steps);
       row.addView(calories);
       tableLayout.addView(row);
       row.removeAllViews();*/
       } while (data.moveToNext());
        data.close();

        return view;
    }
}
