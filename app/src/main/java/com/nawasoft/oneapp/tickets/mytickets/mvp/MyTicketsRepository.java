package com.nawasoft.oneapp.tickets.mytickets.mvp;

import com.nawasoft.datalayer.database.IDatabaseService;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetMyTicketsRequest;
import com.nawasoft.datalayer.http.apimodel.response.tickets.GetMyTicketsData;
import com.nawasoft.datalayer.http.apimodel.response.tickets.GetMyTicketsResponse;
import com.nawasoft.datalayer.model.MyTicket;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MyTicketsRepository implements MyTicketsMVP.Repository {

    private IApiService service;
    private ISharedPref sharedPref;
    private IDatabaseService database;

    private PublishSubject<List<MyTicket>> publisher;

    public MyTicketsRepository(IApiService service, ISharedPref sharedPref, IDatabaseService database) {
        this.service = service;
        this.sharedPref = sharedPref;
        this.database = database;
    }

    @Override
    public PublishSubject<List<MyTicket>> getMyTickets() {
        publisher = PublishSubject.create();
        getTicketsFromNetwork();
        return publisher;
    }

    private void getTicketsFromNetwork() {
        Observable<GetMyTicketsResponse> observable = service.getMyTickets(
                new GetMyTicketsRequest(
                        ApiTags.GetMyTickets.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getMemberId())
                )
        );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetMyTicketsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetMyTicketsResponse ticketsResponse) {
                        GetMyTicketsData data = ticketsResponse.getData().getData();
                        if (data != null) {
                            String basePhotoLink = data.getBaseTicketPhotoLink();
                            String qrBasePhotoLink = data.getQrBasePhotoLink();
                            for (MyTicket ticket : data.getTickets()) {
                                ticket.setTicketPhotoLink(basePhotoLink + ticket.getTicketPhotoLink());
                                ticket.setQrPhotoLink(qrBasePhotoLink + ticket.getQrPhotoLink());
                            }
                            publisher.onNext(data.getTickets());
                            database.deleteAllTickets();
                            database.insert(data.getTickets());
                        } else
                            publisher.onNext(Collections.emptyList());
                        publisher.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println(e.getMessage());
                        getTicketsFromDatabase();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void getTicketsFromDatabase() {
        System.out.println("loaded from database");
        publisher.onNext(database.getCards());
        publisher.onComplete();
    }
}
