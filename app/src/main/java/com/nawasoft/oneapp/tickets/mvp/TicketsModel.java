package com.nawasoft.oneapp.tickets.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetMyTicketsRequest;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;
import com.nawasoft.datalayer.http.apimodel.response.tickets.GetAllTicketsData;
import com.nawasoft.datalayer.http.apimodel.response.tickets.GetAllTicketsResponse;
import com.nawasoft.datalayer.http.apimodel.response.tickets.GetMyTicketsData;
import com.nawasoft.datalayer.http.apimodel.response.tickets.GetMyTicketsResponse;
import com.nawasoft.datalayer.model.MyTicket;
import com.nawasoft.datalayer.model.Ticket;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class TicketsModel implements TicketsMVP.Model {
    private IApiService service;
    private ISharedPref sharedPref;

    public TicketsModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<List<Ticket>> getAllTickets() {
        return service.getAllTickets(
                new BaseRequest(
                        ApiTags.GetAllTickets.getValue(),
                        sharedPref.getLanguage()
                )
        ).flatMap((Func1<GetAllTicketsResponse, Observable<List<Ticket>>>) getAllTicketsResponse -> {
            GetAllTicketsData data = getAllTicketsResponse.getData().getData();
            if (data == null)
                return Observable.empty();
            String basePhotoLink = data.getBaseTicketPhotoLink();

            for (Ticket ticket: data.getTickets()) {
                ticket.setTicketPhotoLink(basePhotoLink + ticket.getTicketPhotoLink());
            }
           return Observable.just(data.getTickets());
        });
    }

}
