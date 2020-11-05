package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class GetOfferDetailsRequest extends BaseRequest {

    @SerializedName("cardId")
    private String cardId;

    public GetOfferDetailsRequest(String tag, String language, String cardId) {
        super(tag, language);
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
