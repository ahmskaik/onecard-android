package com.nawasoft.datalayer.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "card")
public class MyTicket {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("QRPhoto")
    private String qrPhotoLink;
    @SerializedName("thePhoto")
    private String ticketPhotoLink;
    @SerializedName("theTitle")
    private String title;
    @SerializedName("thePrice")
    private String price;
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("ticketId")
    private String ticketId;
    @SerializedName("expiredDate")
    private String expiredDate;

    public MyTicket(int id, String qrPhotoLink, String ticketPhotoLink, String title, String price, String memberId, String ticketId, String expiredDate) {
        this.id = id;
        this.qrPhotoLink = qrPhotoLink;
        this.ticketPhotoLink = ticketPhotoLink;
        this.title = title;
        this.price = price;
        this.memberId = memberId;
        this.ticketId = ticketId;
        this.expiredDate = expiredDate;
    }

    @Ignore
    public MyTicket(String qrPhotoLink,String ticketPhotoLink, String title, String price, String memberId, String ticketId, String expiredDate) {
        this.qrPhotoLink = qrPhotoLink;
        this.ticketPhotoLink = ticketPhotoLink;
        this.title = title;
        this.price = price;
        this.memberId = memberId;
        this.ticketId = ticketId;
        this.expiredDate = expiredDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQrPhotoLink() {
        return qrPhotoLink;
    }

    public void setQrPhotoLink(String qrPhotoLink) {
        this.qrPhotoLink = qrPhotoLink;
    }

    public String getTicketPhotoLink() {
        return ticketPhotoLink;
    }

    public void setTicketPhotoLink(String ticketPhotoLink) {
        this.ticketPhotoLink = ticketPhotoLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
}
