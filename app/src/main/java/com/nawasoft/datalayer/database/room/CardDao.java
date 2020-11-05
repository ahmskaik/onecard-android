package com.nawasoft.datalayer.database.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nawasoft.datalayer.model.MyTicket;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface CardDao {

    @Insert
    void insert(MyTicket card);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<MyTicket> cards);

    @Query("Select * from card")
    List<MyTicket> getAllCards();

    @Query("Delete from card")
    void deleteAllCards();
}
