package com.nawasoft.oneapp.offers.adapter;

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
import com.nawasoft.oneapp.callbacks.OnCategoryClickCallback;
import com.nawasoft.datalayer.model.Category;
import com.nawasoft.oneapp.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Category> categories;
    private OnCategoryClickCallback callback;

    private int LARGE_WIDTH, SMALL_WIDTH;
    private int lastSelected = -1;

    public CategoriesAdapter(Context context, List<Category> categories, OnCategoryClickCallback callback) {
        this.context = context;
        this.categories = categories;
        this.callback = callback;
        LARGE_WIDTH = (int) context.getResources().getDimension(R.dimen._3sdp);
        SMALL_WIDTH = (int) context.getResources().getDimension(R.dimen._1sdp);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_list_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CategoryViewHolder) holder).bind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setLastSelected(int lastSelected) {
        this.lastSelected = lastSelected;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_card)
        MaterialCardView categoryCard;
        @BindView(R.id.category_photo)
        ImageView categoryPhoto;
        @BindView(R.id.category_title)
        TextView title;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void bind(Category category) {
            title.setText(category.getTitle());
            Util.loadPictureAndCache(categoryPhoto, category.getPhotoLink());

            categoryCard.setStrokeWidth(category.isSelected() ? LARGE_WIDTH : SMALL_WIDTH);
            categoryCard.setContentPadding(0, 0, 0, 0);
            if (category.getColor() != null) {
                categoryCard.setStrokeColor(Color.parseColor(category.getColor()));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    categoryCard.setOutlineSpotShadowColor(Color.parseColor(category.getColor()));
                }
            }

            itemView.setOnClickListener(view -> {
                clearSelected();
                if (getAdapterPosition() != lastSelected) {
                    lastSelected = getAdapterPosition();
                    categories.get(lastSelected).setSelected(true);
                    notifyItemChanged(lastSelected);
                } else
                    lastSelected = -1;
                callback.onCategoryClick(getAdapterPosition());
            });
        }

        private void clearSelected() {
            if (lastSelected != -1) {
                categories.get(lastSelected).setSelected(false);
                notifyItemChanged(lastSelected);
            }
        }


    }

}
