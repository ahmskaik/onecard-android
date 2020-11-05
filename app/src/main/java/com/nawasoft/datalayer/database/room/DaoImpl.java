package com.nawasoft.datalayer.database.room;

public class DaoImpl implements IDAOs {
    private AppDatabase database;

    public DaoImpl(AppDatabase database) {
        this.database = database;
    }

    @Override
    public CardDao getCardDao() {
        return database.getCardDao();
    }
}
