package com.nawasoft.oneapp.login.mvp;

import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.LoginRequest;
import com.nawasoft.datalayer.http.apimodel.response.login.LoginResponse;
import com.nawasoft.datalayer.http.apimodel.response.login.LoginResponseData;
import com.nawasoft.datalayer.model.AccountType;
import com.nawasoft.datalayer.model.User;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import rx.Observable;

public class LoginModel implements LoginMVP.Model {

    private IApiService service;
    private ISharedPref sharedPref;

    LoginModel(IApiService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<LoginResponse> login(String emailAddress, String password) {
        return service.login(new LoginRequest(
                ApiTags.Login.getValue(),
                sharedPref.getLanguage(),
                emailAddress,
                password,
                sharedPref.getToken()
        )).doOnNext(loginResponse -> {
            if (loginResponse.getMeta().getStatusNumber() == 200) {
                LoginResponseData data = loginResponse.getData().getData();
                AccountType accountType = data.getLoginType().equalsIgnoreCase("member")
                        ? AccountType.MEMBER
                        : AccountType.COMPANY;
                if (accountType == AccountType.MEMBER) {
                    User user = data.getUsers().get(0);
                    sharedPref.setMemberId(Integer.valueOf(user.getUserId()));
                    sharedPref.saveUser(user);
                } else {
                    sharedPref.setCompanyId(Integer.valueOf(data.getCompanies().get(0).getCompanyId()));
                }
                sharedPref.setLoggedIn();
                sharedPref.setAccountType(accountType.getValue());
            }
        });
    }

    @Override
    public AccountType getAccountType() {
        return sharedPref.getAccountType() == 0 ? AccountType.MEMBER : AccountType.COMPANY;
    }
}
