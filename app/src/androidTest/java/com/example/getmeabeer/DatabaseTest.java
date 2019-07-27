package com.example.getmeabeer;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.example.getmeabeer.model.beer.DatumBeer;
import com.example.getmeabeer.roomDatabase.BeerDao;
import com.example.getmeabeer.roomDatabase.BeerRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private BeerDao beerDao;
    private BeerRoomDatabase db;

    @Before
    public void createDb() {
        //Context context = ApplicationProvider.getApplicationContext();
        Context context = new Activity();
        db = Room.inMemoryDatabaseBuilder(context, BeerRoomDatabase.class).build();
        beerDao = db.beerDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        DatumBeer beer = new DatumBeer();
        beer.setId("2");
        beer.setName("testbeer");
        beer.setAbv("1.5");
        beerDao.insert(beer);

        List<DatumBeer> beerRet = beerDao.getAllBeers();
        assertThat(beerRet.get(0).getId(), equals("2"));
    }
}

