package com.nawasoft.companyapp.reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nawasoft.base.BaseActivity;
import com.nawasoft.companyapp.reports.adapter.CompanyOrderListAdapter;
import com.nawasoft.companyapp.reports.mvp.CompanyOrdersMVP;
import com.nawasoft.datalayer.model.CompanyOrder;
import com.nawasoft.oneapp.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class CompanyOrdersActivity extends BaseActivity implements CompanyOrdersMVP.View,
        SwipeRefreshLayout.OnRefreshListener {

    @Inject
    CompanyOrdersMVP.Presenter presenter;

    @BindView(R.id.orders_list)
    RecyclerView ordersRecyclerView;
    @BindView(R.id.orders_swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.back_button)
    View backButton;
    @BindView(R.id.totals_layout)
    LinearLayout totalsLayout;

    private CompanyOrderListAdapter adapter;
    private List<CompanyOrder> companyOrders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_orders);
        setProgressBar(R.id.company_orders_progressBar);
        ButterKnife.bind(this);
        initializeOrdersList();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        presenter.setView(this);
        presenter.getCompanyOrders();

        backButton.setOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    private void initializeOrdersList() {
        adapter = new CompanyOrderListAdapter(this, companyOrders);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersRecyclerView.setAdapter(adapter);
        ordersRecyclerView.setNestedScrollingEnabled(true);
    }


    private void addTotalView(String total) {
        View view = LayoutInflater.from(this).inflate(R.layout.order_total_layout, null);
        ((TextView) view.findViewById(R.id.total)).setText(total);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin = (int) getResources().getDimension(R.dimen.margin_medium);
        params.setMargins(margin, 0, margin, margin);
        totalsLayout.addView(view, params);
    }


    // view methods


    @Override
    public void onRefresh() {
        presenter.getCompanyOrders();
    }

    @Override
    public void setOrders(List<CompanyOrder> companyOrders) {
        this.companyOrders.clear();
        this.companyOrders.addAll(companyOrders);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setLoaded() {
        super.setLoaded();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setTotals(List<String> totals) {
        totalsLayout.removeAllViews();
        for (String total : totals) {
            addTotalView(total);
        }
    }
}
