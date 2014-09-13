package com.example.mikhail.lightup;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.SystemClock;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by Mikhail on 06.09.2014.
 */
class TeleListener extends PhoneStateListener {
    static Thread myThread;
    private  Camera camera = Camera.open();
    Camera.Parameters param = camera.getParameters();
    static boolean bool = true;
    static long flicker =250;


    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);


        switch (state) {

            case TelephonyManager.CALL_STATE_IDLE:
                bool=false;
                myThread = null;
                System.out.println("CALL_STATE_IDLE");
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                bool=false;
                myThread = null;
                System.out.println("CALL_STATE_OFFHOOK");
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                bool = true;
                System.out.println(incomingNumber);

                myThread = new Thread(
                        new Runnable() { // описываем объект Runnable в конструкторе
                            public void run() {
                                int i=0;
                                while(bool){
                                    SystemClock.sleep(flicker);
                                    if(i%2 == 0)
                                    {
                                        param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                                        camera.setParameters(param);
                                    }
                                    else
                                    {
                                        param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                                        camera.setParameters(param);
                                    }
                                    i++;
                                }
                                param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                                camera.setParameters(param);
                            }
                        }
                );
                myThread.start();
                break;
            default:
                bool=false;
                myThread = null;
                break;
        }

    }

}

