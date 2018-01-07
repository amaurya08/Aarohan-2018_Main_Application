package org.poornima.aarohan.aarohan2017.Tables;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by kuldeep on 06-01-2018.
 */

public class TableSponserDetails {

    public final static String TABLE_NAME = "SponserDetails";
    public final static String SNAME = "sname";
    public final static String SURL = "surl";

    private final static String createTable ="CREATE TABLE `SponserDetails` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`sname`\tTEXT,\n" +
            "\t`surl`\tTEXT\n" +
            ");";
    public static void createTable(SQLiteDatabase db) {
        db.execSQL(createTable);
        Log.d("DATABASE", "Table Created");
    }
    public  static void deleteTableData(SQLiteDatabase db,String query){
        db.execSQL(query);
    }

    public static long insert(SQLiteDatabase db, ContentValues cv) {
        return db.insert(TableSponserDetails.TABLE_NAME, null, cv);
    }

}
