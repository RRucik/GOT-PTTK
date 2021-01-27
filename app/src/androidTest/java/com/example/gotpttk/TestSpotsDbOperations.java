package com.example.gotpttk;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.dbModels.Section;
import com.example.gotpttk.model.dbModels.Spot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TestSpotsDbOperations
{
    private DatabaseHelper databaseHelper;
    private Spot spot1;
    private Spot spot2;
    private Spot spot3;
    private Section section;

    @Before
    public void initializeTest()
    {
        Context context = ApplicationProvider.getApplicationContext();
        databaseHelper = new DatabaseHelper(context);
        spot1 = new Spot("Spot 1", 50, "Nice spot");
        spot2 = new Spot("Spot 2", null, null);
        spot3 = new Spot(null, 50, "What is that spot");
    }

    @After
    public void endTest()
    {
        databaseHelper.closeDB();
    }

    @Test
    public void testAddCorrectSpot()
    {
        assertTrue(databaseHelper.createSpot(spot1));
        databaseHelper.deleteSpot(databaseHelper.getSpotWithName(spot1.getName()).getIdSp());
    }

    @Test
    public void testAddCorrectSpotTwo()
    {
        assertTrue(databaseHelper.createSpot(spot2));
        databaseHelper.deleteSpot(databaseHelper.getSpotWithName(spot2.getName()).getIdSp());
    }

    @Test
    public void testAddWrongSpot()
    {
        assertFalse(databaseHelper.createSpot(spot3));
    }

    @Test
    public void testAddDuplicateSpot()
    {
        databaseHelper.createSpot(spot1);
        assertFalse(databaseHelper.createSpot(spot1));
        databaseHelper.deleteSpot(databaseHelper.getSpotWithName(spot1.getName()).getIdSp());
    }

    @Test
    public void testUpdateSpot()
    {
        databaseHelper.createSpot(spot1);

        // Reading spot value from db to get it's id
        spot1 = databaseHelper.getSpotWithName(spot1.getName());

        // Checking if spot has correct fields values
        assertEquals("Spot 1", spot1.getName());
        assertEquals(new Integer(50), spot1.getHeight());
        assertEquals("Nice spot", spot1.getDesc());

        // Changing spot and updating it
        spot1.setName("Spot changed");
        spot1.setHeight(80);
        spot1.setDesc(null);
        assertTrue(databaseHelper.updateSpot(spot1));

        // Reading changed spot from db
        spot1 = databaseHelper.getSpotWithName(spot1.getName());

        // Checking if spot was properly changed
        assertEquals("Spot changed", spot1.getName());
        assertEquals(new Integer(80), spot1.getHeight());
        assertEquals(null, spot1.getDesc());
        databaseHelper.deleteSpot(databaseHelper.getSpotWithName(spot1.getName()).getIdSp());

        // Resetting spot fields values to it's default state
        spot1.setName("Spot 1");
        spot1.setHeight(50);
        spot1.setDesc("Nice spot");
    }

    @Test
    public void testGetSpotWithName()
    {
        databaseHelper.createSpot(spot1);
        assertEquals(spot1.getName(), databaseHelper.getSpotWithName(spot1.getName()).getName());
        databaseHelper.deleteSpot(databaseHelper.getSpotWithName(spot1.getName()).getIdSp());
    }

    @Test
    public void testDeleteSpot()
    {
        databaseHelper.createSpot(spot1);
        assertTrue(databaseHelper.deleteSpot(databaseHelper.getSpotWithName(spot1.getName()).getIdSp()));
    }

    @Test
    public void testDeleteSectionSpot()
    {
        // Creating spots in db and section
        databaseHelper.createSpot(spot1);
        databaseHelper.createSpot(spot2);
        Integer idStart = databaseHelper.getSpotWithName(spot1.getName()).getIdSp();
        Integer idEnd = databaseHelper.getSpotWithName(spot2.getName()).getIdSp();
        section = new Section(idStart, idEnd, 0, "", 0, 0, "", "", null);
        databaseHelper.createSection(section);

        // Trying to delete spot that is part of section
        assertFalse(databaseHelper.deleteSpot(databaseHelper.getSpotWithName(spot1.getName()).getIdSp()));

        List<Section> sectionList = databaseHelper.getFilteredSections(spot1.getName(), spot2.getName(), 0, "", 0, "");

        databaseHelper.deleteSection(sectionList.get(0).getIdSe());

        // Trying to delete spot after the section was deleted
        assertTrue(databaseHelper.deleteSpot(idStart));
        assertTrue(databaseHelper.deleteSpot(idEnd));
    }

}