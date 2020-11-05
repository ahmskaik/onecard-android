package com.nawasoft.oneapp.companiesfragment.mvp;

import com.nawasoft.datalayer.model.Company;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.GetAllCompaniesRequest;
import com.nawasoft.datalayer.http.apimodel.response.companies.GetAllCompaniesResponse;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;
import rx.functions.Func1;

public class CompaniesRepository implements CompaniesMVP.Repository {

    private IApiService service;
    private ISharedPref sharedPref;

    public CompaniesRepository(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<Company> getCompanies() {
        Observable<GetAllCompaniesResponse> observable =
                service.getAllCompanies(new GetAllCompaniesRequest(ApiTags.GetAllCompanies.getValue()
                        , sharedPref.getLanguage()));

        return observable.flatMap((Func1<GetAllCompaniesResponse, Observable<Company>>) getAllCompaniesResponse -> {
            String basePhotoLink = getAllCompaniesResponse.getData().getData().getBasePhotoLink();

            for (Company company :
                    getAllCompaniesResponse.getData().getData().getCompanies()) {
                company.setCompanyPhotoLink(basePhotoLink + company.getCompanyPhotoLink());
            }

            return Observable.from(getAllCompaniesResponse.getData().getData().getCompanies());
        });
    }
}













