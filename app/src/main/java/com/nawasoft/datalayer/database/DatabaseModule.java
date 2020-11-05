package com.nawasoft.datalayer.database;

import android.content.Context;

import androidx.room.Room;

import com.nawasoft.datalayer.database.room.AppDatabase;
import com.nawasoft.datalayer.database.room.DaoImpl;
import com.nawasoft.datalayer.database.room.IDAOs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    IDAOs provideDAOs(AppDatabase database) {
        return new DaoImpl(database);
    }

    @Provides
    @Singleton
    IDatabaseService provideDatabaseService(IDAOs allDao) {
        return new DatabaseImpl(allDao);
    }
}
