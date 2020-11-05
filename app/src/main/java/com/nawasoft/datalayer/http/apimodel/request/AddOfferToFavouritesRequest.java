package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class AddOfferToFavouritesRequest extends BaseRequest {
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("cardId")
    private String offerId;

    public AddOfferToFavouritesRequest(String tag, String language, String memberId, String offerId) {
        super(tag, language);
        this.memberId = memberId;
        this.offerId = offerId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }
}
