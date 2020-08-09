package com.example.classalert;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
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

    Animation animBlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ivAlarm = findViewById(R.id.ivAlarm);
        tvAlert = findViewById(R.id.tvAlert);

        Glide.with(MainActivity2.this).load(R.drawable.alarm_animation).into(ivAlarm);

        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        tvAlert.startAnimation(animBlink);

        mp= MediaPlayer.create(this,R.raw.fantasy_alarm_clock);
        mp.setLooping(true);
        mp.start();
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp!=null) {
                    mp.stop();
                    mp.release();
                    finish();
                }
            }
        });
    }
}