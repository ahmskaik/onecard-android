package com.nawasoft.datalayer.http.apimodel.response.activecard;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class ActiveCardResponse extends BaseResponse<ActiveCardData> {

    public ActiveCardResponse(String tag, boolean error, BaseMeta meta, BaseData<ActiveCardData> data) {
        super(tag, error, meta, data);
    }
}
