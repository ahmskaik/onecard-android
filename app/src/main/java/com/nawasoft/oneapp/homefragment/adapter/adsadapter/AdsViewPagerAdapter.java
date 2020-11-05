package com.nawasoft.oneapp.homefragment.adapter.adsadapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.nawasoft.datalayer.model.HomeSlider;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.util.Util;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AdsViewPagerAdapter extends PagerAdapter {

    private List<HomeSlider> sliderList;
    private Context context;
    private ViewPager viewPager;
    private Timer timer;
    private TimerTask timerTask;

    public AdsViewPagerAdapter(Context context, ViewPager viewPager, List<HomeSlider> sliderList) {
        this.context = context;
        this.viewPager = viewPager;
        this.sliderList = sliderList;
        timer = new Timer();
    }

    @Override
    public int getCount() {
        return sliderList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_item_layout, container, false);
        ImageView imageView = view.findViewById(R.id.ad_image);
        Util.loadPictureAndCache(imageView, sliderList.get(position).getPhotoLink());
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setTimer() {
        Handler handler = new Handler();
        Runnable runnable = () -> {
            int currentPage = viewPager.getCurrentItem() + 1;
            if (currentPage >= getCount()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage, true);
        };

        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        };

        timer.schedule(timerTask, 0, 4 * 1000);
    }

    public void cancelTimer() {
        try {
            timer.cancel();
            timerTask.cancel();
        } catch (Exception ignored) {
        }

    }
}
