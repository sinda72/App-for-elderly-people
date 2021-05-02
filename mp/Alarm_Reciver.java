package doyeon.mp.mp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarm_Reciver extends BroadcastReceiver {
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        // intent로부터 전달받은 string
        Log.d("들우와1", "들우와1");

        String get_yout_string = intent.getExtras().getString("state");//알람상태전달받음

        // RingtonePlayingService 서비스 intent 생성
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        // RingtonePlayinService로 extra string값 보내기
        service_intent.putExtra("state", get_yout_string);
        // start the ringtone service

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            this.context.startForegroundService(service_intent);
            Log.d("test 1 :", "오레오 이상버전");

        }else{
            this.context.startService(service_intent);
            Log.d("test 1 :", "오레오 이하버전");

        }
        Log.d("test 1 :", "알람 해결");
    }
}
