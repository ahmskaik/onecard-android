package com.nawasoft.oneapp.optionsfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nawasoft.oneapp.R;

public class WebViewFragment extends Fragment {

    private WebView webView;
    private String url;
    public static String WEB_URL = "web_url";
    public static final String WEB_TITLE = "title";

    private TextView title;

    public static WebViewFragment getInstance(String url, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(WebViewFragment.WEB_URL, url);
        bundle.putString(WebViewFragment.WEB_TITLE, title);
        WebViewFragment fragment = new WebViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_fragment_layout, container, false);
        title = view.findViewById(R.id.web_title);
        webView = view.findViewById(R.id.webview);
        url = getArguments().getString(WEB_URL);
        title.setText(getArguments().getString(WEB_TITLE, ""));
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);

        view.findViewById(R.id.back_button).setOnClickListener(view1 -> getFragmentManager().popBackStack());

        return view;
    }
}
