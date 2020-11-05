package com.nawasoft.companyapp.reports.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nawasoft.datalayer.model.CompanyOrder;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyOrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CompanyOrder> companyOrders;

    public CompanyOrderListAdapter(Context context, List<CompanyOrder> companyOrders) {
        this.context = context;
        this.companyOrders = companyOrders;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.company_order_item_layout, parent, false);
        return new CompanyOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CompanyOrderViewHolder) holder).bind(companyOrders.get(position));
    }

    @Override
    public int getItemCount() {
        return companyOrders.size();
    }

    class CompanyOrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.company_photo)
        ImageView companyPhoto;
        @BindView(R.id.order_discount_percentage)
        TextView discountPercentage;
        @BindView(R.id.order_discount_amount)
        TextView tvDiscount;
        @BindView(R.id.order_date)
        TextView date;
        @BindView(R.id.order_member_name)
        TextView memberName;
        @BindView(R.id.order_bill_value)
        TextView billValue;


        public CompanyOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        public void bind(CompanyOrder order) {
            Util.loadPictureAndCache(companyPhoto, order.getCompanyPhotoLink());
            String discount = order.getDiscount();
            if (!discount.contains("%"))
                discount = discount + "%";
            tvDiscount.setText(order.getSaleValue()  + " " + order.getCurrencySymbol());
            discountPercentage.setText(discount);
            date.setText(order.getDate());
            memberName.setText(order.getMemberName());
            billValue.setText(order.getBillValue());
        }
    }
}
