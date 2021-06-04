package com.example.lastexam_letienloi;

import com.example.lastexam_letienloi.Fragment.Adding;
import com.example.lastexam_letienloi.Fragment.ListNoteFrag;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabAdapter extends FragmentStatePagerAdapter {
    private int numPage=2;
    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public TabAdapter(FragmentManager fm, int behav) {
        super(fm, behav);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new ListNoteFrag();
            case 1:return new Adding();
            default:return new ListNoteFrag();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
