package com.nawasoft.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public abstract class BaseFragmentDialog extends DialogFragment implements IBaseView {

    private ProgressBar progressBar;

    private AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
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
}
