package com.nawasoft.oneapp.companiesfragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnCompanyClickCallback;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.oneapp.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompaniesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Company> companies;
    private OnCompanyClickCallback callback;
    public CompaniesListAdapter(Context context, OnCompanyClickCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.featured_company_item_layout, parent, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CompanyViewHolder) holder).bind(companies.get(position));
    }

    @Override
    public int getItemCount() {
        return companies != null ? companies.size() : 0;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    class CompanyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.company_photo)
        ImageView companyPhoto;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Company company) {
            Util.loadPictureAndCache(companyPhoto, company.getCompanyPhotoLink());
            itemView.setOnClickListener(view -> callback.onCompanyClick(getAdapterPosition()));
        }
    }

}
