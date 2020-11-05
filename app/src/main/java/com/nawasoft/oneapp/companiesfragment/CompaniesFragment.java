package com.nawasoft.oneapp.companiesfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.base.BaseFragment;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnBackPressedCallback;
import com.nawasoft.oneapp.callbacks.OnCompanyClickCallback;
import com.nawasoft.oneapp.companiesfragment.adapter.CompaniesListAdapter;
import com.nawasoft.oneapp.companiesfragment.mvp.CompaniesMVP;
import com.nawasoft.oneapp.companyfragment.CompanyFragment;
import com.nawasoft.oneapp.util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompaniesFragment extends BaseFragment implements CompaniesMVP.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    CompaniesMVP.Presenter presenter;

    @BindView(R.id.companies_list)
    RecyclerView companiesRecyclerView;
    @BindView(R.id.companies_swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.companies_back_button)
    MaterialCardView backButton;
    @BindView(R.id.search_input)
    EditText searchInput;

    private CompaniesListAdapter companiesAdapter;
    private List<Company> companies; // companies (without filter)
    private List<Company> filteredCompanies;


    private OnBackPressedCallback onBackPressedCallback;

    public CompaniesFragment(OnBackPressedCallback onBackPressedCallback) {
        this.onBackPressedCallback = onBackPressedCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.companies_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        setProgressBar(view.findViewById(R.id.companies_progressBar));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        searchInput.addTextChangedListener(this);
        initializeCompaniesList();
        presenter.setView(this);
        presenter.getCompanies();

        backButton.setOnClickListener(view1 -> onBackPressedCallback.onBack());
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    private void initializeCompaniesList() {
        companiesAdapter = new CompaniesListAdapter(getContext(), onCompanyClickCallback);
        companiesRecyclerView.setNestedScrollingEnabled(false);
        companiesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        companiesRecyclerView.setAdapter(companiesAdapter);
    }

    private OnCompanyClickCallback onCompanyClickCallback = position -> {
        CompanyFragment fragment = CompanyFragment.getInstance(onBackPressedCallback,
                filteredCompanies.get(position).getCompanyId());
        Util.addFragmentToBackStack(getFragmentManager(), R.id.fragment_container, fragment, "company_fragment");
    };


    @Override
    public void onRefresh() {
        if (companies != null)
            companies.clear();
        presenter.getCompanies();
    }

    @Override
    protected void doSearchQuery() {
        presenter.filterCompanies();
    }

    // view methods

    @Override
    public void addCompanies(List<Company> companies) {
        if (this.companies == null) {
            this.companies = new ArrayList<>();
        }
        this.companies.clear();
        this.companies.addAll(companies);
        presenter.filterCompanies();
    }

    @Override
    public void setLoaded() {
        super.setLoaded();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public String getSearchQuery() {
        return searchInput.getText().toString();
    }

    @Override
    public List<Company> getAllCompanies() {
        return companies;
    }

    @Override
    public void setFilteredCompanies(List<Company> companies) {
        if (filteredCompanies == null) {
            filteredCompanies = new ArrayList<>();
            companiesAdapter.setCompanies(filteredCompanies);
        }
        filteredCompanies.clear();
        filteredCompanies.addAll(companies);
        companiesAdapter.notifyDataSetChanged();
    }
}
