package com.nawasoft.oneapp.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.payment.activecard.ActiveCardDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OptionsMenuFragment extends BottomSheetDialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_options_sheet_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({
            R.id.active_card_button,
            R.id.buy_card_button,
            R.id.renew_card_button
    })
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.active_card_button:
                new ActiveCardDialog().show(getFragmentManager(), "active_card_dialog");
                break;
            case R.id.buy_card_button:
            case R.id.renew_card_button:
                Toast.makeText(getContext(), "Soon...", Toast.LENGTH_SHORT).show();
                break;
        }
        dismiss();
    }

}
