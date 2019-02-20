package com.example.natw.annonce;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class Adapter_ImageSlider extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }
}
