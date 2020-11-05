package com.nawasoft.oneapp.offers.mapfragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.base.BaseFragmentDialog;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnMarkerClickCallback;
import com.nawasoft.datalayer.model.OfferMarker;
import com.nawasoft.oneapp.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfferDialog extends BaseFragmentDialog {

    @BindView(R.id.offer_map_company_photo)
    ImageView companyPhoto;
    @BindView(R.id.company_phone_number)
    TextView companyNumber;
    @BindView(R.id.offer_map_discount)
    TextView discount;
    @BindView(R.id.offer_photo)
    ImageView offerPhoto;
    @BindView(R.id.offer_map_layout)
    MaterialCardView layout;

    public static String OFFER = "offer";

    private OfferMarker offer;

    private OnMarkerClickCallback callback;

    public OfferDialog(OnMarkerClickCallback callback) {
        this.callback = callback;
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.offer_map_layout, container, false);
        ButterKnife.bind(this, view);
        offer = (OfferMarker) getArguments().getSerializable(OFFER);


        companyNumber.setText(offer.getMobileNumber());
        discount.setText(offer.getDiscount() + "%");
        Util.loadPictureAndCache(companyPhoto, offer.getCompanyPhotoLink());
        Util.loadPictureAndCache(offerPhoto, offer.getOfferPhotoLink());

        layout.setOnClickListener(view1 -> {
            callback.onMarkerClick(offer.getOfferId());
            getDialog().dismiss();
        });

        return view;
    }
}
