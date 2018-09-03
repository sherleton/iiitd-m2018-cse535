package com.example.nikhi.mulock;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentTraveller extends FragmentStatePagerAdapter {

    private final List<Fragment> f = new ArrayList<>();
    private final List<String> s = new ArrayList<>();

    public FragmentTraveller(FragmentManager fm) {
        super(fm);
    }

    public void addf(Fragment fr, String st){
        f.add(fr);
        s.add(st);
    }

    @Override
    public Fragment getItem(int i) {
        return f.get(i);
    }

    @Override
    public int getCount() {
        return f.size();
    }
}
