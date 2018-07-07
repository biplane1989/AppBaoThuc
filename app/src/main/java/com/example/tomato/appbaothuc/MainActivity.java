package com.example.tomato.appbaothuc;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSettime, btnStop;
    TextView tvResult;
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        setWidget();
        init();
        setEvent();
    }

    private void setEvent() {
        btnStop.setOnClickListener(this);
        btnSettime.setOnClickListener(this);
    }

    private void setWidget() {
        btnSettime = findViewById(R.id.btn_set_time);
        btnStop = findViewById(R.id.btn_stop);
        timePicker = findViewById(R.id.tp_demo);
        tvResult = findViewById(R.id.tv_result);

    }

    private void init() {
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);  // truy cập vào hệ thống báo động
        intent = new Intent(MainActivity.this, AlarmReceive.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_time:
                doSetTime();
                break;
            case R.id.btn_stop:
                stopMusic();

                break;
            default:
                break;
        }

    }

    private void stopMusic() {
        intent.putExtra("extra", "off");
        //  pendingIntent.cancel();     // dừng lại pendingIntent
        alarmManager.cancel(pendingIntent);
        sendBroadcast(intent);
    }

    private void doSetTime() {
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

        int hour = timePicker.getCurrentHour();     // lấy giờ
        int minute = timePicker.getCurrentMinute();

        String string_hour = String.valueOf(hour);
        String string_minute = String.valueOf(minute);

        if (hour > 12) {
            string_hour = String.valueOf(hour - 12);
        } else {
            string_minute = "0" + String.valueOf(minute);
        }

        intent.putExtra("extra", "on");

        // có thể tồn tại trong suốt ứng dụng ngay cả khi đống ứng dụng
        pendingIntent = PendingIntent.getBroadcast(
                MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT
        );

        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

        tvResult.setText(string_hour + " - " + string_minute);
    }
}
