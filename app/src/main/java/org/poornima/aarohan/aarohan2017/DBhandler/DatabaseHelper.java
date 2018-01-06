package org.poornima.aarohan.aarohan2017.DBhandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public DatabaseHelper(Context context){
        super(context,"aarohanDb.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("TAG","Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}