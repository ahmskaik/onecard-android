package com.nawasoft.oneapp.optionsfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nawasoft.oneapp.R;
import com.nawasoft.base.BaseFragment;
import com.nawasoft.oneapp.callbacks.OnBackPressedCallback;
import com.nawasoft.oneapp.callbacks.OnUserProfileUpdated;
import com.nawasoft.oneapp.editprofile.EditProfileFragment;
import com.nawasoft.oneapp.language.LanguageDialog;
import com.nawasoft.oneapp.login.LoginActivity;
import com.nawasoft.oneapp.optionsfragment.mvp.IOptionsPresenter;
import com.nawasoft.oneapp.optionsfragment.resetpassword.ResetPasswordActivity;
import com.nawasoft.oneapp.orders.OrdersFragment;
import com.nawasoft.oneapp.tickets.TicketsFragment;
import com.nawasoft.oneapp.tickets.mytickets.MyTicketsFragment;
import com.nawasoft.oneapp.util.URL;
import com.nawasoft.oneapp.util.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OptionsFragment extends BaseFragment {

    @Inject
    IOptionsPresenter presenter;


    @BindView(R.id.logout_button)
    View logoutButton;
    @BindView(R.id.signIn_register_button)
    View signInButton;
    @BindView(R.id.edit_profile_button)
    View editProfile;
    @BindView(R.id.reset_password_button)
    View resetPassword;
    @BindView(R.id.orders_button)
    View ordersButton;
    @BindView(R.id.dashBord)
    View dashBord;
    @BindView(R.id.my_cards_button)
    View myCards;
    @BindView(R.id.cards_button)
    View cardsButton;
    @BindView(R.id.cards)
    View cards;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.online_mark)
    View onlineMark;

    private OnBackPressedCallback onBackCallback;

    public OptionsFragment() {
    }

    public OptionsFragment(OnBackPressedCallback onBackCallback) {
        this.onBackCallback = onBackCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.options_fragment_layout, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        checkButtons();
    }

    private void checkButtons() {
        if (presenter.isLoggedIn()) {
            signInButton.setVisibility(View.GONE);
            logoutButton.setVisibility(View.VISIBLE);
            dashBord.setVisibility(View.VISIBLE);
            editProfile.setVisibility(View.VISIBLE);
            resetPassword.setVisibility(View.VISIBLE);
            ordersButton.setVisibility(View.VISIBLE);
            myCards.setVisibility(View.VISIBLE);
            cardsButton.setVisibility(View.VISIBLE);
            cards.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);
            onlineMark.setVisibility(View.VISIBLE);
            setUsername();
        } else {
            signInButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.GONE);
            dashBord.setVisibility(View.GONE);
            editProfile.setVisibility(View.GONE);
            resetPassword.setVisibility(View.GONE);
            ordersButton.setVisibility(View.GONE);
            myCards.setVisibility(View.GONE);
            cardsButton.setVisibility(View.GONE);
            cards.setVisibility(View.GONE);
            username.setVisibility(View.GONE);
            onlineMark.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.signIn_register_button,
            R.id.logout_button,
            R.id.contactUs_button,
            R.id.about_button,
            R.id.reset_password_button,
            R.id.orders_button,
            R.id.edit_profile_button,
            R.id.distributors_button,
            R.id.cards_button,
            R.id.my_cards_button,
            R.id.change_language_button
    })
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.signIn_register_button:
                startActivityForResult(new Intent(getContext(), LoginActivity.class), 22);
                break;
            case R.id.logout_button:
                logout();
                break;
            case R.id.contactUs_button:
                openWebView(URL.CONTACT_US.toString(), getString(R.string.contactUs));
                break;
            case R.id.about_button:
                openWebView(URL.ABOUT_US.toString(), getString(R.string.about));
                break;
            case R.id.distributors_button :
                openWebView(URL.DISTRIBUTORS.toString(), getString(R.string.distributors));
                break;
            case R.id.reset_password_button:
                Util.goToActivity(getContext(), ResetPasswordActivity.class, null);
                break;
            case R.id.orders_button :
                showOrders();
                break;
            case R.id.edit_profile_button:
                showEditProfile();
                break;
            case R.id.cards_button :
                showCards();
                break;
            case R.id.my_cards_button :
                showMyCards();
                break;
            case R.id.change_language_button :
                new LanguageDialog().show(getFragmentManager(), "");
                break;
        }
    }

    private void setUsername() {
        username.setText(presenter.getUserName());
    }

    private void showMyCards() {
        Fragment fragment = new MyTicketsFragment();
        showFragment(fragment);
    }

    private void showCards() {
        Fragment fragment = new TicketsFragment();
        showFragment(fragment);
    }

    private void showOrders() {
        Fragment fragment = new OrdersFragment();
        showFragment(fragment);
    }

    private void showEditProfile() {
        Fragment fragment = new EditProfileFragment(userProfileUpdated);
        showFragment(fragment);
    }

    private OnUserProfileUpdated userProfileUpdated = this::setUsername;

    private void logout() {
        presenter.logout();
        checkButtons();
    }

    private void openWebView(String url, String title) {
        Fragment fragment = WebViewFragment.getInstance(url, title);
        showFragment(fragment);
    }

    private void showFragment(Fragment fragment) {
        Util.addFragmentToBackStack(getFragmentManager(), R.id.fragment_container, fragment, "fragment");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 22 && resultCode == LoginActivity.LOGGED_IN_CODE) {
            onBackCallback.onBack();
        } else
            super.onActivityResult(requestCode, resultCode, data);
    }
}
