package com.example.tomato.appbaothuc;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class Music extends Service {

    MediaPlayer mediaPlayer;
    int id;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String key = intent.getExtras().getString("extra");
        Log.d("lala", "onStartCommand: " + key);

        if (key.equals("on")) {
            id = 1;
        } else if(key.equals("off")){
            id = 0;
        }

        if(id == 1){

            //phát nhạc
            mediaPlayer = MediaPlayer.create(this, R.raw.atime);
            mediaPlayer.start();
            id= 0;
        }else if(id ==0){
            mediaPlayer.stop();
            mediaPlayer.reset();
             android.os.Process.killProcess(android.os.Process.myPid());
        }

       // android.os.Process.killProcess(android.os.Process.myPid());

        return START_NOT_STICKY;
    }
}
