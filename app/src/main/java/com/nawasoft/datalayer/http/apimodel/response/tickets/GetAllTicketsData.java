package com.nawasoft.datalayer.http.apimodel.response.tickets;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.Ticket;

import java.util.List;

public class GetAllTicketsData {
    @SerializedName("ticketPhotoLink")
    private String baseTicketPhotoLink;
    @SerializedName("data")
    private List<Ticket> tickets;

    public GetAllTicketsData(String baseTicketPhotoLink, List<Ticket> tickets) {
        this.baseTicketPhotoLink = baseTicketPhotoLink;
        this.tickets = tickets;
    }

    public String getBaseTicketPhotoLink() {
        return baseTicketPhotoLink;
    }

    public void setBaseTicketPhotoLink(String baseTicketPhotoLink) {
        this.baseTicketPhotoLink = baseTicketPhotoLink;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
