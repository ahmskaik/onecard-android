package com.nawasoft.oneapp.tickets.mvp;

import com.nawasoft.datalayer.model.MyTicket;
import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Ticket;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TicketPresenter implements TicketsMVP.Presenter {
    private TicketsMVP.Model model;
    private TicketsMVP.View view;

    public TicketPresenter(TicketsMVP.Model model) {
        this.model = model;
    }

    private Subscription getTicketsSubscriptions;


    @Override
    public void getTickets() {
        if (getTicketsSubscriptions != null)
            getTicketsSubscriptions.unsubscribe();

        view.setLoading();
        getTicketsSubscriptions = model.getAllTickets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Ticket>>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(List<Ticket> tickets) {
                        view.setAllTickets(tickets);
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (TicketsMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (getTicketsSubscriptions != null && !getTicketsSubscriptions.isUnsubscribed())
            getTicketsSubscriptions.unsubscribe();
    }
}
