package com.nawasoft.datalayer.http.apimodel.response.homeslider;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetHomeSliderResponse extends BaseResponse<GetHomeSliderResponseData> {

    public GetHomeSliderResponse(String tag, boolean error, BaseMeta meta, BaseData<GetHomeSliderResponseData> data) {
        super(tag, error, meta, data);
    }
}
