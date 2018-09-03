package com.example.nikhi.mulock;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class NetworkService extends Service {

    DownloadManager d;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ConnectivityManager connect = (ConnectivityManager) getSystemService(Service.CONNECTIVITY_SERVICE);
        if(connect != null)
        {
            NetworkInfo ni = connect.getActiveNetworkInfo();
            if(ni != null)
            {
                if(ni.getState() == NetworkInfo.State.CONNECTED)
                {
                    d = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri u = Uri.parse("https://faculty.iiitd.ac.in/~mukulika/s1.mp3");
                    DownloadManager.Request re = new DownloadManager.Request(u);
                    re.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    Long r = d.enqueue(re);
                }
                else
                    Toast.makeText(this, "No internet Connection! Try Again", Toast.LENGTH_LONG).show();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
