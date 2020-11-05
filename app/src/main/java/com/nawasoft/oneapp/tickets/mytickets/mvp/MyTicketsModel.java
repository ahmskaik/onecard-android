package com.nawasoft.oneapp.tickets.mytickets.mvp;

import com.nawasoft.datalayer.model.MyTicket;


import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class MyTicketsModel implements MyTicketsMVP.Model {
    private MyTicketsMVP.Repository repository;

    public MyTicketsModel(MyTicketsMVP.Repository repository) {
        this.repository = repository;
    }

    @Override
    public PublishSubject<List<MyTicket>> getMyTickets() {
        return repository.getMyTickets();
    }
}
