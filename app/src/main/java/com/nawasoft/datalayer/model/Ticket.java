package com.nawasoft.datalayer.model;

import com.google.gson.annotations.SerializedName;

public class Ticket {
    @SerializedName("tiId")
    private String ticketId;
    @SerializedName("theTitle")
    private String title;
    @SerializedName("thePhoto")
    private String ticketPhotoLink;
    @SerializedName("thePrice")
    private String price;


    public Ticket(String ticketId, String title, String ticketPhotoLink, String price) {
        this.ticketId = ticketId;
        this.title = title;
        this.ticketPhotoLink = ticketPhotoLink;
        this.price = price;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTicketPhotoLink() {
        return ticketPhotoLink;
    }

    public void setTicketPhotoLink(String ticketPhotoLink) {
        this.ticketPhotoLink = ticketPhotoLink;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
