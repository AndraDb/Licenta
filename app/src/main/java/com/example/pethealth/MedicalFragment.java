package com.example.pethealth;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MedicalFragment extends Fragment {
    private Button add_task;
    private ListView mTaskList;
    private DatabaseHelper myDb;
    private ArrayAdapter<String>mAdapter;
    private Button delete;
    private ArrayList<String> taskList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


       View view=inflater.inflate(R.layout.medical_frag, container, false);
       add_task=view.findViewById(R.id.AddBtn);
      // delete=view.findViewById(R.id.task_delete);

        taskList = new ArrayList<>();
      // final EditText taskEditText=new EditText(getContext());
        myDb=new DatabaseHelper(getContext());
        mTaskList=view.findViewById(R.id.list_todo);
        View parent =(View) view.getParent();
        updateUI();
        setupListViewListener();


       add_task.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final EditText taskEditText = new EditText(getContext());
               AlertDialog dialog = new AlertDialog.Builder(getContext())
                       .setTitle("Add a new plan")
                       .setMessage("What do you have to do ?")
                       .setView(taskEditText)
                       .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               String task = String.valueOf(taskEditText.getText());
                               SQLiteDatabase db = myDb.getWritableDatabase();
                               ContentValues values = new ContentValues();
                               values.put(myDb.COL_TASK_TITLE, task);
                               db.insertWithOnConflict(myDb.TABLE_TODO,
                                       null,
                                       values,
                                       SQLiteDatabase.CONFLICT_REPLACE);
                               db.close();
                               updateUI();
                           }
                       })
                       .setNegativeButton("Cancel", null)
                       .create();
               dialog.show();

              // updateUI();
           }

       });


       return view;
    }

        public  void updateUI() {

            SQLiteDatabase db = myDb.getReadableDatabase();
            Cursor cursor = db.query(myDb.TABLE_TODO,
                    new String[]{myDb.COL_ID_TASK,myDb.COL_TASK_TITLE},
                    null, null, null, null, null);
            while(cursor.moveToNext()) {
                int idx = cursor.getColumnIndex(myDb.COL_TASK_TITLE);
                Log.e("Task_Select", "Task: " + cursor.getString(idx));
                taskList.add(cursor.getString(idx));
            }



        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(getContext(),
                    R.layout.item_todo,
                    R.id.task_title,
                    taskList);
           mTaskList.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }
    public void deleteTask(View view)
    {

        View parent =(View) view.getParent();
        TextView taskTextView=parent.findViewById(R.id.task_title);
        String task=String.valueOf(taskTextView.getText());
        SQLiteDatabase db=myDb.getWritableDatabase();
        db.delete(myDb.TABLE_TODO,
                myDb.COL_TASK_TITLE+" = ?",
                new String []{task});
        db.close();
        updateUI();
    }

    private void setupListViewListener() {
        mTaskList.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position

                        // Return true consumes the long click event (marks it handled)
                        View parent =(View) item.getParent();
                        TextView taskTextView=parent.findViewById(R.id.task_title);
                       final  String task=String.valueOf(taskTextView.getText());
                        AlertDialog.Builder adb=new AlertDialog.Builder(getContext());
                        adb.setTitle("Done?");
                        adb.setMessage(" " + task);
                        final int positionToRemove = pos;
                        adb.setNegativeButton("Cancel", null);
                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                SQLiteDatabase db=myDb.getWritableDatabase();
                                db.delete(myDb.TABLE_TODO,
                                        myDb.COL_TASK_TITLE+" = ?",
                                        new String []{task});
                            }});
                        adb.show();
                       // updateUI();
                        return true;
                    }

                });
    }
}
