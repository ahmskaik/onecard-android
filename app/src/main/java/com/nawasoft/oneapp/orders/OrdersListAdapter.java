package com.nawasoft.oneapp.orders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nawasoft.oneapp.R;
import com.nawasoft.datalayer.model.Order;
import com.nawasoft.oneapp.callbacks.OnClickCallback;
import com.nawasoft.oneapp.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Order> orders;

    private OnClickCallback clickCallback;
    public OrdersListAdapter(Context context, List<Order> orders, OnClickCallback clickCallback) {
        this.context = context;
        this.orders = orders;
        this.clickCallback = clickCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item_layout, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((OrderViewHolder) holder).bind(orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.company_photo)
        ImageView companyPhoto;
        @BindView(R.id.order_discount_percentage)
        TextView discountPercentage;
        @BindView(R.id.order_discount_amount)
        TextView tvDiscount;
        @BindView(R.id.order_date)
        TextView date;
        @BindView(R.id.rate_button)
        ImageView rateButton;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Order order) {
            Util.loadPictureAndCache(companyPhoto, order.getCompanyPhotoLink());
            String discount = order.getDiscount();
            if (!discount.contains("%"))
                discount = discount + "%";
            tvDiscount.setText(order.getSaleValue() + " " + order.getCurrencySymbol());
            discountPercentage.setText(discount);
            date.setText(order.getDate());

            rateButton.setOnClickListener(view -> clickCallback.onClick(getAdapterPosition()));
        }
    }
}
