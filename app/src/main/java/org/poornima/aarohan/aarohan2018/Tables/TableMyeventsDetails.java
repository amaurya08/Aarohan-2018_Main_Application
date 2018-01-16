package org.poornima.aarohan.aarohan2018.Tables;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bhoomika on 08-01-2018.
 */

public class TableMyeventsDetails {
    public static final String Col_eventname="eventname";
    public static final String Col_eventtime="eventtime";
    public static final String Col_eventdate="eventdate";
    public static final String Col_eventmaplong="eventmaplong";
    public static final String Col_eventmaplati="eventmaplati";
    public final static String TABLE_NAME = "MyeventsDetail";
    public final static String create="CREATE TABLE `MyeventsDetail` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`eventname`\tTEXT,\n" +
            "\t`eventtime`\tTEXT,\n" +
            "\t`eventdate`\tTEXT,\n" +
            "\t`eventmaplong`\tTEXT,\n" +
            "\t`eventmaplati`\tTEXT\n" +
            ");";
    public static void createTable(SQLiteDatabase db){
        db.execSQL(create);
     //   Log.d("DATABASE", "Table Created");
    }
    public  static void deleteTableData(SQLiteDatabase db,String query){
        db.execSQL(query);
    }
    public static long insertDetails(SQLiteDatabase db, ContentValues cv) {
        return db.insert("MyeventsDetail",null,cv);
    }
}
