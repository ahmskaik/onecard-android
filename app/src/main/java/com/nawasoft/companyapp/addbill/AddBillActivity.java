package com.nawasoft.companyapp.addbill;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nawasoft.base.BaseActivity;
import com.nawasoft.companyapp.addbill.mvp.AddBillMVP;
import com.nawasoft.companyapp.addbill.offeradapter.CompanyOffersListAdapter;
import com.nawasoft.companyapp.addbill.offeradapter.SelectItemCallback;
import com.nawasoft.datalayer.model.CompanyCard;
import com.nawasoft.datalayer.model.Currency;
import com.nawasoft.oneapp.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class AddBillActivity extends BaseActivity implements AddBillMVP.View {

    @Inject
    AddBillMVP.Presenter presenter;

    @BindView(R.id.currencies_spinner)
    Spinner currenciesSpinner;
    @BindView(R.id.company_offers_list)
    RecyclerView companyOffersRecyclerView;
    @BindView(R.id.back_button)
    View backButton;
    @BindView(R.id.bill_value_input)
    EditText billValue;
    @BindView(R.id.sale_value_input)
    EditText saleValue;
    @BindView(R.id.add_button)
    View addButton;

    public static String MEMBER_ID = "member_id";

    private List<Currency> currencies = new ArrayList<>();
    private ArrayAdapter<Currency> currenciesAdapter;
    private String selectedCurrency = "";

    private List<CompanyCard> companyOffers = new ArrayList<>();
    private CompanyOffersListAdapter companyOffersAdapter;
    private String selectedOffer = "";

    private String memberId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        setProgressBar(R.id.add_bill_progressBar);
        ButterKnife.bind(this);

        memberId = getIntent().getExtras().getString(MEMBER_ID, "");

        initializeCurrenciesSpinner();
        initializeOffersList();

        backButton.setOnClickListener(view -> onBackPressed());
        addButton.setOnClickListener(view -> presenter.addBill());

        presenter.setView(this);
        presenter.getCurrencies();
        presenter.getCompanyCards();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }


    private void initializeCurrenciesSpinner() {
        currenciesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencies);
        currenciesSpinner.setAdapter(currenciesAdapter);
        currenciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCurrency = currencies.get(i).getCurrencyId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initializeOffersList() {
        companyOffersAdapter = new CompanyOffersListAdapter(this, companyOffers, selectItemCallback);
        companyOffersRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        );
        companyOffersRecyclerView.setAdapter(companyOffersAdapter);
    }

    private SelectItemCallback selectItemCallback = position -> {
        selectedOffer = companyOffers.get(position).getCardI();
    };

    // view methods


    @Override
    public void setCurrencies(List<Currency> currencies) {
        this.currencies.clear();
        this.currencies.addAll(currencies);
        currenciesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCompanyCards(List<CompanyCard> companyCards) {
        this.companyOffers.clear();
        this.companyOffers.addAll(companyCards);
        companyOffersAdapter.notifyDataSetChanged();
    }

    @Override
    public String getBillValue() {
        return billValue.getText().toString();
    }

    @Override
    public String getSaleValue() {
        return saleValue.getText().toString();
    }

    @Override
    public String getCurrencyId() {
        return selectedCurrency;
    }

    @Override
    public String getOfferId() {
        return selectedOffer;
    }

    @Override
    public String getMemberId() {
        return memberId;
    }

    @Override
    public void billAdded() {
        billValue.setText("");
        saleValue.setText("");
        companyOffersAdapter.clearSelected();
        selectedOffer = "";
    }
}
