package com.nawasoft.oneapp.rate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nawasoft.base.BaseFragmentDialog;
import com.nawasoft.datalayer.model.Order;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.rate.mvp.RateMVP;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class RateDialog extends BaseFragmentDialog implements RateMVP.View {

    @Inject
    RateMVP.Presenter presenter;

    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.submit_button)
    View submitButton;
    @BindView(R.id.comment_input)
    EditText commentInput;


    public static final String ORDER = "order";

    private Order order;

    private final float MINIMUM_RATING = 1.0f;


    public static RateDialog getInstance(Order order) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(RateDialog.ORDER, order);
        RateDialog rateDialog = new RateDialog();
        rateDialog.setArguments(bundle);
        return rateDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.rate_dialog_layout, container, false);
        setProgressBar(view.findViewById(R.id.rate_progressBar));
        ButterKnife.bind(this, view);

        order = getArguments() != null ? (Order) getArguments().getSerializable(ORDER) : null;

        submitButton.setOnClickListener(view1 -> {
            if (order.getRate() == null)
                presenter.makeRate();
            else
                showMessage(getString(R.string.already_rated));
        });

        ratingBar.setRating(MINIMUM_RATING);
        ratingBar.setOnRatingBarChangeListener((rating, value, b) -> {
            if (value < MINIMUM_RATING)
                rating.setRating(MINIMUM_RATING);
        });

        presenter.setView(this);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    // view methods

    @Override
    public String getOrderId() {
        return order.getOrderId();
    }

    @Override
    public String getComment() {
        return commentInput.getText().toString();
    }

    @Override
    public int getRate() {
        return Math.round(ratingBar.getRating());
    }

    @Override
    public void setRated() {
        ratingBar.setRating(1.0f);
        commentInput.setText("");
        order.setRate(String.valueOf(getRate()));
    }
}
