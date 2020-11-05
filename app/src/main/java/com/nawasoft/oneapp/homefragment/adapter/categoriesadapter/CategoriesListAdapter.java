package com.nawasoft.oneapp.homefragment.adapter.categoriesadapter;

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

public class CategoriesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Category> categories;
    private OnCategoryClickCallback callback;

    public CategoriesListAdapter(Context context, List<Category> categories, OnCategoryClickCallback callback) {
        this.context = context;
        this.categories = categories;
        this.callback = callback;
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


    class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_photo)
        ImageView imageView;
        @BindView(R.id.category_title)
        TextView title;
        @BindView(R.id.category_card)
        MaterialCardView categoryCard;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Category category) {
            title.setText(category.getTitle());
            Util.loadPictureAndCache(imageView, category.getPhotoLink());
            if (category.getColor() != null) {
                categoryCard.setStrokeColor(Color.parseColor(category.getColor()));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    categoryCard.setOutlineSpotShadowColor(Color.parseColor(category.getColor()));
                }
            }

            itemView.setOnClickListener(view -> callback.onCategoryClick(getAdapterPosition()));
        }

    }
}
