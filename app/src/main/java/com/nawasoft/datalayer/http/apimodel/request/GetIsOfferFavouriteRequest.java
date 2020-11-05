package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetIsOfferFavouriteRequest extends BaseRequest {
    @SerializedName("cardId")
    private String offerId;
    @SerializedName("memberId")
    private String memberId;

    public GetIsOfferFavouriteRequest(String tag, String language, String offerId, String memberId) {
        super(tag, language);
        this.offerId = offerId;
        this.memberId = memberId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
