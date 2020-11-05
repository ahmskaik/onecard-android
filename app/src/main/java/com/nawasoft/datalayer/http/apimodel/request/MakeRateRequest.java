package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class MakeRateRequest extends BaseRequest {
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("orderId")
    private String orderId;
    @SerializedName("rate")
    private String rate;
    @SerializedName("comment")
    private String comment;

    public MakeRateRequest(String tag, String language, String memberId, String orderId, String rate, String comment) {
        super(tag, language);
        this.memberId = memberId;
        this.orderId = orderId;
        this.rate = rate;
        this.comment = comment;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
