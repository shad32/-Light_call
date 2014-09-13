package com.example.mikhail.lightup;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by Mikhail on 06.09.2014.
 */
public class MyService extends Service {

    public void onCreate(){
        super.onCreate();
        System.out.println("Create");
    }
    public void onDestroy(){
        super.onDestroy();

        System.out.println("Destroy");
    }
    public int onStartCommand(Intent intent,int flags,int startId){
        System.out.println("start");
        TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        TelephonyMgr.listen(new TeleListener(), PhoneStateListener.LISTEN_CALL_STATE);
        return START_REDELIVER_INTENT;//super.onStartCommand(intent,flags,startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
