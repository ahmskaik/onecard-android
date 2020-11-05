package com.nawasoft.datalayer.http.apimodel.response.login;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class ForgotPasswordResponse extends BaseResponse<ForgotPasswordData> {

    public ForgotPasswordResponse(String tag, boolean error, BaseMeta meta, BaseData<ForgotPasswordData> data) {
        super(tag, error, meta, data);
    }
}
