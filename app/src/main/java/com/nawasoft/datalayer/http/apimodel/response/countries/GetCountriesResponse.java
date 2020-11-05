package com.nawasoft.datalayer.http.apimodel.response.countries;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetCountriesResponse extends BaseResponse<GetCountriesData> {

    public GetCountriesResponse(String tag, boolean error, BaseMeta meta, BaseData<GetCountriesData> data) {
        super(tag, error, meta, data);
    }
}
