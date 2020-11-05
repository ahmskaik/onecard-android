package com.nawasoft.base;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment implements IBaseView, TextWatcher {

    private ProgressBar progressBar;

    private AlertDialog alertDialog;

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    protected void doSearchQuery() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void setLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLoaded() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean isLoading() {
        return progressBar.getVisibility() == View.VISIBLE;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert(String message) {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
        alertDialog =  new AlertDialog.Builder(getContext())
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (d, i) -> {
                    d.dismiss();
                }).create();
        alertDialog.show();
    }

    // text watcher

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        doSearchQuery();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
