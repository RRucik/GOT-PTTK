package com.example.gotpttk;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gotpttk.model.dbHelper.DatabaseHelper;
import com.example.gotpttk.model.dbModels.Section;
import com.example.gotpttk.model.dbModels.Spot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TestSectionsDbOperations
{
    private DatabaseHelper databaseHelper;
    private Spot startingSpot;
    private Spot endingSpot;
    private Section sectionCorrect;
    private Section sectionWrong;
    private Integer idStart;
    private Integer idEnd;
    private Integer sectionId;

    @Before
    public void initializeTest()
    {
        Context context = ApplicationProvider.getApplicationContext();
        databaseHelper = new DatabaseHelper(context);
        startingSpot = new Spot("Spot start", 50, "Nice spot");
        endingSpot = new Spot("Spot end", null, null);
        databaseHelper.createSpot(startingSpot);
        databaseHelper.createSpot(endingSpot);
        idStart = databaseHelper.getSpotWithName(startingSpot.getName()).getIdSp();
        idEnd = databaseHelper.getSpotWithName(endingSpot.getName()).getIdSp();
        sectionCorrect = new Section(idStart, idEnd, 200, "Alps", 9, 5, "20/01/2020", "Beautiful", null);
        sectionWrong = new Section(null, idEnd, -2, "", null, null, "", "", null);
    }

    @After
    public void endTest()
    {
        databaseHelper.deleteSpot(idStart);
        databaseHelper.deleteSpot(idEnd);
        databaseHelper.closeDB();
    }

    @Test
    public void testAddCorrectSection()
    {
        assertTrue(databaseHelper.createSection(sectionCorrect));
        sectionId = databaseHelper.getFilteredSections(startingSpot.getName(), endingSpot.getName(),
                200, "Alps", 9,  "20/01/2020").get(0).getIdSe();
        databaseHelper.deleteSection(sectionId);
    }

    @Test
    public void testAddWrongSection()
    {
        assertFalse(databaseHelper.createSection(sectionWrong));
    }

    @Test
    public void testUpdateSection()
    {
        databaseHelper.createSection(sectionCorrect);

        // Reading section value from db to get it's id
        sectionCorrect = databaseHelper.getFilteredSections(startingSpot.getName(), endingSpot.getName(),
                200, "Alps", 9,  "20/01/2020").get(0);

        // Checking if section has correct fields values
        assertEquals(startingSpot.getName(), databaseHelper.getSpot(sectionCorrect.getIdSpStart()).getName());
        assertEquals(endingSpot.getName(), databaseHelper.getSpot(sectionCorrect.getIdSpEnd()).getName());
        assertEquals("Alps", sectionCorrect.getMountainRange());
        assertEquals(new Integer(9), sectionCorrect.getPointsTo());

        // Changing section and updating it
        sectionCorrect.setIdSpStart(idEnd);
        sectionCorrect.setIdSpEnd(idStart);
        sectionCorrect.setMountainRange("Himalayas");
        sectionCorrect.setPointsTo(13);
        assertTrue(databaseHelper.updateSection(sectionCorrect));

        // Reading changed section from db
        sectionCorrect = databaseHelper.getFilteredSections(endingSpot.getName(), startingSpot.getName(),
                200, "Himalayas", 13,  "20/01/2020").get(0);

        // Checking if section was properly changed
        assertEquals(endingSpot.getName(), databaseHelper.getSpot(sectionCorrect.getIdSpStart()).getName());
        assertEquals(startingSpot.getName(), databaseHelper.getSpot(sectionCorrect.getIdSpEnd()).getName());
        assertEquals("Himalayas", sectionCorrect.getMountainRange());
        assertEquals(new Integer(13), sectionCorrect.getPointsTo());

        databaseHelper.deleteSection(sectionCorrect.getIdSe());

        // Resetting section fields values to it's default state
        sectionCorrect.setIdSpStart(idStart);
        sectionCorrect.setIdSpEnd(idEnd);
        sectionCorrect.setMountainRange("Alps");
        sectionCorrect.setPointsTo(9);
    }

    @Test
    public void testUpdateSectionInWrongWay()
    {
        databaseHelper.createSection(sectionCorrect);

        // Reading section value from db to get it's id
        sectionCorrect = databaseHelper.getFilteredSections(startingSpot.getName(), endingSpot.getName(),
                200, "Alps", 9,  "20/01/2020").get(0);

        // Changing section and updating it
        sectionCorrect.setIdSpStart(null);
        sectionCorrect.setIdSpEnd(null);
        sectionCorrect.setMountainRange(null);
        assertFalse(databaseHelper.updateSection(sectionCorrect));

        databaseHelper.deleteSection(sectionCorrect.getIdSe());

        sectionCorrect.setIdSpStart(idStart);
        sectionCorrect.setIdSpEnd(idEnd);
        sectionCorrect.setMountainRange("Alps");
    }

    @Test
    public void testDeleteSection()
    {
        databaseHelper.createSection(sectionCorrect);
        sectionId = databaseHelper.getFilteredSections(startingSpot.getName(), endingSpot.getName(),
                200, "Alps", 9,  "20/01/2020").get(0).getIdSe();
        assertTrue(databaseHelper.deleteSection(sectionId));
    }
}
