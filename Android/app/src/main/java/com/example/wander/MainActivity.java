package com.example.wander;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.wander.feed.FeedFragment;
import com.example.wander.home.HomeFragment;
import com.example.wander.profile.ProfileFragment;
import com.example.wander.wanderMap.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = getFragments();

        MyPageAdapter pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        pager = findViewById(R.id.pager);
        pager.setAdapter(pageAdapter);
        //Setting the pager position to 1 to open the home fragment first
        pager.setCurrentItem(1,true);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnBottomNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.camera_page);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigation.setSelectedItemId(R.id.profile_page);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.camera_page);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.feeds_page);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.map_page);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnBottomNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.profile_page:
                    pager.setCurrentItem(0);
                    break;

                case R.id.camera_page:
                    pager.setCurrentItem(1);
                    break;

                case R.id.feeds_page:
                    pager.setCurrentItem(2);
                    break;

                case R.id.map_page:
                    pager.setCurrentItem(3);
                    break;
            }

            return true;
        }
    };



    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();
        fList.add(new ProfileFragment());
        fList.add(new HomeFragment());
        fList.add(new FeedFragment());
        fList.add(new MapFragment());
        return fList;
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments ) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position)
        {
            return this.fragments.get(position);
        }

        @Override
        public int getCount()
        {
            return this.fragments.size();
        }
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Click on the logout button",Toast.LENGTH_SHORT).show();
    }
}
