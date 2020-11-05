package com.nawasoft.oneapp.tickets.mytickets.mvp;

import com.nawasoft.base.IBaseView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MyTicketPresenter implements MyTicketsMVP.Presenter {
    private MyTicketsMVP.Model model;
    private MyTicketsMVP.View view;

    public MyTicketPresenter(MyTicketsMVP.Model model) {
        this.model = model;
    }

    private Disposable getTicketsDisposable;

    @Override
    public void getMyTickets() {
        if (getTicketsDisposable != null)
            getTicketsDisposable.dispose();


        view.setLoading();

        getTicketsDisposable = model.getMyTickets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        tickets -> view.setMyTickets(tickets),
                        throwable -> System.err.println(throwable.getMessage()),
                        () -> view.setLoaded()
                );
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (MyTicketsMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (getTicketsDisposable != null && !getTicketsDisposable.isDisposed())
            getTicketsDisposable.dispose();
    }
}
