package com.nawasoft.oneapp.editprofile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.card.MaterialCardView;
import com.nawasoft.base.BaseFragment;
import com.nawasoft.datalayer.model.Country;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnUserProfileUpdated;
import com.nawasoft.oneapp.editprofile.mvp.EditProfileMVP;
import com.nawasoft.oneapp.util.CalendarUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileFragment extends BaseFragment implements CalendarUtil.OnDateSelected, EditProfileMVP.View {

    @Inject
    EditProfileMVP.Presenter presenter;

    @BindView(R.id.edit_name_input)
    EditText firstName;
    @BindView(R.id.edit_last_name_input)
    EditText lastName;
    @BindView(R.id.edit_email_input)
    EditText emailAddress;
    @BindView(R.id.edit_phone_input)
    EditText mobileNumber;
    @BindView(R.id.edit_birthday_input)
    TextView birthday;
    @BindView(R.id.edit_gender_input)
    TextView gender;
    @BindView(R.id.edit_address_input)
    EditText address;
    //    @BindView(R.id.edit_country_input)
//    EditText country;
    @BindView(R.id.countries_spinner)
    Spinner spinner;
    @BindView(R.id.back_button)
    MaterialCardView backButton;
    @BindView(R.id.edit_button)
    MaterialCardView editButton;

    private CalendarUtil calendarUtil;

    private Integer genderStatus; // 1 male, 2 female
    private Integer countryId;

    private List<Country> countries = new ArrayList<>();
    private ArrayAdapter<Country> countriesAdapter;


    private OnUserProfileUpdated onUserProfileUpdated;

    public EditProfileFragment(OnUserProfileUpdated onUserProfileUpdated) {
        this.onUserProfileUpdated = onUserProfileUpdated;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_profile_fragment, container, false);
        ButterKnife.bind(this, view);
        setProgressBar(view.findViewById(R.id.edit_progressBar));
        calendarUtil = new CalendarUtil(getContext(), this);

        initializeCountriesAdapter();

        presenter.setView(this);
        presenter.getSavedUser();
        presenter.getCountries();
        return view;
    }

    private void initializeCountriesAdapter() {
        countriesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, countries);
        spinner.setAdapter(countriesAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                countryId = Integer.valueOf(countries.get(position).getCountryId());
                System.out.println("selected = " + countryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    @OnClick({
            R.id.edit_birthday_input,
            R.id.edit_gender_input,
            R.id.edit_button,
            R.id.back_button
    })
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_birthday_input:
                calendarUtil.showPicker();
                break;
            case R.id.edit_gender_input:
                genderStatus = Math.abs(3 - genderStatus);
                updateGender();
                break;
            case R.id.edit_button:
                presenter.editProfile();
                break;
            case R.id.back_button:
                getFragmentManager().popBackStack();
                break;
        }
    }


    private void updateGender() {
        gender.setText(genderStatus == 1 ? R.string.male : R.string.female);
    }


    @Override
    public void onDateSelected(String date) {
        birthday.setText(date);
    }

    // view methods

    @Override
    public String getFirstName() {
        return firstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastName.getText().toString();
    }

    @Override
    public String getEmailAddress() {
        return emailAddress.getText().toString();
    }

    @Override
    public String getMobileNumber() {
        return mobileNumber.getText().toString();
    }

    @Override
    public String getBirthday() {
        return birthday.getText().toString();
    }

    @Override
    public String getAddress() {
        return address.getText().toString();
    }

    @Override
    public int getGender() {
        return genderStatus;
    }

    @Override
    public int getCountryId() {
        return countryId;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }

    @Override
    public void setEmailAddress(String emailAddress) {
        this.emailAddress.setText(emailAddress);
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber.setText(mobileNumber);
    }

    @Override
    public void setGender(String gender) {
        genderStatus = gender.equalsIgnoreCase("2") ? 2 : 1;
        updateGender();
    }

    @Override
    public void setAddress(String address) {
        this.address.setText(address);
    }

    @Override
    public void setBirthday(String birthday) {
        this.birthday.setText(birthday);
    }

    @Override
    public void setCountryId(String countryId) {
        this.countryId = !countryId.isEmpty() ? Integer.valueOf(countryId) : -1;
    }

    @Override
    public void setCountries(List<Country> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        countriesAdapter.notifyDataSetChanged();
        if (countryId != -1)
            for (int i = 0; i < this.countries.size(); i++) {
                if (countryId.equals(Integer.valueOf(countries.get(i).getCountryId()))) {
                    spinner.setSelection(i);
                    return;
                }
            }
    }

    @Override
    public void profileEdited() {
        onUserProfileUpdated.userProfileUpdated();
        showAlert(getString(R.string.profile_updated));
    }
}
