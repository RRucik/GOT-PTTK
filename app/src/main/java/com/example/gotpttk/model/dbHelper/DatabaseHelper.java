package com.example.gotpttk.model.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gotpttk.model.dbModels.Spot;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "gotPttkDb";

    // Table Names
    private static final String TABLE_SPOT = "Punkt";
    private static final String TABLE_SECTION = "Odcinek";
    private static final String TABLE_MOUNTAIN_RANGE = "Obszar_gorski";

    // SPOTS Table - column names
    private static final String COLUMN_SPOT_ID = "id_p";
    private static final String COLUMN_SPOT_NAME = "nazwa_punktu";
    private static final String COLUMN_SPOT_HEIGHT = "wysokosc";
    private static final String COLUMN_SPOT_DESC = "opis";

    // SECTIONS Table - column names
    private static final String COLUMN_SECTION_ID = "id_o";
    private static final String COLUMN_SECTION_START_SPOT_ID = "punkt_pocz_id_p";
    private static final String COLUMN_SECTION_END_SPOT_ID = "punkt_konc_id_k";
    private static final String COLUMN_SECTION_LENGTH = "dlugosc";
    private static final String COLUMN_SECTION_POINTS = "punktacja";
    private static final String COLUMN_SECTION_RETURN_POINTS = "punktacja_w_druga_strone";
    private static final String COLUMN_SECTION_HEIGHT_DIFF = "roznica_wys";
    private static final String COLUMN_SECTION_ACTIVE_SINCE = "aktywny_od";
    private static final String COLUMN_SECTION_OPEN = "otwarty";


    // CREATE TABLE SPOT statement
    private static final String CREATE_TABLE_SPOT = "CREATE TABLE " + TABLE_SPOT
            + "(" + COLUMN_SPOT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SPOT_NAME + "" +
            " TEXT NOT NULL," + COLUMN_SPOT_HEIGHT + " INTEGER," + COLUMN_SPOT_DESC + " TEXT" + ")";

    // CREATE TABLE SECTION statement
    private static final String CREATE_TABLE_SECTION = "";

    // CREATE TABLE MOUNTAIN_RANGE statement

    private static final String CREATE_TABLE_MOUNTAIN_RANGE = "";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Creating tables
        db.execSQL(CREATE_TABLE_SPOT);
//        db.execSQL(CREATE_TABLE_SECTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        // Dropping and creating tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPOT);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTION);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOUNTAIN_RANGE);

        onCreate(db);
    }

    public boolean createSpot(Spot spot)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SPOT_NAME, spot.getName());
        values.put(COLUMN_SPOT_HEIGHT, spot.getHeight());
        values.put(COLUMN_SPOT_DESC, spot.getDesc());

        long insert = db.insert(TABLE_SPOT, null, values);
        return insert != -1;
    }

    public List<Spot> getAllSpots()
    {
        List<Spot> spots = new ArrayList<Spot>();
        String selectQuery = "SELECT  * FROM " + TABLE_SPOT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst())
        {
            do
            {
                Spot spot = new Spot();
                spot.setIdSp(c.getInt(c.getColumnIndex(COLUMN_SPOT_ID)));
                spot.setName(c.getString(c.getColumnIndex(COLUMN_SPOT_NAME)));
                spot.setHeight(c.getInt(c.getColumnIndex(COLUMN_SPOT_HEIGHT)));
                spot.setDesc(c.getString(c.getColumnIndex(COLUMN_SPOT_DESC)));

                spots.add(spot);
            } while (c.moveToNext());
        }
        return spots;
    }

    public void closeDB()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
