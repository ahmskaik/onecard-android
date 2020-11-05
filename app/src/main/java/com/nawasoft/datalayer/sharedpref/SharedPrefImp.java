package com.nawasoft.datalayer.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.nawasoft.datalayer.model.User;

public class SharedPrefImp implements ISharedPref {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPrefImp(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
    }

    @Override
    public void setLoggedIn() {
        sharedPreferences.edit().putBoolean("loggedIn", true).apply();
    }

    @Override
    public void setLoggedOut() {
        setCompanyId(-1);
        sharedPreferences.edit().putBoolean("loggedIn", false).apply();
    }

    @Override
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean("loggedIn", false);
    }

    @Override
    public void setLanguage(String language) {
        sharedPreferences.edit().putString("language", language).apply();
    }

    @Override
    public String getLanguage() {
        return sharedPreferences.getString("language", "en");
    }

    @Override
    public void setMemberId(int id) {
        sharedPreferences.edit().putInt("memberId", id).apply();
    }

    @Override
    public int getMemberId() {
        return sharedPreferences.getInt("memberId", -1);
    }

    @Override
    public void saveUser(User user) {
        String json = gson.toJson(user);
        sharedPreferences.edit().putString("user", json).apply();
    }

    @Override
    public User getUser() {
        return gson.fromJson(sharedPreferences.getString("user", null), User.class);
    }

    @Override
    public void saveCountry(String countryId) {
        sharedPreferences.edit().putString("country", countryId).apply();
    }

    @Override
    public String getSavedCountry() {
        return sharedPreferences.getString("country", "");
    }

    @Override
    public void saveCity(String cityId) {
        sharedPreferences.edit().putString("city", cityId).apply();
    }

    @Override
    public String getSavedCity() {
        return sharedPreferences.getString("city", "");
    }


    // 0 -> member, 1 -> company
    @Override
    public void setAccountType(int type) {
        sharedPreferences.edit().putInt("account_type", type).apply();
    }

    @Override
    public int getAccountType() {
        return sharedPreferences.getInt("account_type", 0);
    }

    @Override
    public void setCompanyId(int id) {
        sharedPreferences.edit().putInt("company_id", id).apply();
    }

    @Override
    public int getCompanyId() {
        return sharedPreferences.getInt("company_id", -1);
    }

    @Override
    public void setToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
    }

    @Override
    public String getToken() {
        return sharedPreferences.getString("token", "");
    }
}
