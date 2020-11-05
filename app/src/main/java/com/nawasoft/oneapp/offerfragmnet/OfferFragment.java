package com.nawasoft.oneapp.offerfragmnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;
import com.nawasoft.datalayer.model.Branch;
import com.nawasoft.oneapp.R;
import com.nawasoft.base.BaseFragment;
import com.nawasoft.oneapp.callbacks.OnBackPressedCallback;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.oneapp.common.CompanyBranchesAdapter;
import com.nawasoft.oneapp.common.OnBranchClickImpl;
import com.nawasoft.oneapp.offerfragmnet.adapter.OfferPagerAdapter;
import com.nawasoft.oneapp.offerfragmnet.mvp.OfferMVP;
import com.nawasoft.oneapp.util.URL;
import com.nawasoft.oneapp.util.Util;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfferFragment extends BaseFragment implements OfferMVP.View {

    @Inject
    OfferMVP.Presenter presenter;

    @BindView(R.id.offer_back_button)
    MaterialCardView backButton;
    @BindView(R.id.offer_company_name)
    TextView companyName;
    @BindView(R.id.offer_company_photo)
    ImageView companyPhoto;
    @BindView(R.id.offer_views)
    TextView views;
    @BindView(R.id.offer_favourite_button)
    ImageView favouriteButton;
    @BindView(R.id.offer_description)
    TextView offerDescription;
    @BindView(R.id.offer_slider)
    ViewPager offerSlider;
    @BindView(R.id.offer_phone_button)
    ImageView phoneButton;
    @BindView(R.id.offer_email_button)
    ImageView emailButton;
    @BindView(R.id.offer_website_button)
    ImageView websiteButton;
    @BindView(R.id.offer_mobile_button)
    ImageView mobileButton;
    @BindView(R.id.offer_location_button)
    ImageView locationButton;
    @BindView(R.id.offer_tab_layout)
    DotsIndicator tabLayout;
    @BindViews({R.id.offer_start1,
            R.id.offer_start2,
            R.id.offer_start3,
            R.id.offer_start4,
            R.id.offer_start5})
    List<ImageView> rateStars;
    @BindView(R.id.branches_textView)
    TextView tvBranches;
    @BindView(R.id.branches_list)
    RecyclerView branchesList;

    private String offerId;
    private Offer currentOffer;
    private OfferPagerAdapter offerPagerAdapter;
    private CompanyBranchesAdapter companyBranchesAdapter;
    private OnBackPressedCallback onBackPressedCallback;


    public static String OFFER_ID = "offer_id";

    public static OfferFragment getInstance(OnBackPressedCallback callback, String offerId) {
        Bundle bundle = new Bundle();
        bundle.putString(OfferFragment.OFFER_ID, offerId);
        OfferFragment fragment = new OfferFragment(callback);
        fragment.setArguments(bundle);
        return fragment;
    }

    private OfferFragment(OnBackPressedCallback onBackPressedCallback) {
        this.onBackPressedCallback = onBackPressedCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offer_layout, container, false);
        ButterKnife.bind(this, view);
        setProgressBar(view.findViewById(R.id.offer_progressBar));


        offerId = getArguments().getString(OFFER_ID, "");

        initializeSlider();

        presenter.setView(this);
        presenter.getOfferDetails();


        backButton.setOnClickListener(view1 -> onBackPressedCallback.onBack());
        favouriteButton.setOnClickListener(view1 -> presenter.switchOfferFavourite());

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }


    private void initializeSlider() {
        offerPagerAdapter = new OfferPagerAdapter(getContext());
        offerSlider.setClipToPadding(true);
        offerSlider.setAdapter(offerPagerAdapter);
        tabLayout.setViewPager(offerSlider);
    }


    private void initializeViews() {
        Util.loadPictureAndCache(companyPhoto, currentOffer.getCompanyPhotoLink());
        companyName.setText(currentOffer.getCompanyName());
        offerDescription.setText(currentOffer.getDetails());
        views.setText(currentOffer.getViews());
        offerPagerAdapter.setOffer(currentOffer);

        if (getActivity() == null)
            return;

        phoneButton.setOnClickListener(view -> Util.callNumber(getActivity(), currentOffer.getPhoneNumber()));
        emailButton.setOnClickListener(view -> Util.sendEmail(getActivity(), currentOffer.getEmailAddress()));
        mobileButton.setOnClickListener(view -> Util.callNumber(getActivity(), currentOffer.getMobileNumber()));
        websiteButton.setOnClickListener(view -> Util.openBrowser(getActivity(), currentOffer.getWebsite()));
        locationButton.setOnClickListener(view -> Util.openMap(
                getActivity(),
                Double.valueOf(currentOffer.getLatitude()),
                Double.valueOf(currentOffer.getLongitude()),
                currentOffer.getCompanyName()
        ));
    }

    private void initBranches(List<Branch> branches) {
        if (branches == null)
            return;
        tvBranches.setVisibility(branches.isEmpty() ? View.INVISIBLE : View.VISIBLE);
        companyBranchesAdapter = new CompanyBranchesAdapter(getContext(), branches, new OnBranchClickImpl(getActivity()));
        branchesList.setAdapter(companyBranchesAdapter);
    }


    @OnClick(R.id.share_button)
    void shareOffer() {
        String url = URL.BASE_OFFER.toString() + offerId;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(intent, getString(R.string.share_offer)));
    }

    // view methods

    @Override
    public String getOfferId() {
        return offerId;
    }

    @Override
    public void setOffer(Offer offer) {
        currentOffer = offer;
        initializeViews();
    }

    @Override
    public void setRateAverage(String rateAverage) {
        if (rateAverage == null)
            return;

        for (int i = 0; i < Integer.valueOf(rateAverage); i++) {
            rateStars.get(i).setImageResource(R.drawable.ic_star);
        }
    }

    @Override
    public void setSliderPhotos(List<String> photos) {
        if (photos.size() <= 1) {
            tabLayout.setVisibility(View.GONE);
        }
        offerPagerAdapter.setPhotos(photos);
        offerPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setIsOfferFavourite(boolean isOfferFavourite) {
        favouriteButton.setEnabled(true);
        favouriteButton.setImageResource(isOfferFavourite ? R.drawable.ic_favourite_enable : R.drawable.ic_favourite_disable);
    }

    @Override
    public void setBranches(List<Branch> branches) {
        initBranches(branches);
    }
}
