package com.nawasoft.service.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.nawasoft.oneapp.R;

abstract public class BaseNotifications {

    protected Context context;
    protected SharedPreferences sharedPreferences;

    public BaseNotifications(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("notification", Context.MODE_PRIVATE);
        createNotificationChannel();
    }

    abstract protected String getChannelName();

    abstract protected String getChannelId();

    public void makeNotification(@NonNull NotificationCompat.Builder notificationBuilder) {
        notificationBuilder = notificationBuilder.setChannelId(getChannelId());
        publishNotification(notificationBuilder.build(), generateNotificationId());
    }

    public void makeNotification(String title, String message, PendingIntent pendingIntent, Bitmap largeImage) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, getChannelId())
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_logo)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        if (title != null)
            notificationBuilder = notificationBuilder.setContentTitle(title);
        if (message != null)
            notificationBuilder = notificationBuilder.setContentText(message);
        if (largeImage != null)
            notificationBuilder = notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(largeImage));
        if (pendingIntent != null)
            notificationBuilder = notificationBuilder.setContentIntent(pendingIntent).setAutoCancel(true);
        makeNotification(notificationBuilder);
    }

    public void makeNotification(String title, String message, PendingIntent pendingIntent) {
        makeNotification(title, message, pendingIntent, (Bitmap) null);
    }

    public void makeNotification(String title, String message) {
        makeNotification(title, message, null);
    }


    public void publishNotification(Notification notification, int notificationId) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, notification);
    }

    public void publishNotification(Notification notification) {
        publishNotification(notification, generateNotificationId());
    }

    protected void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getChannelName();
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(getChannelId(), name, importance);
            channel.enableLights(true);
            channel.enableVibration(true);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private int generateNotificationId() {
        int id = sharedPreferences.getInt("id", 0);
        sharedPreferences.edit().putInt("id", (id + 1) % 1000).apply();
        return id;
    }
}
