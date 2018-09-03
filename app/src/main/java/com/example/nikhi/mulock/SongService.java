package com.example.nikhi.mulock;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class SongService extends Service {

    MediaPlayer mp;
    int p = -1, po;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mp != null)
        {
            mp.stop();
            mp.reset();
            mp.release();
            mp = null;
        }

        switch (MusicList.pos)
        {
            case 0: mp = MediaPlayer.create(this, R.raw.wiz);

                    break;
            case 1: mp = MediaPlayer.create(this, R.raw.shane);
                    break;
            default: break;
        }

        if(p == MusicList.pos && p != -1)
            mp.seekTo(po);
        else
            p = MusicList.pos;
        mp.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        po = mp.getCurrentPosition();
        mp.reset();
        mp.release();
        mp = null;
    }
}
