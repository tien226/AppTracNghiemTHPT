package com.example.apponthithpt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apponthithpt.AlarmReceiver;
import com.example.apponthithpt.R;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    Animation topanimation, bottomanimation, middleanimation;
    TextView tvslogan;
    ImageView imglogo;
    View firts, second, third, fourth, fifth, sixth;
    private static int Splash_Time_Out = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
//        overridePendingTransition(R.anim.top_animation, R.anim.bottom_animation);

        imglogo = findViewById(R.id.imglogo_SS);
        tvslogan = findViewById(R.id.tvtext_SS);
        firts = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        third = findViewById(R.id.third_line);
        fourth = findViewById(R.id.fourth_line);
        fifth = findViewById(R.id.fifth_line);
        sixth = findViewById(R.id.sixth_line);

        topanimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomanimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleanimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);

        imglogo.setAnimation(middleanimation);
        tvslogan.setAnimation(bottomanimation);
        firts.setAnimation(topanimation);
        second.setAnimation(topanimation);
        third.setAnimation(topanimation);
        fourth.setAnimation(topanimation);
        fifth.setAnimation(topanimation);
        sixth.setAnimation(topanimation);


        //notification app
        creatNotificationChannel();

        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long tenSecondsMillis = 1000 * 15;
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + tenSecondsMillis, pendingIntent);


        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(Splash_Time_Out);
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        splashThread.start();

        //        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },Splash_Time_Out);

    }

    private void creatNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Important Notification";
            String description = "Channel for important notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifylenbit", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}