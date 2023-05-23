package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;
import java.util.zip.Inflater;

public class ResultsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;
    //BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        //bottomNavigationView = findViewById(R.id.bottom_nav);
        frameLayout = findViewById(R.id.frame_layout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
            }

        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch(position) {
                    case 0:
                    case 1:
                    case 2:
                        Objects.requireNonNull(tabLayout.getTabAt(position)).select();
                }
                super.onPageSelected(position);
            }
        });

        /*bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                frameLayout.setVisibility(View.VISIBLE);
                viewPager2.setVisibility(View.GONE);
                switch (item.getItemId()) {
                    case R.id.bottom1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Bottom1()).commit();
                        return true;
                    case R.id.bottom2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Bottom2()).commit();
                        return true;
                    case R.id.bottom3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Bottom3()).commit();
                        return true;
                }
                return false;
            }
        });*/

    }
}
