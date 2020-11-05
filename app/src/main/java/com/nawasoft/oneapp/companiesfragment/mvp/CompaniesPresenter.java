package com.nawasoft.oneapp.companiesfragment.mvp;

import com.nawasoft.oneapp.R;
import com.nawasoft.base.IBaseView;
import com.nawasoft.datalayer.model.Company;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompaniesPresenter implements CompaniesMVP.Presenter {

    private CompaniesMVP.Model model;
    private CompaniesMVP.View view;

    private Subscription getCompaniesSubscriptions;

    public CompaniesPresenter(CompaniesMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getCompanies() {
        if (getCompaniesSubscriptions != null)
            getCompaniesSubscriptions.unsubscribe();

        List<Company> companies = new ArrayList<>();

        view.setLoading();
        getCompaniesSubscriptions = model.getCompanies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Company>() {
                    @Override
                    public void onCompleted() {
                        view.setLoaded();
                        view.addCompanies(companies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setLoaded();
                        view.addCompanies(companies);
                        view.showAlert(view.getString(R.string.no_internt_connection));
                    }

                    @Override
                    public void onNext(Company company) {
                        companies.add(company);
                    }
                });
    }

    @Override
    public void setView(IBaseView view) {
        this.view = (CompaniesMVP.View) view;
    }


    @Override
    public void filterCompanies() {
        List<Company> allCompanies = view.getAllCompanies();
        if (allCompanies == null)
            return;
        List<Company> filteredCompanies = new ArrayList<>();
        String query = view.getSearchQuery();
        for (Company company: allCompanies) {
            if (company.getTitle().toLowerCase().contains(query.toLowerCase()))
                filteredCompanies.add(company);
        }
        view.setFilteredCompanies(filteredCompanies);
    }

    @Override
    public void cancelCalls() {
        if (getCompaniesSubscriptions != null && !getCompaniesSubscriptions.isUnsubscribed()) {
            getCompaniesSubscriptions.unsubscribe();
        }
    }
}
