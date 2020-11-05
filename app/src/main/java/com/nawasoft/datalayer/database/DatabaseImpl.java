package com.nawasoft.datalayer.database;

import com.nawasoft.datalayer.database.room.IDAOs;
import com.nawasoft.datalayer.model.MyTicket;

import java.util.List;


public class DatabaseImpl implements IDatabaseService {
    private IDAOs allDao;

    public DatabaseImpl(IDAOs allDao) {
        this.allDao = allDao;
    }

    @Override
    public List<MyTicket> getCards() {
        return allDao.getCardDao().getAllCards();
    }

    @Override
    public void insert(MyTicket card) {
        allDao.getCardDao().insert(card);
    }

    @Override
    public void insert(List<MyTicket> cards) {
        allDao.getCardDao().insert(cards);
    }

    @Override
    public void deleteAllTickets() {
        allDao.getCardDao().deleteAllCards();
    }
}
