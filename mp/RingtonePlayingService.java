package doyeon.mp.mp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class RingtonePlayingService extends Service {
    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "default";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            PendingIntent mPendingIntent = PendingIntent.getActivity(RingtonePlayingService.this, 0,
                    new Intent(getApplicationContext(), AlarmActivity.class),
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("할무니네 멍뭉이 약알림")
                    .setContentText("화면 이동을 원하시면 클릭해주세요.")
                    .setAutoCancel(true)//알림창클릭시 제거
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(mPendingIntent)
                    .build();

            startForeground(1, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String getState = intent.getExtras().getString("state");
        assert getState != null;
        mediaPlayer = MediaPlayer.create(this, R.raw.dog2);
        switch (getState) {
            case "alarm on":
                mediaPlayer.start();
                this.isRunning = true;
                //mediaPlayer.stop();
                break;
            default:
                mediaPlayer.stop();
                mediaPlayer.reset();
                //mediaPlayer.release();
                break;
        }
        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("onDestory() 실행", "서비스 파괴");

    }
}
