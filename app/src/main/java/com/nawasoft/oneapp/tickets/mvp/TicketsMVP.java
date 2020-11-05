package com.nawasoft.oneapp.tickets.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.MyTicket;
import com.nawasoft.datalayer.model.Ticket;

import java.util.List;

import rx.Observable;

public interface TicketsMVP {
    interface View extends IBaseView {
        void setAllTickets(List<Ticket> tickets);
    }

    interface Presenter extends IBasePresenter {
        void getTickets();
    }

    interface Model {
        Observable<List<Ticket>> getAllTickets();
    }
}
