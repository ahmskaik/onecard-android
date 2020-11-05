package com.nawasoft.datalayer.http.apimodel.response.oreders;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.model.CurrencyTotal;

import java.util.List;

public class GetAllTotalsResponse extends BaseResponse<List<CurrencyTotal>> {
    public GetAllTotalsResponse(String tag, boolean error, BaseMeta meta, BaseData<List<CurrencyTotal>> data) {
        super(tag, error, meta, data);
    }
}
