package com.nawasoft.datalayer.http.apimodel.request.base;

import com.google.gson.annotations.SerializedName;

public class BaseRequest {
    @SerializedName("tag")
    protected String tag;
    @SerializedName("language")
    protected String language;

    public BaseRequest(String tag, String language) {
        this.tag = tag;
        this.language = language;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
