package com.nawasoft.oneapp.tickets.mytickets.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;

import com.nawasoft.datalayer.model.MyTicket;

import java.util.List;

import io.reactivex.subjects.PublishSubject;


public interface MyTicketsMVP {
    interface View extends IBaseView {
        void setMyTickets(List<MyTicket> tickets);
    }

    interface Presenter extends IBasePresenter {
        void getMyTickets();
    }

    interface Model {
        PublishSubject<List<MyTicket>> getMyTickets();
    }

    interface Repository {
        PublishSubject<List<MyTicket>> getMyTickets();
    }

}
