package com.nawasoft.oneapp.companyfragment;

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

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.datalayer.model.Branch;
import com.nawasoft.oneapp.R;
import com.nawasoft.base.BaseFragment;
import com.nawasoft.oneapp.callbacks.OnBackPressedCallback;
import com.nawasoft.oneapp.common.CompanyBranchesAdapter;
import com.nawasoft.oneapp.common.OnBranchClickImpl;
import com.nawasoft.oneapp.companyfragment.mvp.CompanyMVP;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.oneapp.util.Util;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyFragment extends BaseFragment implements CompanyMVP.View {

    @Inject
    CompanyMVP.Presenter presenter;

    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.company_address)
    TextView companyAddress;
    @BindView(R.id.company_photo)
    ImageView companyPhoto;
    @BindView(R.id.company_back_button)
    MaterialCardView backButton;
    @BindView(R.id.company_phone_button)
    ImageView phoneButton;
    @BindView(R.id.company_email_button)
    ImageView emailButton;
    @BindView(R.id.company_website_button)
    ImageView websiteButton;
    @BindView(R.id.company_mobile_button)
    ImageView mobileButton;
    @BindView(R.id.company_location_button)
    ImageView locationButton;
    @BindView(R.id.branches_textView)
    TextView tvBranches;
    @BindView(R.id.branches_list)
    RecyclerView branchesList;

    // intent key
    public static String COMPANY_ID = "company_id";

    private String companyId;
    private Company currentCompany;
    private OnBackPressedCallback onBackPressedCallback;

    private CompanyBranchesAdapter companyBranchesAdapter;

    public static CompanyFragment getInstance(OnBackPressedCallback callback, String companyId) {
        Bundle bundle = new Bundle();
        bundle.putString(CompanyFragment.COMPANY_ID, companyId);
        CompanyFragment fragment = new CompanyFragment(callback);
        fragment.setArguments(bundle);
        return fragment;
    }

    public CompanyFragment(OnBackPressedCallback onBackPressedCallback) {
        this.onBackPressedCallback = onBackPressedCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.company_layout, container, false);
        ButterKnife.bind(this, view);
        setProgressBar(view.findViewById(R.id.company_progressBar));
        companyId = getArguments().getString(COMPANY_ID);

        presenter.setView(this);
        presenter.getCompanyDetails();

        backButton.setOnClickListener(view1 -> onBackPressedCallback.onBack());

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    private void initializeViews() {
        Util.loadPictureAndCache(companyPhoto, currentCompany.getCompanyPhotoLink());
        companyName.setText(currentCompany.getTitle());
        companyAddress.setText(currentCompany.getCityName());
        if (getActivity() == null)
            return;
        phoneButton.setOnClickListener(view -> Util.callNumber(getActivity(), currentCompany.getPhoneNumber()));
        emailButton.setOnClickListener(view -> Util.sendEmail(getActivity(), currentCompany.getEmailAddress()));
        mobileButton.setOnClickListener(view -> Util.callNumber(getActivity(), currentCompany.getMobileNumber()));
        websiteButton.setOnClickListener(view -> Util.openBrowser(getActivity(), currentCompany.getWebsite()));
        locationButton.setOnClickListener(view -> Util.openMap(
                getActivity(),
                Double.valueOf(currentCompany.getLatitude()),
                Double.valueOf(currentCompany.getLongitude()),
                currentCompany.getTitle()
        ));
    }

    private void initBranches(List<Branch> branches) {
        if (branches == null)
            return;
        tvBranches.setVisibility(branches.isEmpty() ? View.GONE : View.VISIBLE);
        companyBranchesAdapter = new CompanyBranchesAdapter(getContext(), branches, new OnBranchClickImpl(getActivity()));
        branchesList.setAdapter(companyBranchesAdapter);
    }


    // view methods

    @Override
    public void setCompany(Company company) {
        this.currentCompany = company;
        initializeViews();
    }

    @Override
    public void setCompanyBranches(List<Branch> branches) {
        initBranches(branches);
    }

    @Override
    public String getCompanyId() {
        return companyId;
    }
}
