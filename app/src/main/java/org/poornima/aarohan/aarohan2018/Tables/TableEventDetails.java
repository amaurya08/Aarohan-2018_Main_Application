package org.poornima.aarohan.aarohan2018.Tables;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by kuldeep on 09-01-2018.
 */

public class TableEventDetails {
    public final static String TABLE_NAME = "EventDetails";

    public final static String Event_name = "event_name";
    public final static String Event_category = "event_category";
    public final static String Event_participation_category = "event_participation_category";
    public final static String Event_type = "event_type";
    public final static String Event_detail = "event_detail";
    public final static String Event_location = "event_location";
    public final static String Event_date = "event_date";
    public final static String Event_time = "event_time";
    public final static String Co_name = "co_name";
    public final static String Co_email = "co_email";
    public final static String Co_contact_no = "co_contact_no";
    public final static String Event_map_coordinates_long = "event_map_coordinates_long";
    public final static String Event_map_coordinates_latt = "event_map_coordinates_latt";
    public final static String Event_image_location = "event_image_location";


    private static final String createTable="CREATE TABLE `EventDetails` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`event_name`\tTEXT,\n" +
            "\t`event_category`\tTEXT,\n" +
            "\t`event_participation_category`\tTEXT,\n" +
            "\t`event_type`\tTEXT,\n" +
            "\t`event_detail`\tTEXT,\n" +
            "\t`event_location`\tTEXT,\n" +
            "\t`event_date`\tTEXT,\n" +
            "\t`event_time`\tTEXT,\n" +
            "\t`co_name`\tTEXT,\n" +
            "\t`co_email`\tTEXT,\n" +
            "\t`co_contact_no`\tTEXT,\n" +
            "\t`event_map_coordinates_long`\tTEXT,\n" +
            "\t`event_map_coordinates_latt`\tTEXT,\n" +
            "\t`event_image_location`\tTEXT\n" +
            ");";
    public static void createTable(SQLiteDatabase db) {
        db.execSQL(createTable);
        Log.d("DATABASE", "Table Created");
    }
    public  static void deleteTableData(SQLiteDatabase db,String query){
        db.execSQL(query);
    }

    public static long insert(SQLiteDatabase db, ContentValues cv) {
        return db.insert(TableEventDetails.TABLE_NAME, null, cv);
    }
}
