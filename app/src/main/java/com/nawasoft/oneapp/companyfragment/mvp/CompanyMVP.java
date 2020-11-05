package com.nawasoft.oneapp.companyfragment.mvp;

import com.nawasoft.base.IBasePresenter;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.http.apimodel.response.companydetails.GetCompanyDetailsData;
import com.nawasoft.datalayer.model.Branch;
import com.nawasoft.datalayer.model.Company;

import java.util.List;

import rx.Observable;

public interface CompanyMVP {
    interface View extends IBaseView {
        void setCompany(Company company);
        void setCompanyBranches(List<Branch> branches);
        String getCompanyId();
    }

    interface Presenter extends IBasePresenter {
        void getCompanyDetails();
    }

    interface Model {
        Observable<GetCompanyDetailsData> getCompanyDetails(String id);
    }
}
