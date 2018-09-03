package com.example.nikhi.mulock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String store = intent.getAction();
        if(store.equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Power Connected", Toast.LENGTH_SHORT).show();
        }
        else if(store.equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context, "Power Disconnected", Toast.LENGTH_LONG).show();
        }
    }
}
