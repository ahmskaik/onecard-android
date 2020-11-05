package com.nawasoft.oneapp.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nawasoft.datalayer.model.Branch;
import com.nawasoft.datalayer.model.Company;
import com.nawasoft.datalayer.model.LatLong;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnBranchClickCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyBranchesAdapter extends RecyclerView.Adapter<CompanyBranchesAdapter.BranchViewHolder> {

    private Context context;
    private List<Branch> branches;
    private OnBranchClickCallback branchClickCallback;

    public CompanyBranchesAdapter(Context context, List<Branch> branches, OnBranchClickCallback branchClickCallback) {
        this.context = context;
        this.branches = branches;
        this.branchClickCallback = branchClickCallback;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.branch_item_layout, parent, false);
        return new BranchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder holder, int position) {
        holder.bind(branches.get(position));
    }

    @Override
    public int getItemCount() {
        return branches == null ? 0 : branches.size();
    }

    class BranchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.branch_title)
        TextView title;
        @BindView(R.id.branch_address)
        TextView address;
        @BindView(R.id.branch_location_button)
        ImageView locationButton;
        @BindView(R.id.branch_mobile_button)
        ImageView mobileButton;
        @BindView(R.id.branch_phone_button)
        ImageView phoneButton;
        @BindView(R.id.branch_message_button)
        ImageView messageButton;

        public BranchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Company branch) {
            title.setText(branch.getTitle());
            address.setText(branch.getCityName());
            locationButton.setOnClickListener(view -> branchClickCallback.onLocationClick(
                    new LatLong(branch.getLatitude(), branch.getLongitude()), branch.getTitle()
            ));
            mobileButton.setOnClickListener(view -> branchClickCallback.onMobileClick(branch.getMobileNumber()));
            phoneButton.setOnClickListener(view -> branchClickCallback.onMobileClick(branch.getPhoneNumber()));
            messageButton.setOnClickListener(view -> branchClickCallback.onMobileClick(branch.getEmailAddress()));
        }
    }
}
