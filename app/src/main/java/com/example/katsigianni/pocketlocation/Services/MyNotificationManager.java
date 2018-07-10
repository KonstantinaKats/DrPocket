package com.example.katsigianni.pocketlocation.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.katsigianni.pocketlocation.Activities.LoggedActivity;
import com.example.katsigianni.pocketlocation.Common;
import com.example.katsigianni.pocketlocation.R;

/**
 * Created by KonstantinaKats on 01-Jul-18.
 */

public class MyNotificationManager {

    private Context mCtx;
    private static MyNotificationManager mInstance;

    public MyNotificationManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized MyNotificationManager getmInstance(Context context){
        if (mInstance == null){
            mInstance = new MyNotificationManager(context);
        }
        return mInstance;
    }

    public void displayNotification(String title, String body){
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(mCtx)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true);

        Intent intent = new Intent(mCtx, LoggedActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, 0, intent , PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null){
            notificationManager.notify(1, mBuilder.build());
        }
    }
}
