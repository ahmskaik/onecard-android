package com.nawasoft.datalayer.http.apimodel.response.currency;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.model.Currency;

import java.util.List;

public class GetCurrenciesResponse extends BaseResponse<List<Currency>> {

    public GetCurrenciesResponse(String tag, boolean error, BaseMeta meta, BaseData<List<Currency>> data) {
        super(tag, error, meta, data);
    }
}
