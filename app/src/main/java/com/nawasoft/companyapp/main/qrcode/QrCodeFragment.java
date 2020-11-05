package com.nawasoft.companyapp.main.qrcode;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.Result;
import com.nawasoft.oneapp.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrCodeFragment extends Fragment implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;
    private LinearLayout qrCodeLayout;

    private QrCodeCallBack callBack;
    private View cancel;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callBack = (QrCodeCallBack) activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qrcode_fragment_layout, container, false);
        qrCodeLayout = view.findViewById(R.id.qr_code_container);
        cancel = view.findViewById(R.id.cancel_qr_code_button);
        cancel.setOnClickListener(v -> {
                    scannerView.stopCamera();
                    scannerView.stopCameraPreview();
                    callBack.cancelQr();
                }
        );

        scannerView = new ZXingScannerView(getContext());
        scannerView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        qrCodeLayout.addView(scannerView);
        return view;

    }

    @Override
    public void onPause() {
        scannerView.stopCamera();
        super.onPause();
    }


    @Override
    public void handleResult(Result result) {
        scannerView.stopCamera();
        callBack.onResult(result.getText());
        callBack.cancelQr();
    }
}
