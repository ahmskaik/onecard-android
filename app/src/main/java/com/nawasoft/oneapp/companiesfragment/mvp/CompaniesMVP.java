package com.nawasoft.oneapp.companiesfragment.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Company;

import java.util.List;

import rx.Observable;

public interface CompaniesMVP {

    interface View extends IBaseView {
        void addCompanies(List<Company> companies);
        String getSearchQuery();
        List<Company> getAllCompanies();
        void setFilteredCompanies(List<Company> companies);
    }

    interface Presenter extends IBasePresenter {
        void getCompanies();
        void filterCompanies();
    }

    interface Model {
        Observable<Company> getCompanies();
    }

    interface Repository {
        Observable<Company> getCompanies();
    }

}
