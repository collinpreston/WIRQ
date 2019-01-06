package org.wirq.collinhpreston.wirq;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.viewpagerindicator.UnderlinePageIndicator;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = findViewById(R.id.pager);

        /* Getting fragment manager */
        FragmentManager fm = getSupportFragmentManager();

        /* Instantiating FragmentPagerAdapter */
        final Fragment_Pager pagerAdapter = new Fragment_Pager(fm){

        };

        /* Setting the pagerAdapter to the pager object */
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(1);

        // ViewPager Indicator
        UnderlinePageIndicator mIndicator = findViewById(R.id.indicator);
        mIndicator.setFades(false);
        mIndicator.setViewPager(pager);



    }


}
