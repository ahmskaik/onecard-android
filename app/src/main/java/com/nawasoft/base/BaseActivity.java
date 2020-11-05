package com.nawasoft.base;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nawasoft.oneapp.util.LocaleHelper;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private ProgressBar progressBar;

    private AlertDialog alertDialog;

    protected void setProgressBar(@IdRes int id) {
        progressBar = findViewById(id);
        progressBar.setVisibility(View.GONE);
    }
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(LocaleHelper.onAttach(newBase));
//    }

    @Override
    public void setLoading() {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void setLoaded() {
        progressBar.setVisibility(View.INVISIBLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public boolean isLoading() {
        return progressBar.getVisibility() == View.VISIBLE;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert(String message) {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
        alertDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (d, i) -> {
                    d.dismiss();
                }).create();
        alertDialog.show();

    }
}
