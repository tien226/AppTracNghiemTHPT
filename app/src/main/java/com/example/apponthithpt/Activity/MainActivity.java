package com.example.apponthithpt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.apponthithpt.AlarmReceiver;
import com.example.apponthithpt.Fragment.HomeFragment;
import com.example.apponthithpt.Fragment.InfoAppFragment;
import com.example.apponthithpt.Fragment.ScoreboardFragment;
import com.example.apponthithpt.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.right_animation, 0);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle("Trang Chủ");
        navigationView = findViewById(R.id.navigation_view);

//        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            showDefaultFragment();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        //notification app
        creatNotificationChannel();

        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long tenSecondsMillis = 1000 * 5;
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + tenSecondsMillis, pendingIntent);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                showDefaultFragment();
                toolbar.setTitle("Trang Chủ");
                break;
            case R.id.nav_scoreboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ScoreboardFragment()).commit();
                toolbar.setTitle("Bảng Điểm");
                break;

            case R.id.nav_infoapp:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new InfoAppFragment()).commit();
                toolbar.setTitle("Thông Tin");
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void showDefaultFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Ôn Tập Trắc Nghiệm THPT");
        }
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