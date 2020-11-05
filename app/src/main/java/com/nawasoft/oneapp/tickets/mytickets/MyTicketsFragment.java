package com.nawasoft.oneapp.tickets.mytickets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nawasoft.base.BaseFragment;
import com.nawasoft.datalayer.model.MyTicket;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnClickCallback;
import com.nawasoft.oneapp.tickets.mytickets.mvp.MyTicketsMVP;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTicketsFragment extends BaseFragment implements MyTicketsMVP.View, SwipeRefreshLayout.OnRefreshListener{
    @Inject
    MyTicketsMVP.Presenter presenter;


    @BindView(R.id.tickets_list)
    RecyclerView ticketsRecyclerView;
    @BindView(R.id.back_button)
    View backButton;
    @BindView(R.id.tickets_swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<MyTicket> tickets = new ArrayList<>();
    private MyTicketsListAdapter ticketsListAdapter;


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

        presenter.getMyTickets();

        backButton.setOnClickListener(view1 -> {
            getFragmentManager().popBackStack();
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    private void initializeTicketsList() {
        ticketsListAdapter = new MyTicketsListAdapter(getContext(), tickets, onTicketClick);
        ticketsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketsRecyclerView.setAdapter(ticketsListAdapter);
    }

    private OnClickCallback onTicketClick = position -> {
        Bundle bundle = new Bundle();
        bundle.putString(QrCodeDialog.QR_PHOTO_LINK, tickets.get(position).getQrPhotoLink());
        QrCodeDialog dialog = new QrCodeDialog();
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(), "qr_code_dialog");
    };


    @Override
    public void onRefresh() {
        presenter.getMyTickets();
    }

    @Override
    public void setMyTickets(List<MyTicket> tickets) {
        this.tickets.clear();
        this.tickets.addAll(tickets);
        ticketsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setLoaded() {
        super.setLoaded();
        swipeRefreshLayout.setRefreshing(false);
    }
}
