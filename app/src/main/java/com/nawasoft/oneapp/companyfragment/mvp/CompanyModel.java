package com.nawasoft.oneapp.companyfragment.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyDetailsRequest;
import com.nawasoft.datalayer.http.apimodel.response.companydetails.GetCompanyDetailsData;
import com.nawasoft.datalayer.http.apimodel.response.companydetails.GetCompanyDetailsResponse;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;
import rx.functions.Func1;

public class CompanyModel implements CompanyMVP.Model {
    private IApiService apiService;
    private ISharedPref sharedPref;

    public CompanyModel(IApiService apiService, ISharedPref sharedPref) {
        this.apiService = apiService;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<GetCompanyDetailsData> getCompanyDetails(String id) {
        Observable<GetCompanyDetailsResponse> observable = apiService.getCompanyDetails(
                new GetCompanyDetailsRequest(
                        ApiTags.GetCompanyDetails.getValue(),
                        sharedPref.getLanguage(),
                        id
                )
        );
        return observable.flatMap((Func1<GetCompanyDetailsResponse, Observable<GetCompanyDetailsData>>) getCompanyDetailsResponse -> {
            GetCompanyDetailsData data = getCompanyDetailsResponse.getData().getData();
            String basePhotoLink = data.getBasePhotoLink();
            data.getCompanies().get(0).setCompanyPhotoLink(basePhotoLink + data.getCompanies().get(0).getCompanyPhotoLink());
            return Observable.just(data);
        });
    }
}
