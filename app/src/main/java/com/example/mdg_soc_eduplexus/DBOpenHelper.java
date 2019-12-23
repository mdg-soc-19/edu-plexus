package com.example.mdg_soc_eduplexus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {


    final String CREATE_EVENTS_TABLE = "CREATE TABLE " +
            DBStructure.Eventstable.EVENT_TABLE_NAME + " ( " +
            DBStructure.Eventstable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DBStructure.Eventstable.EVENT + " TEXT, "+
            DBStructure.Eventstable.TIME+" TEXT, "+
            DBStructure.Eventstable.MONTH + " TEXT, "+
            DBStructure.Eventstable.YEAR + " TEXT )";
    /*final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuizContract.QuestionsTable.TEST_NUMBER+ " INTEGER"+
                ")";
    */
    private static final String DROP_EVENTS_TABLE = "DROP TABLE IF EXISTS "+DBStructure.Eventstable.EVENT_TABLE_NAME;

    public DBOpenHelper(@Nullable Context context) {
        super(context, DBStructure.Eventstable.DB_NAME, null ,DBStructure.Eventstable.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_EVENTS_TABLE);
        onCreate(db);
    }

    public void SaveEvent(String event,String time,String date,String month,String year,SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.Eventstable.EVENT,event);
        contentValues.put(DBStructure.Eventstable.TIME,event);
        contentValues.put(DBStructure.Eventstable.DATE,event);
        contentValues.put(DBStructure.Eventstable.MONTH,event);
        contentValues.put(DBStructure.Eventstable.YEAR,event);
        database.insert(DBStructure.Eventstable.EVENT_TABLE_NAME,null,contentValues);
    }

    public Cursor ReadEVents(String date, SQLiteDatabase database){
        String [] Projections = {DBStructure.Eventstable.EVENT,DBStructure.Eventstable.TIME,DBStructure.Eventstable.DATE,DBStructure.Eventstable.MONTH,DBStructure.Eventstable.YEAR};
        String Selection = DBStructure.Eventstable.DATE + "=?";
        String [] SelectionArgs = {date};
       return  database.query(DBStructure.Eventstable.EVENT_TABLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }
    public Cursor ReadEventsperMonth(String month,String year,SQLiteDatabase database){
        String [] Projections = {DBStructure.Eventstable.EVENT,DBStructure.Eventstable.TIME,DBStructure.Eventstable.DATE,DBStructure.Eventstable.MONTH,DBStructure.Eventstable.YEAR};
        String Selection = DBStructure.Eventstable.MONTH + "=? and "+DBStructure.Eventstable.YEAR+"=?";
        String [] SelectionArgs = {month,year};
        return  database.query(DBStructure.Eventstable.EVENT_TABLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }
}
