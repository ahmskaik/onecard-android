package com.nawasoft.datalayer.database;

import com.nawasoft.datalayer.model.MyTicket;

import java.util.List;


public interface IDatabaseService {
    List<MyTicket> getCards();

    void insert(MyTicket card);

    void insert(List<MyTicket> cards);

    void deleteAllTickets();
}
