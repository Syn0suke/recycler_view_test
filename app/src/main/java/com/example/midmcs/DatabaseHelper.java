package com.example.midmcs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ItemList.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "item_data";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "item_name";
    private static final String COLUMN_QUANTITY = "item_quantity";
    private static final String COLUMN_DESC = "item_desc";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_QUANTITY + " INTEGER, " +
                        COLUMN_DESC + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addItem(String name, int quantity, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cval = new ContentValues();

        cval.put(COLUMN_NAME,name);
        cval.put(COLUMN_QUANTITY,quantity);
        cval.put(COLUMN_DESC,desc);
        long result = db.insert(TABLE_NAME, null, cval);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
          cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id,  String name, String desc, String quantity)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cval = new ContentValues();
        cval.put(COLUMN_NAME, name);
        cval.put(COLUMN_DESC, desc);
        cval.put(COLUMN_QUANTITY , quantity);
        long result = db.update(TABLE_NAME, cval, "_id=?", new String[]{row_id});
        if (result == -1)
        {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Data Updated", Toast.LENGTH_SHORT).show();
        }
    }
}
