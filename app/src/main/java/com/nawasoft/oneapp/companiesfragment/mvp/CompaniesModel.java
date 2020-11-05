package com.nawasoft.oneapp.companiesfragment.mvp;

import com.nawasoft.datalayer.model.Company;

import rx.Observable;

public class CompaniesModel implements CompaniesMVP.Model {
    private CompaniesMVP.Repository repository;

    public CompaniesModel(CompaniesMVP.Repository repository) {
        this.repository = repository;
    }


    @Override
    public Observable<Company> getCompanies() {
        return repository.getCompanies();
    }
}
