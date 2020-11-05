package com.nawasoft.oneapp.favouritefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.base.BaseFragment;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnBackPressedCallback;
import com.nawasoft.oneapp.callbacks.OnOfferClickCallback;
import com.nawasoft.oneapp.favouritefragment.mvp.FavouriteMVP;
import com.nawasoft.oneapp.offerfragmnet.OfferFragment;
import com.nawasoft.oneapp.offers.adapter.OffersListAdapter;
import com.nawasoft.oneapp.util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteFragment extends BaseFragment implements FavouriteMVP.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    FavouriteMVP.Presenter presenter;

    @BindView(R.id.favourite_offers_list)
    RecyclerView favouriteOffersRecyclerView;
    @BindView(R.id.favourite_offers_swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.favourite_back_button)
    MaterialCardView backButton;
    @BindView(R.id.search_input)
    EditText searchInput;

    private OnBackPressedCallback onBackPressedCallback;

    private List<Offer> offers;
    private List<Offer> filteredOffers;

    private OffersListAdapter offersListAdapter;

    public FavouriteFragment(OnBackPressedCallback onBackPressedCallback) {
        this.onBackPressedCallback = onBackPressedCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourite_offers_layout, container, false);
        ButterKnife.bind(this, view);
        setProgressBar(view.findViewById(R.id.favourite_offers_progressBar));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        searchInput.addTextChangedListener(this);
        initializeOffersList();

        presenter.setView(this);
        presenter.getFavouriteOffers();

        backButton.setOnClickListener(view1 -> onBackPressedCallback.onBack());
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    private void initializeOffersList() {
        offersListAdapter = new OffersListAdapter(getContext(), offerClickCallback);
        favouriteOffersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favouriteOffersRecyclerView.setNestedScrollingEnabled(false);
        favouriteOffersRecyclerView.setAdapter(offersListAdapter);
    }

    private OnOfferClickCallback offerClickCallback = position -> {
        Fragment fragment = OfferFragment.getInstance(onBackPressedCallback, filteredOffers.get(position).getOfferId());
        Util.addFragmentToBackStack(getFragmentManager(), R.id.fragment_container, fragment, "offer_fragment");
    };

    @Override
    protected void doSearchQuery() {
        presenter.filterOffers();
    }


    // view methods


    @Override
    public void onRefresh() {
        if (offers != null)
            offers.clear();
        offersListAdapter.notifyDataSetChanged();
        presenter.getFavouriteOffers();
    }

    @Override
    public void setOffers(List<Offer> offers) {
        if (this.offers == null) {
            this.offers = new ArrayList<>();

        }
        this.offers.clear();
        this.offers.addAll(offers);
        presenter.filterOffers();
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
    public List<Offer> getAllOffers() {
        return offers;
    }

    @Override
    public void setFilteredOffers(List<Offer> offers) {
        if (filteredOffers == null) {
            filteredOffers = new ArrayList<>();
            offersListAdapter.setOffers(filteredOffers);
        }
        filteredOffers.clear();
        filteredOffers.addAll(offers);
        offersListAdapter.notifyDataSetChanged();

    }
}
