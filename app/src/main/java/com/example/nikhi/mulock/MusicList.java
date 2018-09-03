package com.example.nikhi.mulock;

import android.Manifest;
import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.getSystemService;

public class MusicList extends Fragment{

    ImageButton play, forward, backward, online, stop;

    ArrayList<SInfo> list = new ArrayList<>();
    SongTraveller st;
    RecyclerView rv;
    MediaPlayer mp;
    View view;
    SInfo s;
    DownloadManager d;
    Handler h = new Handler();
    static int pos = -1;
    boolean playing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_fragment, container, false);

        play = view.findViewById(R.id.play);
        forward = view.findViewById(R.id.forward);
        backward = view.findViewById(R.id.backward);
        online = view.findViewById(R.id.online);
        stop = view.findViewById(R.id.stop);

        rv = view.findViewById(R.id.listview);
        s = new SInfo("Hopeless Romantic", "Wiz Khalifa", "sfd");
        list.add(s);
        s = new SInfo("Feels like the end", "Shane Alexander", "abcd");
        list.add(s);

        st = new SongTraveller(view.getContext(), list);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration did = new DividerItemDecoration(view.getContext(), llm.getOrientation());
        rv.addItemDecoration(did);
        rv.setLayoutManager(llm);

        st.onItemListener(new SongTraveller.itemListener() {
            @Override
            public void onClick(TextView b, final ImageView iv, View v, final SInfo s, final int i) {

                    if(pos == i)
                        getActivity().stopService(new Intent(getActivity(), SongService.class));
                    pos = i;
                    getActivity().startService(new Intent(getActivity(), SongService.class));

            }
        });

        online.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getActivity().startService(new Intent(getActivity(), NetworkService.class));
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startService(new Intent(getActivity(), SongService.class));
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(new Intent(getActivity(), SongService.class));
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = (pos + 1)%(list.size());
                getActivity().startService(new Intent(getActivity(), SongService.class));
            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = (list.size() + pos - 1)%(list.size());
                getActivity().startService(new Intent(getActivity(), SongService.class));
            }
        });

        rv.setAdapter(st);
        return view;
    }
}
