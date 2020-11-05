package com.nawasoft.datalayer.http.apimodel.response.login;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class LoginResponse extends BaseResponse<LoginResponseData> {

    public LoginResponse(String tag, boolean error, BaseMeta meta, BaseData<LoginResponseData> data) {
        super(tag, error, meta, data);
    }
}
