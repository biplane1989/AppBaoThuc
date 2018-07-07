package com.example.tomato.appbaothuc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class GetUp extends AppCompatActivity implements View.OnClickListener{

    Button btnStop;
    TextView tvTimeNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_up);

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        setWidget();
        setEvent();
        init();

//        Intent intent = new Intent(this, Music.class);
//        intent.putExtra("extra", "on");
//        startService(intent);
    }

    private void init() {
        Calendar calendar = Calendar.getInstance();
       int hour = calendar.get(Calendar.HOUR);
       int minute = calendar.get(Calendar.MINUTE);
       tvTimeNow.setText(hour + " : "+minute);

    }

    private void setEvent() {
        btnStop.setOnClickListener(this);
    }

    private void setWidget() {
        btnStop = findViewById(R.id.btn_stop);
        tvTimeNow = findViewById(R.id.tv_time_now);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_stop){
            Intent intent = new Intent(this,Music.class);
            intent.putExtra("extra", "off");
           // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startService(intent);
            finish();

        }
    }
}
