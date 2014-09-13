package com.example.mikhail.lightup;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MyActivity extends Activity{
    public ToggleButton tg;
    public TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Context context = getApplicationContext();
        CharSequence text = "Для сохранения закройте приложение!";
        final Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);


        tg = (ToggleButton)findViewById(R.id.toggleButton);
        tg.setChecked(checkService());


            tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked && !checkService()) {
                        // flicker = true;
                        startService(new Intent(MyActivity.this, MyService.class));
                        tg.setEnabled(false);
                    }
                    else
                        // flicker = false;
                    {
                        stopService(new Intent(MyActivity.this, MyService.class));

                        tg.setEnabled(false);
                    }
                    toast.show();
                }
            });
    }

    public boolean checkService(){
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (MyService.class.getName().equals(service.service.getClassName())) {

              return true;
            }
        }
        return false;
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
