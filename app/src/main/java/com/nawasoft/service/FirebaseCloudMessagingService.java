package com.nawasoft.service;

import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nawasoft.datalayer.http.IApiService;
import com.nawasoft.datalayer.http.apimodel.ApiTags;
import com.nawasoft.datalayer.http.apimodel.request.RefreshTokenRequest;
import com.nawasoft.datalayer.model.AccountType;
import com.nawasoft.datalayer.model.OfferNotification;
import com.nawasoft.datalayer.sharedpref.ISharedPref;
import com.nawasoft.oneapp.main.MainActivity;
import com.nawasoft.service.notification.ServerNotifications;

import java.util.Map;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class FirebaseCloudMessagingService extends FirebaseMessagingService {

    @Inject
    ISharedPref sharedPref;
    @Inject
    IApiService service;
    @Inject
    ServerNotifications serverNotifications;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        if (AccountType.getTypeByValue(sharedPref.getAccountType()) == AccountType.COMPANY)
            return;
        Map<String, String> dataMap = remoteMessage.getData();
        OfferNotification offerNotification = new OfferNotification(dataMap);
        serverNotifications.makeNotification(offerNotification.getNotificationTitle(), offerNotification.getNotificationBody(),
                getPendingIntent(getOpenOfferIntent(offerNotification.getOfferId())));

    }


    private PendingIntent getPendingIntent(Intent intent) {
        if (intent == null) return null;
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(intent);
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private Intent getOpenOfferIntent(String offerId) {
        if (offerId == null || offerId.isEmpty())
            return null;
        return MainActivity.getOpenOfferIntent(this, offerId);
    }

    @Override
    public void onNewToken(@NonNull String s) {
        sharedPref.setToken(s);
        if (sharedPref.isLoggedIn()) {
            service.refreshToken(
                    new RefreshTokenRequest(
                            ApiTags.RefreshToken.getValue(),
                            sharedPref.getLanguage(),
                            String.valueOf(sharedPref.getMemberId()),
                            s
                    )
            );
        }
    }
}
