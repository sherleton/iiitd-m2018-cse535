package com.example.nikhi.mulock;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Download extends Fragment {

    ImageButton download;
    DownloadManager d;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.download_fragment, container, false);
        download = view.findViewById(R.id.dbutton);

        download.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                d = (DownloadManager)getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                Uri u = Uri.parse("https://faculty.iiitd.ac.in/~mukulika/s1.mp3");
                DownloadManager.Request re = new DownloadManager.Request(u);
                re.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long r = d.enqueue(re);
            }
        });

        return view;
    }
}
