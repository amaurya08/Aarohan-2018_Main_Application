package org.poornima.aarohan.aarohan2017.Tables;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class ProfileTable {
    public static final String Col_name="name";
    public static final String Col_mail="mail";
    public static final String col_rid="rid";
    public static final String col_college="college";
    public static final String Col_mobileno="mobileno";
    public static final String tablename="profiletable";
    public static final String create=" CREATE TABLE `profiletable` (\n" +
            "\t`sid`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`rid`\tTEXT NOT NULL,\n" +
            "\t`name`\tTEXT NOT NULL,\n" +
            "\t`email`\tTEXT NOT NULL,\n" +
            "\t`mobile`\tTEXT NOT NULL\n" +
            ");";

    public static void createTable(SQLiteDatabase db){
        db.execSQL(create);
    }
    public static long insertDetails(SQLiteDatabase db, ContentValues cv)
    {
        return db.insert("profiletable",null,cv);
    }
}
