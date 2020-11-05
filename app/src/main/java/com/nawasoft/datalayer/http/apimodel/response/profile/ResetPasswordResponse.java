package com.nawasoft.datalayer.http.apimodel.response.profile;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;

public class ResetPasswordResponse extends BaseResponse<ResetPasswordData> {

    public ResetPasswordResponse(String tag, boolean error, BaseMeta meta, BaseData<ResetPasswordData> data) {
        super(tag, error, meta, data);
    }
}
