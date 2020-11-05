package com.nawasoft.oneapp.language;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nawasoft.base.BaseFragmentDialog;
import com.nawasoft.oneapp.main.MainActivity;
import com.nawasoft.oneapp.R;
import com.nawasoft.datalayer.cache.ICacheRepository;
import com.nawasoft.datalayer.sharedpref.ISharedPref;
import com.nawasoft.oneapp.util.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class LanguageDialog extends BaseFragmentDialog {

    @Inject
    ISharedPref sharedPref;
    @Inject
    ICacheRepository cacheRepository;

    @BindView(R.id.languages_group)
    RadioGroup languagesGroup;
    @BindView(R.id.done_button)
    View doneButton;

    private String selectedLanguage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.language_dialog_layout, container, false);
        ButterKnife.bind(this, view);
        setSelectedLanguage();

        languagesGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
            selectedLanguage = (String) radioButton.getTag();
        });

        doneButton.setOnClickListener(view1 -> {
            setNewLanguage(selectedLanguage);
        });

        return view;
    }

    private void setSelectedLanguage() {
        selectedLanguage = sharedPref.getLanguage();
        RadioButton radioButton = languagesGroup.findViewWithTag(selectedLanguage);
        radioButton.toggle();
    }

    private void setNewLanguage(String language) {
        sharedPref.setLanguage(language);
        cacheRepository.clearAllData();
        getActivity().finish();
        Util.goToActivity(getContext(), MainActivity.class, null);
    }
}
