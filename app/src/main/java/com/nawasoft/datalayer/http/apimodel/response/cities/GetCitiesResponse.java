package com.nawasoft.datalayer.http.apimodel.response.cities;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.model.City;

import java.util.List;

public class GetCitiesResponse extends BaseResponse<List<City>> {

    public GetCitiesResponse(String tag, boolean error, BaseMeta meta, BaseData<List<City>> data) {
        super(tag, error, meta, data);
    }
}
