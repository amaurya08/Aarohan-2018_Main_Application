package org.poornima.aarohan.aarohan2017.DBhandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.poornima.aarohan.aarohan2017.Tables.ProfileTable;
import org.poornima.aarohan.aarohan2017.Tables.TableEventDetails;
import org.poornima.aarohan.aarohan2017.Tables.TableMyeventsDetails;
import org.poornima.aarohan.aarohan2017.Tables.TableSponserDetails;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private Context context;
    public DatabaseHelper(Context context){
        super(context,"aarohanDb.db",null,1);
        //Log.d("TAG","Database Created");
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableSponserDetails.createTable(db);
        TableMyeventsDetails.createTable(db);
        ProfileTable.createTable(db);
        TableEventDetails.createTable(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    @Override
    public SQLiteDatabase getReadableDatabase() {

        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {

        return super.getWritableDatabase();
    }

}