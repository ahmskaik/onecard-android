package com.nawasoft.oneapp.companyfragment.mvp;

import com.nawasoft.datalayer.http.apimodel.response.companydetails.GetCompanyDetailsData;
import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Company;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompanyPresenter implements CompanyMVP.Presenter {

    private CompanyMVP.Model model;
    private CompanyMVP.View view;

    private Subscription getCompanyDetailsSubscription;

    public CompanyPresenter(CompanyMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getCompanyDetails() {
        view.setLoading();
        getCompanyDetailsSubscription = model.getCompanyDetails(view.getCompanyId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetCompanyDetailsData>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(GetCompanyDetailsData detailsData) {
                        view.setCompany(detailsData.getCompanies().get(0));
                        view.setCompanyBranches(detailsData.getBranches());
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (CompanyMVP.View) view;
    }

    @Override
    public void cancelCalls() {
        if (getCompanyDetailsSubscription != null && !getCompanyDetailsSubscription.isUnsubscribed()) {
            getCompanyDetailsSubscription.unsubscribe();
        }
    }
}
