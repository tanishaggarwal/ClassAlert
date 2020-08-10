package com.example.classalert;

import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity2 extends AppCompatActivity {

    MediaPlayer mp;
    Button btn;
    ImageView ivAlarm;
    TextView tvAlert;
    PowerManager.WakeLock wakeLock;
    Animation animBlink;
    KeyguardManager km;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
//        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP
//                | PowerManager.ON_AFTER_RELEASE, "app:myapplock");
//        wakeLock.acquire();
//        km = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
//        final KeyguardManager.KeyguardLock kl = km.newKeyguardLock("MyKeyguardLock");
//        kl.disableKeyguard();

        setContentView(R.layout.activity_main2);

        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.FROYO) {
            // only for gingerbread and newer versions
            (this).getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
            ( this).getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
            ( this).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

             km =(KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock lock = km.newKeyguardLock("abc");
            lock.disableKeyguard();

        } else {

             km = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock keyguardLock = km.newKeyguardLock("TAG");
            (this).getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
            (this).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
            (this).getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

            keyguardLock.disableKeyguard();
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                    | PowerManager.ACQUIRE_CAUSES_WAKEUP
                    | PowerManager.ON_AFTER_RELEASE
                    | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "app:MyWakeLock");

            wakeLock.acquire();
        }




        ivAlarm = findViewById(R.id.ivAlarm);
        tvAlert = findViewById(R.id.tvAlert);

        Glide.with(MainActivity2.this).load(R.drawable.alarm_animation).into(ivAlarm);

        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        tvAlert.startAnimation(animBlink);

        mp= MediaPlayer.create(this,R.raw.fantasy_alarm_clock);
//        mp.setLooping(true);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()) {
                    if(km.inKeyguardRestrictedInputMode()) {
                        mp.stop();
                    }
                        finish();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(wakeLock!=null) {
            wakeLock.release();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp!=null && mp.isPlaying()) {
          mp.stop();
        }
        }

    @Override
    protected void onPause() {
        super.onPause();
        if(mp!=null && mp.isPlaying()) {
            if(!km.inKeyguardRestrictedInputMode()) {
               // mp.stop();
                finish();
            }
        }
    }


}