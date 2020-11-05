package com.nawasoft.datalayer.http.apimodel.response.rate;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class MakeRateResponse extends BaseResponse {

    public MakeRateResponse(String tag, boolean error, BaseMeta meta, BaseData data) {
        super(tag, error, meta, data);
    }
}
