package com.example.classalert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
         mp= MediaPlayer.create(context,R.raw.fantasy_alarm_clock);
        mp.start();

    }
}
