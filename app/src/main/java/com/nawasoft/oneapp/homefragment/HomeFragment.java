package com.nawasoft.oneapp.homefragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.base.BaseFragment;
import com.nawasoft.datalayer.model.Category;
import com.nawasoft.datalayer.model.City;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.datalayer.model.HomeSlider;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.oneapp.FRAGMENTS;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnBackPressedCallback;
import com.nawasoft.oneapp.callbacks.OnCategoryClickCallback;
import com.nawasoft.oneapp.callbacks.OnCompanyClickCallback;
import com.nawasoft.oneapp.callbacks.OnFilterCallback;
import com.nawasoft.oneapp.callbacks.OnOfferClickCallback;
import com.nawasoft.oneapp.companyfragment.CompanyFragment;
import com.nawasoft.oneapp.homefragment.adapter.adsadapter.AdsViewPagerAdapter;
import com.nawasoft.oneapp.homefragment.adapter.categoriesadapter.CategoriesListAdapter;
import com.nawasoft.oneapp.homefragment.adapter.companyadapter.FeaturedCompanyListAdapter;
import com.nawasoft.oneapp.homefragment.adapter.offersadapter.FeaturedOfferListAdapter;
import com.nawasoft.oneapp.homefragment.mvp.HomeMVP;
import com.nawasoft.oneapp.offerfragmnet.OfferFragment;
import com.nawasoft.oneapp.offers.OffersFragment;
import com.nawasoft.oneapp.offers.filter.FilterDialog;
import com.nawasoft.oneapp.util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment implements HomeMVP.View, SwipeRefreshLayout.OnRefreshListener,
        OnFilterCallback {

    private static final String OFFER_ID = "offer_id";

    @Inject
    HomeMVP.Presenter presenter;

    @BindView(R.id.ads_viewPager)
    ViewPager adsViewPager;
    @BindView(R.id.categories_list)
    RecyclerView categoriesRecyclerView;
    @BindView(R.id.featured_offers_list)
    RecyclerView featuredOffersRecyclerView;
    @BindView(R.id.featured_companies_list)
    RecyclerView featuredCompaniesRecyclerView;
    @BindView(R.id.home_swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.home_map_button)
    MaterialCardView mapButton;
    @BindView(R.id.home_filter_button)
    View filterButton;
    @BindView(R.id.search_input)
    EditText searchInput;

    private AdsViewPagerAdapter adsViewPagerAdapter;
    private CategoriesListAdapter categoriesAdapter;
    private FeaturedOfferListAdapter featuredOffersAdapter;
    private FeaturedCompanyListAdapter featuredCompaniesAdapter;

    private List<HomeSlider> sliderList = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<Offer> featuredOffers = new ArrayList<>();
    private List<Company> featuredCompanies = new ArrayList<>();


    private OnBackPressedCallback onBackPressedCallback;

    private String offerId = ""; // offerId to open it

    public static HomeFragment getInstance(OnBackPressedCallback onBackPressedCallback, String offerId) {
        Bundle bundle = new Bundle();
        bundle.putString(OFFER_ID, offerId);
        HomeFragment homeFragment = new HomeFragment(onBackPressedCallback);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    public HomeFragment(OnBackPressedCallback onBackPressedCallback) {
        this.onBackPressedCallback = onBackPressedCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        setProgressBar(view.findViewById(R.id.home_progressBar));
        ButterKnife.bind(this, view);
        view.findViewById(R.id.home_layout).requestFocus();
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        initializeAdsPager();
        initializeCategoriesList();
        initializeFeatureOffersList();
        initializeFeaturedCompaniesList();

        fetchParams();

        presenter.setView(this);
        presenter.getHomeSlider();
        presenter.getFeaturedOffers();
        presenter.getCategories();
        presenter.getFeaturedCompanies();


        mapButton.setOnClickListener(view1 -> {
            Fragment fragment = OffersFragment.getInstanceByMapMode(onBackPressedCallback, true);
            Util.replaceFragment(getFragmentManager(), R.id.fragment_container, fragment, FRAGMENTS.OffersFragment.toString());
        });

        filterButton.setOnClickListener(view1 -> {
            new FilterDialog(this).show(getFragmentManager(), "filter_dialog");
        });


        searchInput.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Fragment fragment = OffersFragment.getInstanceBySearch(onBackPressedCallback, searchInput.getText().toString());
                Util.replaceFragment(getFragmentManager(), R.id.fragment_container, fragment, FRAGMENTS.OffersFragment.toString());
            }
            return false;
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
        adsViewPagerAdapter.cancelTimer();
    }

    private void fetchParams() {
        if (getArguments() != null) {
            offerId = getArguments().getString(OFFER_ID, "");
            if (!offerId.isEmpty())
                openOffer(offerId);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initializeAdsPager() {
        int padding = (int) getContext().getResources().getDimension(R.dimen.margin_medium);
        int paddingLarge = (int) getContext().getResources().getDimension(R.dimen.margin_large);
        int margin = (int) getContext().getResources().getDimension(R.dimen.margin_medium);
        adsViewPagerAdapter = new AdsViewPagerAdapter(getContext(), adsViewPager, sliderList);
        adsViewPager.setClipToPadding(false);
        adsViewPager.setPadding(padding, 10, paddingLarge, 10);
        adsViewPager.setPageMargin(margin);
        adsViewPager.setAdapter(adsViewPagerAdapter);
        adsViewPagerAdapter.setTimer();
        adsViewPager.setOnTouchListener((view, motionEvent) -> {
            swipeRefreshLayout.setEnabled(false);
            if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                swipeRefreshLayout.setEnabled(true);
            return false;
        });
    }

    private void initializeCategoriesList() {
        categoriesAdapter = new CategoriesListAdapter(getContext(), categories, categoryCallback);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        categoriesRecyclerView.setNestedScrollingEnabled(false);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
    }

    private OnCategoryClickCallback categoryCallback = position -> {
        categories.get(position).setSelected(true);
        Fragment fragment = OffersFragment.getInstanceByCategory(onBackPressedCallback, categories.get(position).getId());
        Util.replaceFragment(getFragmentManager(), R.id.fragment_container, fragment, FRAGMENTS.OffersFragment.toString());
    };

    private void initializeFeatureOffersList() {
        featuredOffersAdapter = new FeaturedOfferListAdapter(getContext(), featuredOffers, offerClickCallback);
        featuredOffersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        featuredOffersRecyclerView.setNestedScrollingEnabled(false);
        featuredOffersRecyclerView.setAdapter(featuredOffersAdapter);
    }


    private OnOfferClickCallback offerClickCallback = position -> {
        openOffer(featuredOffers.get(position).getOfferId());
    };

    private void initializeFeaturedCompaniesList() {
        featuredCompaniesAdapter = new FeaturedCompanyListAdapter(getContext(), featuredCompanies, onCompanyClickCallback);
        featuredCompaniesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        featuredCompaniesRecyclerView.setNestedScrollingEnabled(false);
        featuredCompaniesRecyclerView.setAdapter(featuredCompaniesAdapter);
    }

    private OnCompanyClickCallback onCompanyClickCallback = position -> {
        Fragment fragment = CompanyFragment.getInstance(onBackPressedCallback, featuredCompanies.get(position).getCompanyId());
        Util.addFragmentToBackStack(getFragmentManager(), R.id.fragment_container, fragment, "company_fragment");
    };


    private void openOffer(String offerId) {
        Fragment fragment = OfferFragment.getInstance(onBackPressedCallback, offerId);
        Util.addFragmentToBackStack(getFragmentManager(), R.id.fragment_container, fragment, "offer_fragment");
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }

    @Override
    public void addFilter(String countryId, City city) {
        presenter.getFeaturedCompanies();
        presenter.getFeaturedOffers();
    }

    // view method
    @Override
    public void addHomeSliders(List<HomeSlider> sliderList) {
        this.sliderList.clear();
        this.sliderList.addAll(sliderList);
        adsViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void addCategories(List<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFeaturedOffers(List<Offer> offers) {
        featuredOffers.clear();
        featuredOffers.addAll(offers);
        featuredOffersAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFeaturedCompanies(List<Company> companies) {
        featuredCompanies.clear();
        featuredCompanies.addAll(companies);
        featuredCompaniesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setLoaded() {
        super.setLoaded();
        swipeRefreshLayout.setRefreshing(false);
    }
}
