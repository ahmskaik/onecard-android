package com.nawasoft.oneapp.offerfragmnet.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.oneapp.R;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.oneapp.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfferPagerAdapter extends PagerAdapter {

    private Context context;
    private List<String> photos;
    private Offer offer;

    @BindView(R.id.offer_slider_card)
    MaterialCardView offerCard;
    @BindView(R.id.offer_slider_discount)
    TextView discount;
    @BindView(R.id.offer_slider_photo)
    ImageView offerPhoto;
    @BindView(R.id.offer_slider_category_photo)
    ImageView categoryPhoto;
    @BindView(R.id.offer_slider_company_photo)
    ImageView companyPhoto;
    @BindView(R.id.offer_slider_category)
    MaterialCardView offerCategory;
    @BindView(R.id.offer_slider_outer_card)
    MaterialCardView offerOuterCard;


    public OfferPagerAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.offer_slider_item_layout, container, false);
        ButterKnife.bind(this, view);

        Util.loadPictureAndCache(offerPhoto, photos.get(position));
        Util.loadPictureAndCache(categoryPhoto, offer.getCategoryPhotoLink());
        Util.loadPictureAndCache(companyPhoto, offer.getCompanyPhotoLink());

        discount.setText(offer.getDiscount() + "%");

        if (offer.getColor() != null) {
            int color = Color.parseColor(offer.getColor());
            offerCategory.setStrokeColor(color);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                offerCard.setOutlineSpotShadowColor(color);
                offerOuterCard.setOutlineSpotShadowColor(color);
                offerCategory.setOutlineSpotShadowColor(color);
            }
        }

        container.addView(view);
        return view;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public int getCount() {
        return photos == null ? 0 : photos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
