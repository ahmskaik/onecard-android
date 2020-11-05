package com.nawasoft.datalayer.http.apimodel.response.categories;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class GetCategoriesResponse extends BaseResponse<GetCategoriesResponseData> {

    public GetCategoriesResponse(String tag, boolean error, BaseMeta meta, BaseData<GetCategoriesResponseData> data) {
        super(tag, error, meta, data);
    }
}
