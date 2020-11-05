package com.nawasoft.oneapp.tickets.mytickets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nawasoft.base.BaseFragmentDialog;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.util.ImagesUtil;
import com.nawasoft.oneapp.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QrCodeDialog extends BaseFragmentDialog {

    @BindView(R.id.qr_code_photo)
    ImageView qrCodePhoto;

    public static final String QR_PHOTO_LINK = "photo_link";

    private String url = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.qr_code_dialog_layout, container, false);
        ButterKnife.bind(this, view);
        url = getArguments().getString(QR_PHOTO_LINK, "");
        ImagesUtil.loadPictureAndStore(getContext(), qrCodePhoto, url);
        return view;
    }
}
