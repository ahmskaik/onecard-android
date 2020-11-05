package com.nawasoft.base;

import androidx.annotation.StringRes;

public interface IBaseView {
    void setLoading();
    void setLoaded();
    void showMessage(String message);
    void showAlert(String message);
    String getString(@StringRes int id);
    boolean isLoading();
}
