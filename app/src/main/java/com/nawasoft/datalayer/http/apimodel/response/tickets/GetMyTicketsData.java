package com.nawasoft.datalayer.http.apimodel.response.tickets;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.model.MyTicket;

import java.util.List;

public class GetMyTicketsData {
    @SerializedName("ticketPhotoLink")
    private String baseTicketPhotoLink;
    @SerializedName("QRPhotoLink")
    private String qrBasePhotoLink;
    @SerializedName("data")
    private List<MyTicket> tickets;

    public GetMyTicketsData(String baseTicketPhotoLink, String qrBasePhotoLink, List<MyTicket> tickets) {
        this.baseTicketPhotoLink = baseTicketPhotoLink;
        this.qrBasePhotoLink = qrBasePhotoLink;
        this.tickets = tickets;
    }

    public String getBaseTicketPhotoLink() {
        return baseTicketPhotoLink;
    }

    public void setBaseTicketPhotoLink(String baseTicketPhotoLink) {
        this.baseTicketPhotoLink = baseTicketPhotoLink;
    }

    public String getQrBasePhotoLink() {
        return qrBasePhotoLink;
    }

    public void setQrBasePhotoLink(String qrBasePhotoLink) {
        this.qrBasePhotoLink = qrBasePhotoLink;
    }

    public List<MyTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<MyTicket> tickets) {
        this.tickets = tickets;
    }
}
