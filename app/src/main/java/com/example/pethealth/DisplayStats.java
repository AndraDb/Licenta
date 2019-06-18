package com.example.pethealth;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DisplayStats extends AppCompatActivity {
private TextView dayTV, stepTV;
DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String month=getIntent().getStringExtra("month");
        final String steps=getIntent().getStringExtra("steps");
        setContentView(R.layout.activity_display_stats);
        myDb=new DatabaseHelper(this);
        dayTV=findViewById(R.id.day);
        stepTV=findViewById(R.id.steps);
        Cursor cursor1=myDb.getStats(FirebaseAuth.getInstance().getCurrentUser().getUid(),Integer.parseInt(month));
        cursor1.moveToFirst();
        dayTV.setText(cursor1.getString(cursor1.getColumnIndex("date")));
        stepTV.setText(cursor1.getString(cursor1.getColumnIndex("steps")));




    }
}
