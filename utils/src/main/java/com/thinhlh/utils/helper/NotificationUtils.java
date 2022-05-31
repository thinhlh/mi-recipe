package com.thinhlh.utils.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Objects;

/**
 * Created by thinhlh on 02/03/2022.
 * Copyright (c). All rights reserved
 */
public abstract class NotificationUtils {

    /**
     * Create a notification channel for Android O and later
     *
     * @param context     - Application context
     * @param channelId   - ID of notification
     * @param channelName - Name of notification channel
     */
    public static void createNotificationChannel(
            Context context, String channelId, String channelName
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            Objects.requireNonNull(manager).createNotificationChannel(serviceChannel);
        }
    }

    /**
     * Build new notification
     *
     * @param context          - Application context
     * @param channelId        - ID of notification channel
     * @param channelName      - Name of notification channel
     * @param notificationIcon - Notification icon
     * @return NotificationCompat.Builder
     */
    public static Notification buildNotification(
            Context context,
            String channelId,
            String channelName,
            int notificationIcon,
            String contentTitle,
            String contentText,
            int priority,
            @Nullable PendingIntent pendingIntent
    ) {
        return new NotificationCompat.Builder(context, channelName)
                .setSmallIcon(notificationIcon)
                .setChannelId(channelId)
                .setContentTitle(contentTitle)
                .setTicker(contentTitle)
                .setContentText(contentText)
                .setPriority(priority)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .build();
    }
}
