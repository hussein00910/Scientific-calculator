package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CalcPagerAdapter extends FragmentStateAdapter {

    CalcPagerAdapter(FragmentActivity fa) { super(fa); }

    @NonNull @Override
    public Fragment createFragment(int position) {
        return position == 0 ? new BasicFragment() : new ScientificFragment();
    }

    @Override public int getItemCount() { return 2; }
}
