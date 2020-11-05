package com.nawasoft.oneapp.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.datalayer.model.City;
import com.nawasoft.oneapp.LockableScrollView;
import com.nawasoft.oneapp.R;
import com.nawasoft.base.BaseFragment;
import com.nawasoft.oneapp.callbacks.OnBackPressedCallback;
import com.nawasoft.oneapp.callbacks.OnCategoryClickCallback;
import com.nawasoft.oneapp.callbacks.OnFilterCallback;
import com.nawasoft.oneapp.callbacks.OnMarkerClickCallback;
import com.nawasoft.oneapp.callbacks.OnOfferClickCallback;
import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.oneapp.offerfragmnet.OfferFragment;
import com.nawasoft.oneapp.offers.adapter.CategoriesAdapter;
import com.nawasoft.oneapp.offers.adapter.OffersListAdapter;
import com.nawasoft.oneapp.offers.filter.FilterDialog;
import com.nawasoft.oneapp.offers.mapfragment.OfferMapFragment;
import com.nawasoft.oneapp.offers.mvp.OffersMVP;
import com.nawasoft.oneapp.util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OffersFragment extends BaseFragment implements OffersMVP.View,
        SwipeRefreshLayout.OnRefreshListener, OnFilterCallback {
    @Inject
    OffersMVP.Presenter presenter;

    @BindView(R.id.offers_categories_list)
    RecyclerView categoriesRecyclerView;
    @BindView(R.id.offers_list)
    RecyclerView offersRecyclerView;
    @BindView(R.id.map_container)
    FrameLayout mapContainer;
    @BindView(R.id.offer_scrollView)
    LockableScrollView scrollView;
    @BindView(R.id.offers_swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.offers_map_button)
    MaterialCardView mapButton;
    @BindView(R.id.offers_list_button)
    MaterialCardView listButton;
    @BindView(R.id.search_input)
    EditText searchInput;
    @BindView(R.id.offers_filter_button)
    MaterialCardView filterButton;

    public static String CATEGORY_ID = "category_id";
    public static String MAP_MODE = "map_mode";
    public static String SEARCH_QUERY = "search_mode";

    private List<Category> categories = new ArrayList<>();
    private List<Offer> offers; // all offers (without filter)
    private List<Offer> filteredOffers;

    private CategoriesAdapter categoriesAdapter;
    private OffersListAdapter offersAdapter;


    private String categoryId = "";
    private String countryId = "";
    private String cityId = "";
    private boolean mapMode = false;
    private boolean mustRefresh = false;

    private OnBackPressedCallback onBackPressedCallback;

    public static OffersFragment getInstanceByCategory(OnBackPressedCallback callback, String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_ID, categoryId);
        return getInstance(callback, bundle);
    }

    public static OffersFragment getInstanceByMapMode(OnBackPressedCallback callback, boolean mapMode) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MAP_MODE, mapMode);
        return getInstance(callback, bundle);
    }

    public static OffersFragment getInstanceBySearch(OnBackPressedCallback callback, String searchQuery) {
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY, searchQuery);
        return getInstance(callback, bundle);
    }


    private static OffersFragment getInstance(OnBackPressedCallback callback, Bundle bundle) {
        OffersFragment fragment = new OffersFragment(callback);
        fragment.setArguments(bundle);
        return fragment;
    }

    public OffersFragment(OnBackPressedCallback onBackPressedCallback) {
        this.onBackPressedCallback = onBackPressedCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offers_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        setProgressBar(view.findViewById(R.id.offers_progressBar));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);

        mapButton.setOnClickListener(view1 -> showMap());
        listButton.setOnClickListener(view1 -> showList());
        filterButton.setOnClickListener(view1 -> {
            new FilterDialog(this).show(getFragmentManager(), "filter_dialog");
        });



        presenter.setView(this);
        presenter.getSavedFilter(); // country & city
        presenter.getCategories();

        checkParameters();

        initializeCategoriesList();
        initializeOffersList();


        searchInput.addTextChangedListener(this);

        if (!mapMode)
            presenter.getOffers();
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
        for (Category category: categories)
            category.setSelected(false);
    }

    private void checkParameters() {
        if (getArguments() == null)
            return;
        categoryId = getArguments().getString(CATEGORY_ID, "");
        mapMode = getArguments().getBoolean(MAP_MODE, false);
        String searchQuery = getArguments().getString(SEARCH_QUERY, "");
        if (!searchQuery.isEmpty()) {
            searchInput.setText(searchQuery);
        }
        if (mapMode)
            showMap();
    }

    private void showList() {
        offersRecyclerView.setVisibility(View.VISIBLE);
        scrollView.setScrollingEnabled(true);
        swipeRefreshLayout.setEnabled(true);
        mapButton.setVisibility(View.VISIBLE);
        listButton.setVisibility(View.GONE);
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.map_container);
        if (fragment != null) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }
        mapContainer.setVisibility(View.GONE);
        mapMode = false;
        if (offers == null || mustRefresh) {
            presenter.getOffers();
            mustRefresh = false;
        }
    }

    private void showMap() {
        offersRecyclerView.setVisibility(View.GONE);
        scrollView.setScrollingEnabled(false);
        swipeRefreshLayout.setEnabled(false);
        mapContainer.setVisibility(View.VISIBLE);
        mapButton.setVisibility(View.GONE);
        listButton.setVisibility(View.VISIBLE);
        Fragment fragment = OfferMapFragment.getInstance(onMarkerClickCallback, categoryId, countryId, cityId);
        Util.addFragment(getChildFragmentManager(), R.id.map_container, fragment);
        mapMode = true;
    }

    private OnMarkerClickCallback onMarkerClickCallback = this::openOffer;

    private void initializeCategoriesList() {
        categoriesAdapter = new CategoriesAdapter(getContext(), categories, categoryClickCallback);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        categoriesRecyclerView.setAdapter(categoriesAdapter);
    }


    private OnCategoryClickCallback categoryClickCallback = position -> {
        String newId = categories.get(position).getId();
        categoryId = categoryId.equalsIgnoreCase(newId) ? "" : newId;
        if (!mapMode)
            presenter.getOffers();
        else {
            mustRefresh = true;
            ((OfferMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map_container))
                    .setCategoryId(newId);
        }
    };

    private void initializeOffersList() {
        offersAdapter = new OffersListAdapter(getContext(), offerClickCallback);
        offersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        offersRecyclerView.setNestedScrollingEnabled(false);
        offersRecyclerView.setAdapter(offersAdapter);
    }

    private OnOfferClickCallback offerClickCallback = position -> {
        openOffer(filteredOffers.get(position).getOfferId());
    };

    private void openOffer(String id) {
        Fragment fragment = OfferFragment.getInstance(onBackPressedCallback, id);
        Util.addFragmentToBackStack(getFragmentManager(), R.id.fragment_container, fragment, "offer_fragment");
    }

    @Override
    public void addFilter(String countryId, City city) {
        this.countryId = countryId;
        this.cityId = city.getCityId();
        if (!mapMode)
            presenter.getOffers();
        else {
            mustRefresh = true;
            ((OfferMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map_container))
                    .setCountryAndCity(countryId, city);
        }
    }


    // view method

    @Override
    protected void doSearchQuery() {
        presenter.filterOffers();
    }


    @Override
    public void addCategory(Category category) {
        categories.add(category);
        if (category.isSelected())
            categoriesAdapter.setLastSelected(categories.size() - 1);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        if (offers != null)
            offers.clear();
        categories.clear();
        presenter.getOffers();
        presenter.getCategories();
    }

    @Override
    public void addOffers(List<Offer> offers) {
        if (this.offers == null) {
            this.offers = new ArrayList<>();
        }
        this.offers.clear();
        this.offers.addAll(offers);
        presenter.filterOffers();
    }

    @Override
    public String getCategoryId() {
        return categoryId;
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
    public String getCountryId() {
        return countryId;
    }

    @Override
    public String getCityId() {
        return cityId;
    }

    @Override
    public List<Offer> getAllOffers() {
        return offers;
    }

    @Override
    public void setFilteredOffers(List<Offer> offers) {
        if (filteredOffers == null) {
            filteredOffers = new ArrayList<>();
            offersAdapter.setOffers(filteredOffers);
        }
        filteredOffers.clear();
        filteredOffers.addAll(offers);
        offersAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSavedFilter(String countryId, String cityId) {
        this.countryId = countryId;
        this.cityId = cityId;
    }
}
