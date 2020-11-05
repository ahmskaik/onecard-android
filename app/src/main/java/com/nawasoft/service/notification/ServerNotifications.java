package com.nawasoft.service.notification;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

// Helper class that manages a notification pushing
@Singleton
public class ServerNotifications extends BaseNotifications {

    public static final String CHANNEL_ID = "one_server_channel_id";

    private Context context;
    private SharedPreferences sharedPreferences;

    @Inject
    public ServerNotifications(Context context) {
        super(context);
    }

    @Override
    protected String getChannelName() {
        return "One Public Notifications";
    }

    @Override
    protected String getChannelId() {
        return CHANNEL_ID;
    }
}
