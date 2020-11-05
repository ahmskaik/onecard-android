package com.nawasoft.datalayer.http.apimodel.response.register;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class RegisterResponse extends BaseResponse<RegisterResponseData> {

    public RegisterResponse(String tag, boolean error, BaseMeta meta, BaseData<RegisterResponseData> data) {
        super(tag, error, meta, data);
    }
}
