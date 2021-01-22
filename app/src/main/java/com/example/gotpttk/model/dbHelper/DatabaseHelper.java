package com.example.gotpttk.model.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gotpttk.model.dbModels.Section;
import com.example.gotpttk.model.dbModels.Spot;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "gotPttkDb";

    // Table Names
    private static final String TABLE_SPOT = "Punkt";
    private static final String TABLE_SECTION = "Odcinek";

    // SPOTS Table - column names
    private static final String COLUMN_SPOT_ID = "id_p";
    private static final String COLUMN_SPOT_NAME = "nazwa_punktu";
    private static final String COLUMN_SPOT_HEIGHT = "wysokosc";
    private static final String COLUMN_SPOT_DESC = "opis";

    // SECTIONS Table - column names
    private static final String COLUMN_SECTION_ID = "id_o";
    private static final String COLUMN_SECTION_START_SPOT_ID = "punkt_pocz_id_p";
    private static final String COLUMN_SECTION_END_SPOT_ID = "punkt_konc_id_k";
    private static final String COLUMN_SECTION_MOUNTAIN_RANGE = "pasmo_gorskie";
    private static final String COLUMN_SECTION_LENGTH = "dlugosc";
    private static final String COLUMN_SECTION_POINTS = "punktacja";
    private static final String COLUMN_SECTION_RETURN_POINTS = "punktacja_w_druga_strone";
    private static final String COLUMN_SECTION_HEIGHT_DIFF = "roznica_wys";
    private static final String COLUMN_SECTION_ACTIVE_SINCE = "aktywny_od";
    private static final String COLUMN_SECTION_DESC = "opis";
    private static final String COLUMN_SECTION_OPEN = "otwarty";


    // CREATE TABLE SPOT statement
    private static final String CREATE_TABLE_SPOT = "CREATE TABLE "
            + TABLE_SPOT + "("
            + COLUMN_SPOT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SPOT_NAME + " TEXT NOT NULL UNIQUE,"
            + COLUMN_SPOT_HEIGHT + " INTEGER,"
            + COLUMN_SPOT_DESC + " TEXT)";

    // CREATE TABLE SECTION statement
    private static final String CREATE_TABLE_SECTION = "CREATE TABLE "
            + TABLE_SECTION + "("
            + COLUMN_SECTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SECTION_START_SPOT_ID + " INTEGER NOT NULL,"
            + COLUMN_SECTION_END_SPOT_ID + " INTEGER NOT NULL,"
            + COLUMN_SECTION_MOUNTAIN_RANGE + " TEXT NOT NULL,"
            + COLUMN_SECTION_LENGTH + " INTEGER NOT NULL,"
            + COLUMN_SECTION_POINTS + " INTEGER NOT NULL,"
            + COLUMN_SECTION_RETURN_POINTS + " INTEGER,"
            + COLUMN_SECTION_HEIGHT_DIFF + " INTEGER,"
            + COLUMN_SECTION_ACTIVE_SINCE + " TEXT NOT NULL,"
            + COLUMN_SECTION_DESC + " TEXT,"
            + COLUMN_SECTION_OPEN + " INTEGER NOT NULL,"
            + " FOREIGN KEY ("+COLUMN_SECTION_START_SPOT_ID+") REFERENCES "+TABLE_SPOT+"("+COLUMN_SPOT_ID+"),"
            + " FOREIGN KEY ("+COLUMN_SECTION_END_SPOT_ID+") REFERENCES "+TABLE_SPOT+"("+COLUMN_SPOT_ID+"));";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Creating tables
        db.execSQL(CREATE_TABLE_SPOT);
        db.execSQL(CREATE_TABLE_SECTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        // Dropping and creating tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPOT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTION);

        onCreate(db);
    }

    //------------------------- SPOTS CRUD OPERATIONS -------------------------
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

    public boolean updateSpot(Spot spot)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SPOT_NAME, spot.getName());
        values.put(COLUMN_SPOT_HEIGHT, spot.getHeight());
        values.put(COLUMN_SPOT_DESC, spot.getDesc());

        long update = db.update(TABLE_SPOT, values, COLUMN_SPOT_ID + " = ?",
                new String[] { String.valueOf(spot.getIdSp())});
        return update != -1;
    }

    public boolean deleteSpot(long spot_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete(TABLE_SPOT, COLUMN_SPOT_ID + " = ?",
                new String[] { String.valueOf(spot_id) });
        return delete != 0;
    }

    public Spot getSpot(long spot_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_SPOT + " WHERE "
                + COLUMN_SPOT_ID + " = " + spot_id;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null && c.moveToFirst())
        {
            Spot spot = new Spot();
            spot.setIdSp(c.getInt(c.getColumnIndex(COLUMN_SPOT_ID)));
            spot.setName(c.getString(c.getColumnIndex(COLUMN_SPOT_NAME)));
            if(c.isNull(c.getColumnIndex(COLUMN_SPOT_HEIGHT))){
                spot.setHeight(null);
            }
            else{
                spot.setHeight(c.getInt(c.getColumnIndex(COLUMN_SPOT_HEIGHT)));
            }
            spot.setDesc(c.getString(c.getColumnIndex(COLUMN_SPOT_DESC)));

            return spot;
        }
        return null;
    }

    public Spot getSpotWithName(String spot_name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_SPOT + " WHERE "
                + COLUMN_SPOT_NAME + " = '" + spot_name + "'";

        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null && c.moveToFirst())
        {
            Spot spot = new Spot();
            spot.setIdSp(c.getInt(c.getColumnIndex(COLUMN_SPOT_ID)));
            spot.setName(c.getString(c.getColumnIndex(COLUMN_SPOT_NAME)));
            if(c.isNull(c.getColumnIndex(COLUMN_SPOT_HEIGHT))){
                spot.setHeight(null);
            }
            else{
                spot.setHeight(c.getInt(c.getColumnIndex(COLUMN_SPOT_HEIGHT)));
            }
            spot.setDesc(c.getString(c.getColumnIndex(COLUMN_SPOT_DESC)));
            return spot;
        }
        return null;
    }

    public List<Spot> getFilteredSpots(String spot_name, Integer spot_height)
    {
        //spot_height = spot_height != null ? spot_height : -1;
        List<Spot> spots = new ArrayList<Spot>();
        String selectQuery = "SELECT  * FROM " + TABLE_SPOT + " WHERE "
                + COLUMN_SPOT_NAME + " LIKE '%" + spot_name + "%'";

        if(spot_height != null){
            selectQuery += " AND " + COLUMN_SPOT_HEIGHT + " >= " + spot_height;
        }

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c != null && c.moveToFirst())
        {
            do
            {
                Spot spot = new Spot();
                spot.setIdSp(c.getInt(c.getColumnIndex(COLUMN_SPOT_ID)));
                spot.setName(c.getString(c.getColumnIndex(COLUMN_SPOT_NAME)));
                if(c.isNull(c.getColumnIndex(COLUMN_SPOT_HEIGHT))){
                    spot.setHeight(null);
                }
                else{
                    spot.setHeight(c.getInt(c.getColumnIndex(COLUMN_SPOT_HEIGHT)));
                }
                spot.setDesc(c.getString(c.getColumnIndex(COLUMN_SPOT_DESC)));
                spots.add(spot);
            } while (c.moveToNext());
        }
        return spots;
    }

    public List<Spot> getAllSpots()
    {
        List<Spot> spots = new ArrayList<Spot>();
        String selectQuery = "SELECT  * FROM " + TABLE_SPOT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c != null && c.moveToFirst())
        {
            do
            {
                Spot spot = new Spot();
                spot.setIdSp(c.getInt(c.getColumnIndex(COLUMN_SPOT_ID)));
                spot.setName(c.getString(c.getColumnIndex(COLUMN_SPOT_NAME)));
                if(c.isNull(c.getColumnIndex(COLUMN_SPOT_HEIGHT))){
                    spot.setHeight(null);
                }
                else{
                    spot.setHeight(c.getInt(c.getColumnIndex(COLUMN_SPOT_HEIGHT)));
                }
                spot.setDesc(c.getString(c.getColumnIndex(COLUMN_SPOT_DESC)));

                spots.add(spot);
            } while (c.moveToNext());
        }
        return spots;
    }

    //------------------------- SECTIONS CRUD OPERATIONS -------------------------
    public boolean createSection(Section section)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SECTION_START_SPOT_ID, section.getIdSpStart());
        values.put(COLUMN_SECTION_END_SPOT_ID, section.getIdSpEnd());
        values.put(COLUMN_SECTION_LENGTH, section.getLength());
        values.put(COLUMN_SECTION_MOUNTAIN_RANGE, section.getMountainRange());
        values.put(COLUMN_SECTION_POINTS, section.getPointsTo());
        values.put(COLUMN_SECTION_RETURN_POINTS, section.getPointsFrom());
        values.put(COLUMN_SECTION_HEIGHT_DIFF, section.getHeightDiff());
        values.put(COLUMN_SECTION_ACTIVE_SINCE, section.getActiveSince());
        values.put(COLUMN_SECTION_DESC, section.getDesc());
        values.put(COLUMN_SECTION_OPEN, section.getOpen());

        long insert = db.insert(TABLE_SECTION, null, values);
        return insert != -1;
    }

    public boolean updateSection(Section section)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SECTION_START_SPOT_ID, section.getIdSpStart());
        values.put(COLUMN_SECTION_END_SPOT_ID, section.getIdSpEnd());
        values.put(COLUMN_SECTION_LENGTH, section.getLength());
        values.put(COLUMN_SECTION_MOUNTAIN_RANGE, section.getMountainRange());
        values.put(COLUMN_SECTION_POINTS, section.getPointsTo());
        values.put(COLUMN_SECTION_RETURN_POINTS, section.getPointsFrom());
        values.put(COLUMN_SECTION_HEIGHT_DIFF, section.getHeightDiff());
        values.put(COLUMN_SECTION_ACTIVE_SINCE, section.getActiveSince());
        values.put(COLUMN_SECTION_DESC, section.getDesc());
        values.put(COLUMN_SECTION_OPEN, section.getOpen());

        long update = db.update(TABLE_SECTION, values, COLUMN_SECTION_ID + " = ?",
                new String[] { String.valueOf(section.getIdSe())});
        return update != -1;
    }

    public boolean deleteSection(long section_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete(TABLE_SECTION, COLUMN_SECTION_ID + " = ?",
                new String[] { String.valueOf(section_id) });
        return delete != 0;
    }

    public Section getSection(long section_id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_SECTION + " WHERE "
                + COLUMN_SECTION_ID + " = " + section_id;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null && c.moveToFirst())
        {
            Section section = new Section();
            section.setIdSe(c.getInt(c.getColumnIndex(COLUMN_SECTION_ID)));
            section.setIdSpStart(c.getInt(c.getColumnIndex(COLUMN_SECTION_START_SPOT_ID)));
            section.setIdSpEnd(c.getInt(c.getColumnIndex(COLUMN_SECTION_END_SPOT_ID)));
            section.setLength(c.getInt(c.getColumnIndex(COLUMN_SECTION_LENGTH)));
            section.setMountainRange(c.getString(c.getColumnIndex(COLUMN_SECTION_MOUNTAIN_RANGE)));
            section.setPointsTo(c.getInt(c.getColumnIndex(COLUMN_SECTION_POINTS)));
            if(c.isNull(c.getColumnIndex(COLUMN_SECTION_RETURN_POINTS)))
            {
                section.setPointsFrom(null);
            }
            else
            {
                section.setPointsFrom(c.getInt(c.getColumnIndex(COLUMN_SECTION_RETURN_POINTS)));
            }
            section.setHeightDiff(c.getInt(c.getColumnIndex(COLUMN_SECTION_HEIGHT_DIFF)));
            section.setActiveSince(c.getString(c.getColumnIndex(COLUMN_SECTION_ACTIVE_SINCE)));
            section.setDesc(c.getString(c.getColumnIndex(COLUMN_SECTION_DESC)));
            section.setOpen(c.getInt(c.getColumnIndex(COLUMN_SECTION_OPEN)) == 1);

            return section;
        }
        return null;
    }

    public List<Section> getAllSections()
    {
        List<Section> sections = new ArrayList<Section>();
        String selectQuery = "SELECT  * FROM " + TABLE_SECTION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c != null && c.moveToFirst())
        {
            do
            {
                Section section = new Section();
                section.setIdSe(c.getInt(c.getColumnIndex(COLUMN_SECTION_ID)));
                section.setIdSpStart(c.getInt(c.getColumnIndex(COLUMN_SECTION_START_SPOT_ID)));
                section.setIdSpEnd(c.getInt(c.getColumnIndex(COLUMN_SECTION_END_SPOT_ID)));
                section.setLength(c.getInt(c.getColumnIndex(COLUMN_SECTION_LENGTH)));
                section.setMountainRange(c.getString(c.getColumnIndex(COLUMN_SECTION_MOUNTAIN_RANGE)));
                section.setPointsTo(c.getInt(c.getColumnIndex(COLUMN_SECTION_POINTS)));
                if(c.isNull(c.getColumnIndex(COLUMN_SECTION_RETURN_POINTS)))
                {
                    section.setPointsFrom(null);
                }
                else
                {
                    section.setPointsFrom(c.getInt(c.getColumnIndex(COLUMN_SECTION_RETURN_POINTS)));
                }
                section.setHeightDiff(c.getInt(c.getColumnIndex(COLUMN_SECTION_HEIGHT_DIFF)));
                section.setActiveSince(c.getString(c.getColumnIndex(COLUMN_SECTION_ACTIVE_SINCE)));
                section.setDesc(c.getString(c.getColumnIndex(COLUMN_SECTION_DESC)));
                section.setOpen(c.getInt(c.getColumnIndex(COLUMN_SECTION_OPEN)) == 1);
                sections.add(section);
            } while (c.moveToNext());
        }
        return sections;
    }

    public void closeDB()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
