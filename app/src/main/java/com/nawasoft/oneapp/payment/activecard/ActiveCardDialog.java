package com.nawasoft.oneapp.payment.activecard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nawasoft.base.BaseFragmentDialog;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.payment.activecard.mvp.ActiveCardMVP;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class ActiveCardDialog extends BaseFragmentDialog implements ActiveCardMVP.View {

    @Inject
    ActiveCardMVP.Presenter presenter;

    @BindView(R.id.active_button)
    View activeButton;
    @BindView(R.id.serial_number_input)
    EditText serialNumberInput;
    @BindView(R.id.password_input)
    EditText passwordInput;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.active_card_dialog_layout, container, false);
        ButterKnife.bind(this, view);
        setProgressBar(view.findViewById(R.id.active_card_progressBar));

        presenter.setView(this);

        activeButton.setOnClickListener(view1 -> presenter.activeCard());
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }


    // view methods

    @Override
    public void setCardActivated() {
        showAlert(getString(R.string.card_activated));
        serialNumberInput.setText("");
        passwordInput.setText("");
    }

    @Override
    public String getSerialNumber() {
        return serialNumberInput.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordInput.getText().toString();
    }
}
