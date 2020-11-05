package com.nawasoft.oneapp.callbacks;

import com.nawasoft.datalayer.model.City;

public interface OnFilterCallback {
    void addFilter(String countryId, City city);
}
