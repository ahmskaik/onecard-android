package com.nawasoft.oneapp.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nawasoft.oneapp.R;
import com.nawasoft.base.BaseFragment;
import com.nawasoft.datalayer.model.Order;
import com.nawasoft.oneapp.callbacks.OnClickCallback;
import com.nawasoft.oneapp.orders.mvp.OrdersMVP;
import com.nawasoft.oneapp.rate.RateDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersFragment extends BaseFragment implements OrdersMVP.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    OrdersMVP.Presenter presenter;

    @BindView(R.id.orders_list)
    RecyclerView ordersList;
    @BindView(R.id.back_button)
    View backButton;
    @BindView(R.id.totals_layout)
    LinearLayout totalsLayout;
    @BindView(R.id.orders_swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<Order> orders = new ArrayList<>();
    private OrdersListAdapter ordersListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_fragment_layout, container, false);
        setProgressBar(view.findViewById(R.id.orders_progressBar));
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);


        initializeOrdersList();

        presenter.setView(this);
        presenter.getOrders();
        backButton.setOnClickListener(view1 -> getFragmentManager().popBackStack());
        return view;
    }


    @Override
    public void onRefresh() {
        presenter.getOrders();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }


    private void initializeOrdersList() {
        ordersListAdapter = new OrdersListAdapter(getContext(), orders, onRateClick);
        ordersList.setLayoutManager(new LinearLayoutManager(getContext()));
        ordersList.setAdapter(ordersListAdapter);
    }

    private OnClickCallback onRateClick = position -> {
        if (orders.get(position).getRate() != null) {
            showMessage(getString(R.string.already_rated));
        } else {
            RateDialog rateDialog = RateDialog.getInstance(orders.get(position));
            rateDialog.show(getFragmentManager(), "rate_dialog");
        }
    };

    private void addTotalView(String total) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.order_total_layout, null);
        ((TextView) view.findViewById(R.id.total)).setText(total);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin = (int) getContext().getResources().getDimension(R.dimen.margin_medium);
        params.setMargins(margin, 0, margin, margin);
        totalsLayout.addView(view, params);
    }

    // view methods
    @Override
    public void setOrders(List<Order> orders) {
        System.out.println("added");
        this.orders.clear();
        this.orders.addAll(orders);
        ordersListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setLoaded() {
        super.setLoaded();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setTotals(List<String> totals) {
        totalsLayout.removeAllViews();
        for (String total: totals)
            addTotalView(total);
    }

}
