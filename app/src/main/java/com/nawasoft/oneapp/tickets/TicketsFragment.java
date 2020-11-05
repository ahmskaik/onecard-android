package com.nawasoft.oneapp.tickets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nawasoft.base.BaseFragment;
import com.nawasoft.datalayer.model.Ticket;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnClickCallback;
import com.nawasoft.oneapp.payment.OptionsMenuFragment;
import com.nawasoft.oneapp.tickets.mvp.TicketsMVP;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketsFragment extends BaseFragment implements TicketsMVP.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    TicketsMVP.Presenter presenter;

    @BindView(R.id.tickets_list)
    RecyclerView ticketsRecyclerView;
    @BindView(R.id.back_button)
    View backButton;
    @BindView(R.id.tickets_swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<Ticket> tickets = new ArrayList<>();
    private TicketsListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tickets_layout, container, false);
        setProgressBar(view.findViewById(R.id.tickets_progressBar));
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);


        initializeTicketsList();

        presenter.setView(this);

        presenter.getTickets();

        backButton.setOnClickListener(view1 -> {
            getFragmentManager().popBackStack();
        });

        return view;
    }

    private void initializeTicketsList() {
        adapter = new TicketsListAdapter(getContext(), tickets, onTicketClick);
        ticketsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketsRecyclerView.setAdapter(adapter);
    }

    private OnClickCallback onTicketClick = position -> {

//        new ActiveCardDialog().show(getFragmentManager(), "active_card_dialog");
        DialogFragment fragment = new OptionsMenuFragment();
        fragment.show(getFragmentManager(), "");
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }


    // view methods

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.getTickets();
    }

    @Override
    public void setAllTickets(List<Ticket> tickets) {
        this.tickets.clear();
        this.tickets.addAll(tickets);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setLoaded() {
        super.setLoaded();
        swipeRefreshLayout.setRefreshing(false);
    }
}
