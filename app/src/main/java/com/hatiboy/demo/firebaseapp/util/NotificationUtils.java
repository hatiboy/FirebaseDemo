package com.hatiboy.demo.firebaseapp.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.hatiboy.demo.firebaseapp.R;

public class NotificationUtils {


    public void showNotification() {
        String name = "channel";
        String description = "channel des"; // The user-visible description of the channel.

        Log.i(TAG , "showNotification:title=" + title + ";msg=" + message);
        try {
            String appTitle = "demo Notitification";

            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
            bigTextStyle.setBigContentTitle(title);
            bigTextStyle.bigText(message);
            bigTextStyle.setSummaryText(appTitle);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                assert mNotificationManager != null;
                NotificationChannel mChannel = mNotificationManager.getNotificationChannel(id);
                if (mChannel == null) {
                    mChannel = new NotificationChannel(id, name, importance);
                    mChannel.setDescription(description);
                    mChannel.enableVibration(true);
                    mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    mNotificationManager.createNotificationChannel(mChannel);
                }
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,id)
                        .setWhen(System.currentTimeMillis())
                        .setContentText(message)
                        .setContentTitle(title)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setAutoCancel(true)
                        .setTicker(title)
                        .setStyle(bigTextStyle)
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(context, mainClass);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                resultIntent.putExtra("title", title);
                resultIntent.putExtra("message", message);
                resultIntent.putExtra("id", id);
                resultIntent.putExtra("link", link);
                resultIntent.putExtra("url", imageUrl);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(mainClass);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 959,
                        resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(1, mBuilder.build());
            } else {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, id)
                        .setWhen(System.currentTimeMillis())
                        .setContentText(message)
                        .setContentTitle(title)
                        .setSmallIcon(R.drawable.ic_launcher_background)
//                        .setLargeIcon(Bitmap.createScaledBitmap(bitmap, bitmapSize, bitmapSize, false))
                        .setAutoCancel(true)
                        .setTicker(title)
                        .setStyle(bigTextStyle)
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(context, mainClass);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                resultIntent.putExtra("title", title);
                resultIntent.putExtra("message", message);
                resultIntent.putExtra("id", id);
                resultIntent.putExtra("link", link);
                resultIntent.putExtra("url", imageUrl);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(mainClass);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 959,
                        resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(1, mBuilder.build());
            }
            // The stack builder object will contain an artificial back stack for
            // the started Activity.
            // This ensures that navigating backward from the Activity leads out of
            // your application to the Home screen.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    private static String TAG = "NotificationUtils";

    private Class mainClass;
    private Context context;
    private String title;
    private String message;
    private String imageUrl;
    private String id;
    private String link;

    private static NotificationUtils instance;


    public NotificationUtils(Context context) {
        this.context = context;
    }

    public static NotificationUtils getInstance(Context context){
        if(instance == null){
            instance = new NotificationUtils(context);
        }
        return instance;
    }

    public Class getMainClass() {
        return mainClass;
    }

    public NotificationUtils setMainClass(Class mainClass) {
        this.mainClass = mainClass;
        return this;
    }

    public NotificationUtils setImageUrl(String url){
        this.imageUrl = url;
        return this;
    }

    public String getImageUrl(){
        return imageUrl;
    }
    public Context getContext() {
        return context;
    }

    public NotificationUtils setContext(Context context) {
        this.context = context;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NotificationUtils setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public NotificationUtils setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getId() {
        return id;
    }

    public NotificationUtils setId(String id) {
        this.id = id;
        return this;
    }

    public String getLink() {
        return link;
    }

    public NotificationUtils setLink(String link) {
        this.link = link;
        return this;
    }
}
