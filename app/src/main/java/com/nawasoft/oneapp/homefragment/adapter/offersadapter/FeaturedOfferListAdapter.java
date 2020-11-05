package com.nawasoft.oneapp.homefragment.adapter.offersadapter;

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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnOfferClickCallback;
import com.nawasoft.datalayer.model.Offer;
import com.nawasoft.oneapp.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeaturedOfferListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Offer> offers;
    private OnOfferClickCallback callback;

    public FeaturedOfferListAdapter(Context context, List<Offer> offers, OnOfferClickCallback callback) {
        this.context = context;
        this.offers = offers;
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.featured_offer_item_layout, parent, false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((OfferViewHolder) holder).bind(offers.get(position));
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    class OfferViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.offer_discount)
        TextView discount;
        @BindView(R.id.category_photo)
        ImageView categoryPhoto;
        @BindView(R.id.company_photo)
        ImageView companyPhoto;
        @BindView(R.id.offer_photo)
        ImageView offerPhoto;
        @BindView(R.id.offer_card)
        MaterialCardView offerCard;
        @BindView(R.id.offer_outer_card)
        MaterialCardView offerOuterCard;
        @BindView(R.id.featured_offers_category)
        MaterialCardView category;


        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Offer offer) {
            discount.setText(offer.getDiscount() + "%");
            Util.loadPictureAndCache(categoryPhoto, offer.getCategoryPhotoLink());
            Util.loadPictureAndCache(companyPhoto, offer.getCompanyPhotoLink());
            Util.loadPictureAndCache(offerPhoto, offer.getOfferPhotoLink());
            if (offer.getColor() != null) {
                int color = Color.parseColor(offer.getColor());
                category.setStrokeColor(color);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    offerCard.setOutlineSpotShadowColor(color);
                    offerOuterCard.setOutlineSpotShadowColor(color);
                    category.setOutlineSpotShadowColor(color);
                }
            }

            itemView.setOnClickListener(view -> callback.onOfferClick(getAdapterPosition()));
        }
    }

}
