package com.nawasoft.datalayer.http.apimodel.request;

import com.google.gson.annotations.SerializedName;
import com.nawasoft.datalayer.http.apimodel.request.base.BaseRequest;

public class AddBillRequest extends BaseRequest {
    @SerializedName("companyId")
    private String companyId;
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("billValue")
    private String billValue;
    @SerializedName("saleValue")
    private String saleValue;
    @SerializedName("currencyId")
    private String currencyId;
    @SerializedName("cardId")
    private String offerId;

    public AddBillRequest(String tag, String language, String companyId, String memberId, String billValue, String saleValue, String currencyId, String offerId) {
        super(tag, language);
        this.companyId = companyId;
        this.memberId = memberId;
        this.billValue = billValue;
        this.saleValue = saleValue;
        this.currencyId = currencyId;
        this.offerId = offerId;
    }
}
