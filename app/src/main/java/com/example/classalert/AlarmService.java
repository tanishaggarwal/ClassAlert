package com.example.classalert;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent notificationintent= new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=  PendingIntent.getActivity(this,0,notificationintent,0);


        Notification notification= new NotificationCompat.Builder(this,Application.Channel_ID)
                .setContentTitle("Class Alert")
                .setContentText("It will give you the meet alert")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);
        return START_NOT_STICKY;

    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
    // * @param name Used to name the worker thread, important only for debugging.
     */

}
