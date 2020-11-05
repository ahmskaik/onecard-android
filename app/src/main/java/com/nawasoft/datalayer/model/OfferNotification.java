package com.nawasoft.datalayer.model;

import java.util.Map;

public class OfferNotification {
    private String offerId;
    private String notificationTitle;
    private String notificationBody;

    public OfferNotification(String offerId, String notificationTitle, String notificationBody) {
        this.offerId = offerId;
        this.notificationTitle = notificationTitle;
        this.notificationBody = notificationBody;
    }

    public OfferNotification(Map<String, String> map) {
        this(map.get("offerId"), map.get("title"), map.get("body"));
    }

    public String getOfferId() {
        return offerId;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public String getNotificationBody() {
        return notificationBody;
    }
}
