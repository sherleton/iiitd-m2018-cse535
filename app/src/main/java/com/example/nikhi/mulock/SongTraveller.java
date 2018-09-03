package com.example.nikhi.mulock;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongTraveller extends RecyclerView.Adapter<SongTraveller.SongHold> {

    private ArrayList<SInfo> list;
    private Context c;

    itemListener il;

    SongTraveller(Context c, ArrayList<SInfo> list){
        this.c = c;
        this.list = list;
    }

    public interface itemListener{
        void onClick(TextView b, ImageView iv, View v, SInfo s, int i);
    }

    public void onItemListener(itemListener il){
        this.il = il;
    }

    @NonNull
    @Override
    public SongHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(c).inflate(R.layout.song, viewGroup, false);
        return new SongHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongHold songHold, final int i) {
        final SInfo s = list.get(i);
        songHold.song.setText(s.name);
        songHold.artist.setText((s.artist));
        songHold.keynote.setImageResource(R.drawable.blank);

        songHold.song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(il != null){
                    il.onClick(songHold.song, songHold.keynote, v, s, i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SongHold extends RecyclerView.ViewHolder{

        TextView song, artist;
        ImageView keynote;

        public SongHold(View itemView) {
            super(itemView);
            song = itemView.findViewById(R.id.songname);
            artist = itemView.findViewById(R.id.artistname);
            keynote = itemView.findViewById(R.id.keynote);
        }
    }
}
