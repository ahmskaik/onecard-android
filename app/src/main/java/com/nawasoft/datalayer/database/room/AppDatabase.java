package com.nawasoft.datalayer.database.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.nawasoft.datalayer.database.Converter;
import com.nawasoft.datalayer.model.MyTicket;

@Database(entities = {MyTicket.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    abstract CardDao getCardDao();
}
