package com.nawasoft.datalayer.http.apimodel.response.editprofile;

import com.nawasoft.datalayer.http.apimodel.response.base.BaseData;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseMeta;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.model.User;

import java.util.List;

public class EditProfileResponse extends BaseResponse<List<User>> {

    public EditProfileResponse(String tag, boolean error, BaseMeta meta, BaseData<List<User>> data) {
        super(tag, error, meta, data);
    }
}
