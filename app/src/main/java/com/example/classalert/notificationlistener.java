package com.example.classalert;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.provider.AlarmClock;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class notificationlistener extends NotificationListenerService {

    SharedPreferences preferences;

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        preferences = this.getSharedPreferences("Name", Context.MODE_PRIVATE);

//        Log.d("asdf","**********  onNotificationPosted");
//        Toast.makeText(getApplicationContext(), sbn.getPackageName(),Toast.LENGTH_LONG).show();
        if(sbn.getPackageName().equals("com.whatsapp"))
        {
            String text = sbn.getNotification().extras.getString("android.text");

            if(text!=null && !preferences.getString("Value","").equals(text) && text.contains("https://meet.google.com"))
            {
                preferences.edit().putString("Value",text).apply();
                startActivity(new Intent(this, MainActivity2.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
          //  Log.d("asdf",sbn.getNotification().extras.getString("android.title").toString());


//            Intent intent= new Intent(AlarmClock.ACTION_SET_ALARM);
//            intent.putExtra(AlarmClock.EXTRA_HOUR,Integer.parseInt(new SimpleDateFormat("HH").format(Calendar.getInstance().getTime())));
//            intent.putExtra(AlarmClock.EXTRA_MINUTES,Integer.parseInt(new SimpleDateFormat("mm").format(Calendar.getInstance().getTime()))+1);
//            startActivity(intent);
//            Intent intent = new Intent(this, MyBroadcastReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                    this.getApplicationContext(), 234324243, intent, 0);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
//                    + (1 * 1000), pendingIntent);

        }

    }
}
