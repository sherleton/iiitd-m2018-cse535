package com.example.nikhi.mulock;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main_A2_2016061 extends AppCompatActivity{

    private ViewPager vp;
    private FragmentTraveller ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__a2_2016061);

        vp = findViewById(R.id.container);
        ft = new FragmentTraveller(getSupportFragmentManager());

        setpager(vp);
//        vp.setCurrentItem(0);
    }

    public void setpager(ViewPager vpp){
        FragmentTraveller a = new FragmentTraveller(getSupportFragmentManager());
        a.addf(new MusicList(), "Music List");
//        a.addf(new Download(), "Download Page");
        vpp.setAdapter(a);
    }
}
