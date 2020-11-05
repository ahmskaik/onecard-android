package com.nawasoft.companyapp.main.qrcode;

public interface QrCodeCallBack {

    void onResult(String result);
    void cancelQr();
}
