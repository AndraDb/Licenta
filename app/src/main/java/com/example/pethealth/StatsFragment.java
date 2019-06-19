package com.example.pethealth;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;


public class StatsFragment extends Fragment {

    private TableLayout tableLayout;
    DatabaseHelper myDb;
    private ListView mStatsList,DayList,CalList;
private Button month;
private String mm;
    private ArrayAdapter<String> mAdapter,dAdapter,cAdapter;
    //Cursor c=myDb.data;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myDb=new DatabaseHelper(getContext());
        SQLiteDatabase database=myDb.getReadableDatabase();
        View view=inflater.inflate(R.layout.statistics_frag, container, false);
        mStatsList=view.findViewById(R.id.listStats);
        DayList=view.findViewById(R.id.listDay);
        CalList=view.findViewById(R.id.listCal);



        month=view.findViewById(R.id.monthS);
        /*Cursor cursor =myDb.;
        if(cursor.getCount()==0)
        {
            toastMessage("Nothing to show.Please select other month");

        }
        */

       month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText taskEditText = new EditText(getContext());
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("Statistics ")
                        .setMessage("Which month will you like to see?")
                        .setView(taskEditText)
                        .setPositiveButton("Show", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               mm=taskEditText.getText().toString();
                                updateUI(mm);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();


            }

        });
       // myDb.insertDataMonitor(FirebaseAuth.getInstance().getCurrentUser().getUid(), 0, 1000,400,6,6);
      // myDb.insertDataMonitor(FirebaseAuth.getInstance().getCurrentUser().getUid(), 0, 1500,410,5,6);
       // myDb.insertDataMonitor(FirebaseAuth.getInstance().getCurrentUser().getUid(), 0, 2500,450,4,6);
       /* tableLayout=(TableLayout)view.findViewById(R.id.tableLayout);
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
       row.removeAllViews();
       } while (data.moveToNext());
        data.close();
*/
        return view;
    }

    private void toastMessage(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }

    private void updateUI(String m) {
        ArrayList<String> statsList = new ArrayList<>();
        ArrayList<String>ST=new ArrayList<>();
        ArrayList<String>CT=new ArrayList<>();
        SQLiteDatabase db = myDb.getReadableDatabase();
       // ListAdapter listAdapter=new ArrayList<String>()
        mAdapter = new ArrayAdapter<>(getContext(),
                R.layout.item_stats,
                R.id.steps,
                statsList);
       //mStatsList.setAdapter(mAdapter);
        dAdapter=new ArrayAdapter<>(getContext(),
                R.layout.item_stats_d,
                R.id.day,
                ST);
        cAdapter=new ArrayAdapter<>(getContext(),
                R.layout.item_stats_c,
                R.id.cal,
                CT);
        Cursor cursor = myDb.getStats(FirebaseAuth.getInstance().getCurrentUser().getUid(),Integer.parseInt(m));/*db.query(myDb.TABLE_NAME_PMONITOR,

                new String[]{myDb.COL_STEPS,myDb.COL_DATE},
                myDb.COL_MONTH +" = 6", null, null, null, null);*/
        if(cursor.getCount()==0)
        {
            toastMessage("Nothing to show for this month.");
        }
        else {
            while (cursor.moveToNext()) {
                // int idx = cursor.getColumnIndex(0);
                //  Log.e("Stats_Select", "Task: " + cursor.getString(idx));
                statsList.add(cursor.getString(2));
                ST.add(cursor.getString(4));
                CT.add(cursor.getString(3));
                DayList.setAdapter(dAdapter);
                mStatsList.setAdapter(mAdapter);
                CalList.setAdapter(cAdapter);
            }



     /*   if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(getContext(),
                    R.layout.item_stats,

                    statsList);
            mStatsList.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(statsList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
        */
        }
    }
}
