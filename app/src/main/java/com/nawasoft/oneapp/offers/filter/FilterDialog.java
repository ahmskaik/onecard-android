package com.nawasoft.oneapp.offers.filter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nawasoft.base.BaseFragmentDialog;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnFilterCallback;
import com.nawasoft.datalayer.model.City;
import com.nawasoft.datalayer.model.Country;
import com.nawasoft.oneapp.offers.filter.mvp.FilterMVP;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class FilterDialog extends BaseFragmentDialog implements FilterMVP.View {

    @Inject
    FilterMVP.Presenter presenter;

    @BindView(R.id.countries_spinner)
    Spinner countriesSpinner;
    @BindView(R.id.country_hint)
    TextView countryHint;
    @BindView(R.id.cities_spinner)
    Spinner citiesSpinner;
    @BindView(R.id.city_hint)
    TextView cityHint;

    private ArrayAdapter<Country> countriesAdapter;
    private List<Country> countries = new ArrayList<>();
    private String selectedCountry = "";

    private ArrayAdapter<City> citiesAdapter;
    private List<City> cities = new ArrayList<>();
    private String selectedCityId = "";


    private OnFilterCallback filterCallback;

    public FilterDialog(OnFilterCallback filterCallback) {
        this.filterCallback = filterCallback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.filter_dialog_layout, container, false);
        ButterKnife.bind(this, view);

        initializeCountriesAdapter();
        countriesSpinner.setVisibility(View.GONE);
        initializeCitiesAdapter();
        citiesSpinner.setVisibility(View.GONE);

        presenter.setView(this);

        selectedCountry = presenter.getSavedCountry();
        selectedCityId = presenter.getSavedCity();

        presenter.getCountries();


        return view;
    }

    private void initializeCountriesAdapter() {
        countriesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, countries);
        countriesSpinner.setAdapter(countriesAdapter);

        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedCountry = countries.get(position).getCountryId();
                presenter.getCities();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }




    private void initializeCitiesAdapter() {
        citiesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, cities);
        citiesSpinner.setAdapter(citiesAdapter);
        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedCityId = cities.get(position).getCityId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private City getCityById(String id) {
        for (City city: cities) {
            if (id.equalsIgnoreCase(city.getCityId()))
                return city;
        }
        return new City("", "", "", "");
    }

    @OnClick({
            R.id.city_hint,
            R.id.country_hint,
    })
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.city_hint :
                showCitiesSpinner();
                citiesSpinner.performClick();
                break;
            case R.id.country_hint :
                showCountrySpinner();
                countriesSpinner.performClick();
                break;
        }
    }

    @OnClick(R.id.done_button)
    void onDoneClick() {
        presenter.saveCity(selectedCityId);
        presenter.saveCountry(selectedCountry);
        filterCallback.addFilter(selectedCountry, getCityById(selectedCityId));
        dismiss();
    }

    private void showCountrySpinner() {
        countryHint.setVisibility(View.GONE);
        countriesSpinner.setVisibility(View.VISIBLE);
    }

    private void showCitiesSpinner() {
        cityHint.setVisibility(View.GONE);
        citiesSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    // view methods


    @Override
    public String getCountryId() {
        return selectedCountry;
    }

    @Override
    public void setCountries(List<Country> countries) {
        this.countries.clear();
        this.countries.add(new Country("", getString(R.string.none)));
        this.countries.addAll(countries);
        countriesAdapter.notifyDataSetChanged();
        for (int i = 0; i < countries.size(); i++) {
            Country country = countries.get(i);
            if (country.getCountryId().equals(selectedCountry)) {
                countriesSpinner.setSelection(i + 1);
                showCountrySpinner();
            }
        }
    }

    @Override
    public void setCities(List<City> cities) {
        this.cities.clear();
        this.cities.add(new City("", getString(R.string.none), "", ""));
        this.cities.addAll(cities);
        citiesAdapter.notifyDataSetChanged();
        checkSelectedCity();
    }

    private void checkSelectedCity() {
        int i;
        for (i = 1; i <cities.size() ; i++) {
            City city = cities.get(i);
            if (city.getCityId().equals(selectedCityId)) {
                citiesSpinner.setSelection(i);
                showCitiesSpinner();
            }
        }
        if (i == cities.size()) {
            selectedCityId = "";
        }
    }

    @Override
    public void setCitiesLoading() {
        citiesSpinner.setVisibility(View.GONE);
        cityHint.setVisibility(View.VISIBLE);
        cityHint.setText(getString(R.string.please_wait));
        cityHint.setEnabled(false);
    }

    @Override
    public void setCitiesLoaded() {
        cityHint.setEnabled(true);
        cityHint.setText(getString(R.string.select_city));
    }
}
