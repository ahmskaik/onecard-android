package com.nawasoft.companyapp.addbill.mvp;

import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.AddBillRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCompanyCardsRequest;
import com.nawasoft.datalayer.http.apimodel.request.GetCurrenciesRequest;
import com.nawasoft.datalayer.http.apimodel.response.base.BaseResponse;
import com.nawasoft.datalayer.http.apimodel.response.companycard.GetCompanyCardsData;
import com.nawasoft.datalayer.http.apimodel.response.companycard.GetCompanyCardsResponse;
import com.nawasoft.datalayer.http.companyservice.IApiCompanyService;
import com.nawasoft.datalayer.model.CompanyCard;
import com.nawasoft.datalayer.model.Currency;
import com.nawasoft.datalayer.sharedpref.ISharedPref;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class AddBillModel implements AddBillMVP.Model {

    private IApiCompanyService service;
    private ISharedPref sharedPref;

    public AddBillModel(IApiCompanyService service, ISharedPref sharedPref) {
        this.service = service;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<List<Currency>> getCurrencies() {
        return service.getCurrencies(
                new GetCurrenciesRequest(
                        ApiTags.GetCurrencies.getValue(),
                        sharedPref.getLanguage()
                )
        );
    }

    @Override
    public Observable<List<CompanyCard>> getCompanyCards() {
        return service.getCompanyCards(
                new GetCompanyCardsRequest(
                        ApiTags.GetCompanyCards.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getCompanyId())
                )
        ).flatMap((Func1<GetCompanyCardsResponse, Observable<List<CompanyCard>>>) getCompanyCardsResponse -> {
            GetCompanyCardsData data = getCompanyCardsResponse.getData().getData();
            if (data == null)
                return Observable.empty();
            String offerBaseLink = data.getBaseOfferPhotoLink();
            String companyBaseLink = data.getBaseCompanyPhotoLink();
            String categoryBaseLink = data.getBaseCategoryPhotoLink();

            for (CompanyCard companyCard :
                    data.getCompanyCards()) {
                companyCard.setOfferPhotoLink(offerBaseLink + companyCard.getOfferPhotoLink());
                companyCard.setCompanyPhotoLink(companyBaseLink + companyCard.getCompanyPhotoLink());
                companyCard.setCategoryPhotoLink(categoryBaseLink + companyCard.getCategoryPhotoLink());
            }

            return Observable.just(data.getCompanyCards());
        });
    }

    @Override
    public Observable<BaseResponse> addBill(String memberId, String billValue, String saleValue, String currencyId, String offerId) {
        return service.addBill(
                new AddBillRequest(
                        ApiTags.AddBill.getValue(),
                        sharedPref.getLanguage(),
                        String.valueOf(sharedPref.getCompanyId()),
                        memberId,
                        billValue,
                        saleValue,
                        currencyId,
                        offerId
                )
        );
    }
}
