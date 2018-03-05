package com.hatiboy.demo.firebaseapp.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hatiboy.demo.firebaseapp.R;

import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private NotificationManager notificationManager;
    private String ADMIN_CHANNEL_ID = "";

    public MyFirebaseMessagingService() {
    }

    private String TAG = "MyFirebaseMessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived: ");
        String title = "";
        String body = "";
        String messageID = "";
        try {
            messageID = remoteMessage.getMessageId();
            title = remoteMessage.getNotification().getTitle();
            body = remoteMessage.getNotification().getBody();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Title: " + title);
        Log.d(TAG, "Body: " + body);
        Log.d(TAG, "messageID" + messageID);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Setting up Notification channels for android O and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setupChannels();
        }
        int notificationId = new Random().nextInt(60000);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("message")) //ditto
                .setAutoCancel(true) //dismisses the notification on click
                .setSound(defaultSoundUri);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels() {
        CharSequence adminChannelName = getString(R.string.notifications_admin_channel_name);
        String adminChannelDescription = getString(R.string.notifications_admin_channel_description);
        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_LOW);
        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }
    }

    @Override
    public void onSendError(String s, Exception e) {
        Log.d(TAG, "onSendError: ");
    }

    @Override
    public void onMessageSent(String s) {
        Log.d(TAG, "onMessageSent: ");
    }


    @Override
    public void onDeletedMessages() {
        Log.d(TAG, "onDeletedMessages: ");
    }
}