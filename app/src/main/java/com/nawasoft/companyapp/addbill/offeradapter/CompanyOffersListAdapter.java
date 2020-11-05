package com.nawasoft.companyapp.addbill.offeradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nawasoft.datalayer.model.CompanyCard;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyOffersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CompanyCard> companyOffers;
    private SelectItemCallback callback;

    private int selectedItem = -1;

    public CompanyOffersListAdapter(Context context, List<CompanyCard> companyOffers, SelectItemCallback callback) {
        this.context = context;
        this.companyOffers = companyOffers;
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.company_offer_item_layout, parent, false);
        return new CompanyOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CompanyOfferViewHolder) holder).bind(companyOffers.get(position));
    }

    @Override
    public int getItemCount() {
        return companyOffers.size();
    }

    public void clearSelected() {
        if (selectedItem != -1) {
            companyOffers.get(selectedItem).setSelected(false);
            notifyItemChanged(selectedItem);
        }
        selectedItem = -1;
    }

    class CompanyOfferViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.offer_image)
        ImageView imageView;
        @BindView(R.id.offer_background)
        View background;

        public CompanyOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CompanyCard companyCard) {
            Util.loadPictureAndCache(imageView, companyCard.getOfferPhotoLink());
            setBackground(companyCard.isSelected());
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                clearSelected();
                selectedItem = position;
                companyOffers.get(selectedItem).setSelected(true);
                notifyItemChanged(selectedItem);
                callback.onSelected(selectedItem);
            });
        }

        private void setBackground(boolean isSelected) {
            background.setBackground(context.getDrawable(
                    isSelected ? R.drawable.horizontal_gradient : android.R.color.white
            ));
        }
    }
}
