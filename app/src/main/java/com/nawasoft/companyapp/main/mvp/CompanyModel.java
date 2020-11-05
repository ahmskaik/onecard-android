package com.nawasoft.companyapp.main.mvp;

import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.CheckBarcodeRequest;
import com.nawasoft.datalayer.http.apimodel.response.barcode.CheckBarcodeResponse;
import com.nawasoft.datalayer.http.companyservice.IApiCompanyService;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;

public class CompanyModel implements CompanyMainMVP.Model {

    private IApiCompanyService service;
    private ISharedPref sharedPref;

    public CompanyModel(IApiCompanyService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<CheckBarcodeResponse> checkQrCode(String serialNumber) {
        return service.checkBarcode(
                new CheckBarcodeRequest(
                        ApiTags.CheckBarcodeInfo.getValue(),
                        sharedPref.getLanguage(),
                        serialNumber
                )
        );
    }

    @Override
    public void logout() {
        sharedPref.setLoggedOut();
    }
}
