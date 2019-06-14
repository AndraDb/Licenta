package com.example.pethealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="PetData.db";
    //private final Context myContext;
    //USER DATA
    public static final String  TABLE_NAME="User";
    public static final String  COL_IDU="id_user";
    public static final String  COL_USER="username";
   // public static final String  ="User";
    //PET MONITOR
    public static final String TABLE_NAME_PMONITOR="Pet_Monitor";
    public static final String COL_ID_M="id";
    public static final String COL_GOALS="goals";
    public static final String COL_STEPS="steps";
    public static final String COL_CAL="calories";
    public static final String COL_DATE="date";
    public static final String COL_MONTH="month";
    //PET DATA
    public static final  String TABLE_NAME_PET="Pet";
    public static final  String COL_ID_PET="id_user";
    public static final  String COL_NAME_PET="name";
    public static final  String COL_TYPE_PET="type";
    public static final  String COL_BREED_PET="breed";
    public static final  String COL_AGE_PET="age";
    public static final  String COL_WEIGHT_PET="weight";
    public static final  String COL_MED_PET="med";
//public Cursor data;

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
        //this.myContext=context;
        //SQLiteDatabase db=this.getWritableDatabase();
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table "+TABLE_NAME+"(id_user primary key autoincrement,username text)");
        db.execSQL("CREATE TABLE "
                + TABLE_NAME + "(" + COL_IDU
                + "  text primary key ,"+ COL_USER +" varchar)");
        db.execSQL("CREATE TABLE Pet (id_user text,name text, breed text,type text,age numeric,weight numeric,med text,FOREIGN KEY (id_user) REFERENCES User(id_user))");
        db.execSQL("CREATE TABLE Pet_Monitor (id text,goals numeric, steps numeric,calories numeric ,date numeric,month numeric,FOREIGN KEY (id) REFERENCES User(id_user))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        db.execSQL("drop table if exists "+TABLE_NAME_PET);
        db.execSQL("drop table if exists "+TABLE_NAME_PMONITOR);


        onCreate(db);


    }
    public boolean insertDataMonitor(String id, int goals , int steps, int calories, int date,int month) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_M,id);
        contentValues.put(COL_GOALS,goals);
        contentValues.put(COL_STEPS,steps);
        contentValues.put(COL_CAL,calories);
        contentValues.put(COL_DATE,date);
        contentValues.put(COL_MONTH,month);

        long result = db.insert(TABLE_NAME_PMONITOR,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataUser(String id,String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_IDU,id);
        contentValues.put(COL_USER,username);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean insertDataPet(String id,String name , String breed,String type, float age , float weight , String med) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_PET,id);
        contentValues.put(COL_NAME_PET,name);
        contentValues.put(COL_BREED_PET,breed);
        contentValues.put(COL_TYPE_PET,type);
        contentValues.put(COL_AGE_PET,age);
        contentValues.put(COL_WEIGHT_PET,weight);
        contentValues.put(COL_MED_PET,med);

        long result = db.insert(TABLE_NAME_PET,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public String getGoals( String id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        // String query=new String("SELECT username FROM User WHERE id_user=?");
        String[] column={COL_ID_M,COL_GOALS};
        Cursor res = db.query(TABLE_NAME_PMONITOR,column,COL_ID_M+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_M);
            int index2=res.getColumnIndex(COL_GOALS);
            String iduser=res.getString(index);
            String goals=res.getString(index2);
            buffer.append(goals);
        }
        return buffer.toString();
    }
    public String getMonth( String id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        // String query=new String("SELECT username FROM User WHERE id_user=?");
        String[] column={COL_ID_M,COL_MONTH};
        Cursor res = db.query(TABLE_NAME_PMONITOR,column,COL_ID_M+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_M);
            int index2=res.getColumnIndex(COL_MONTH);
            String iduser=res.getString(index);
            String goals=res.getString(index2);
            buffer.append(goals);
        }
        return buffer.toString();
    }
    public String getSteps( String id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        // String query=new String("SELECT username FROM User WHERE id_user=?");
        String[] column={COL_ID_M,COL_STEPS};
        Cursor res = db.query(TABLE_NAME_PMONITOR,column,COL_ID_M+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_M);
            int index2=res.getColumnIndex(COL_STEPS);
            String iduser=res.getString(index);
            String steps=res.getString(index2);
            buffer.append(steps);
        }
        return buffer.toString();
    }
    public String getCalories( String id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        // String query=new String("SELECT username FROM User WHERE id_user=?");
        String[] column={COL_ID_M,COL_CAL};
        Cursor res = db.query(TABLE_NAME_PMONITOR,column,COL_ID_M+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_M);
            int index2=res.getColumnIndex(COL_CAL);
            String iduser=res.getString(index);
            String calories=res.getString(index2);
            buffer.append(calories);
        }
        return buffer.toString();
    }
    public String getDate( String id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        // String query=new String("SELECT username FROM User WHERE id_user=?");
        String[] column={COL_ID_M,COL_DATE};
        Cursor res = db.query(TABLE_NAME_PMONITOR,column,COL_ID_M+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_M);
            int index2=res.getColumnIndex(COL_DATE);
            String iduser=res.getString(index);
            String date=res.getString(index2);
            buffer.append(date);
        }
        return buffer.toString();
    }
    public String getUsername( String id ) {
        SQLiteDatabase db = this.getWritableDatabase();
       // String query=new String("SELECT username FROM User WHERE id_user=?");
        String[] column={COL_IDU,COL_USER};
        Cursor res = db.query(TABLE_NAME,column,COL_IDU+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_IDU);
            int index2=res.getColumnIndex(COL_USER);
            String iduser=res.getString(index);
            String username=res.getString(index2);
            buffer.append(username);
        }
        return buffer.toString();
    }
    public String getPetName(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] column={COL_ID_PET,COL_NAME_PET};
        Cursor res = db.query(TABLE_NAME_PET,column,COL_ID_PET+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_PET);
            int index2=res.getColumnIndex(COL_NAME_PET);
            String iduser=res.getString(index);
            String petName=res.getString(index2);
            buffer.append(petName);
        }
        return buffer.toString();
    }
    public String getPetBreed(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] column={COL_ID_PET,COL_BREED_PET};
        Cursor res = db.query(TABLE_NAME_PET,column,COL_ID_PET+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_PET);
            int index2=res.getColumnIndex(COL_BREED_PET);
            String iduser=res.getString(index);
            String petBreed=res.getString(index2);
            buffer.append(petBreed);
        }
        return buffer.toString();
    }
    public String getPetType(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] column={COL_ID_PET,COL_TYPE_PET};
        Cursor res = db.query(TABLE_NAME_PET,column,COL_ID_PET+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_PET);
            int index2=res.getColumnIndex(COL_TYPE_PET);
            String iduser=res.getString(index);
            String petType=res.getString(index2);
            buffer.append(petType);
        }
        return buffer.toString();
    }
    public String getPetAge(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] column={COL_ID_PET,COL_AGE_PET};
        Cursor res = db.query(TABLE_NAME_PET,column,COL_ID_PET+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_PET);
            int index2=res.getColumnIndex(COL_AGE_PET);
            String iduser=res.getString(index);
            String petAge=res.getString(index2);
            buffer.append(petAge);
        }
        return buffer.toString();
    }
    public String getPetWeight(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] column={COL_ID_PET,COL_WEIGHT_PET};
        Cursor res = db.query(TABLE_NAME_PET,column,COL_ID_PET+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_PET);
            int index2=res.getColumnIndex(COL_WEIGHT_PET);
            String iduser=res.getString(index);
            String petWeight=res.getString(index2);
            buffer.append(petWeight);
        }
        return buffer.toString();
    }
    public String getPetMed(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] column={COL_ID_PET,COL_MED_PET};
        Cursor res = db.query(TABLE_NAME_PET,column,COL_ID_PET+"='"+id+"'",null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            int index=res.getColumnIndex(COL_ID_PET);
            int index2=res.getColumnIndex(COL_MED_PET);
            String iduser=res.getString(index);
            String petMed=res.getString(index2);
            buffer.append(petMed);
        }
        return buffer.toString();
    }
    public boolean updatePetData(String id,String name , String breed,String type, float age , float weight , String med)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_PET,id);
        contentValues.put(COL_NAME_PET,name);
        contentValues.put(COL_BREED_PET,breed);
        contentValues.put(COL_TYPE_PET,type);
        contentValues.put(COL_AGE_PET,age);
        contentValues.put(COL_WEIGHT_PET,weight);
        contentValues.put(COL_MED_PET,med);

        long result = db.update(TABLE_NAME_PET,contentValues," id_user = ?",new String []{id});
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean updatePetMonitor(String id,int goals, int steps , int calories, int date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_M,id);
        contentValues.put(COL_GOALS,goals);
        contentValues.put(COL_STEPS,steps);
        contentValues.put(COL_CAL,calories);
        contentValues.put(COL_CAL,date);


        long result = db.update(TABLE_NAME_PMONITOR,contentValues," id = ?",new String []{id});
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean updateSteps(String id , int steps,int date,int month)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_STEPS,steps);



        long result = db.update(TABLE_NAME_PMONITOR,contentValues," id = ? and date = ? and month = ?",new String []{id,Integer.toString(date),Integer.toString(month)});
        if(result == -1)
            return false;
        else
            return true;
    }
}
