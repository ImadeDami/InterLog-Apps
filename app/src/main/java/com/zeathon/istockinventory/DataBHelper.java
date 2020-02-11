package com.zeathon.istockinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBHelper extends SQLiteOpenHelper {
    public static final String TAG = "DataBHelper";
    public static final String TABLE_NAME = "dcstock";
    public static final String COL1 = "id";
    //public static final String COL18 = "USERID";
    public static final String COL2 = "CUSTNAM";
    public static final String COL3 = "ADDR";
    public static final String COL4 = "PRODTNAM";
    public static final String COL5 = "DATE";
    public static final String COL6 = "OPNBAL";
    public static final String COL7 = "RELS1";
    public static final String COL8 = "RELS2";
    public static final String COL9 = "CLSBAL";

    //public static final String SYNC_STATUS = "syncstatus";

    public DataBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CUSTNAM TEXT, ADDR TEXT, PRODTNAM TEXT, DATE TEXT, OPNBAL TEXT, RELS1 TEXT, RELS2 TEXT, CLSBAL TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String custmName, String address, String prodName, String dat, String openBal, String relzo, String relzt, String closBal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL18, userID);
        contentValues.put(COL2, custmName);
        contentValues.put(COL3, address);
        contentValues.put(COL6, prodName);
        contentValues.put(COL4, dat);
        contentValues.put(COL5, openBal);
        contentValues.put(COL7, relzo);
        contentValues.put(COL8, relzt);
        contentValues.put(COL9, closBal);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if data is inserted correctly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        return data;
    }

   /* public boolean updateNameStatus(int id, int sync_status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SYNC_STATUS, sync_status);
        db.update(TABLE_NAME, contentValues, COL1 + "=" + id, null);
        db.close();
        return true;
    } */

    /*
     * this method is for getting all the unsynced name
     * so that we can sync it with database
     * */
   /* public Cursor getUnsyncedNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + SYNC_STATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    } */

    public List<String> getFN() {
        List<String> labels = new ArrayList<String>();
        // Select All Query
        //String selectQuery = "SELECT * FROM " + TABLE_NAME;
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(2));
                labels.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

}