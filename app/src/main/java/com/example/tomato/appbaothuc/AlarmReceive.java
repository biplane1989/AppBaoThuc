package com.example.tomato.appbaothuc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceive extends BroadcastReceiver {   // là thằng trung gian
    @Override
    public void onReceive(Context context, Intent intent) {

      //  Log.e("khoanhkhac","alarmreceive");

        String chuoi = intent.getExtras().getString("extra");
        Log.e("bạn nhân được", chuoi);

        Intent myIntent1 = new Intent(context,Music.class);
        Intent myIntent = new Intent(context,GetUp.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);

        myIntent1.putExtra("extra", chuoi);

        // khi nhận được j đó. lệnh sau sẽ gọi service lên chạy cùng vs
        context.startService(myIntent1);


        // đoạn code làm sáng màn hình khi thông báo ngay cả khi tát máy
        PowerManager pm = (PowerManager) context
                .getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock =
                pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                        | PowerManager.ACQUIRE_CAUSES_WAKEUP
                        | PowerManager.ON_AFTER_RELEASE, "WakeLockLauncher");
        wakeLock.acquire();

    }
}
