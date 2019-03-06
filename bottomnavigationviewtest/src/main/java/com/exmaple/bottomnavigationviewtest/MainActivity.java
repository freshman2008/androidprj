package com.exmaple.bottomnavigationviewtest;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private MenuItem menuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewPager = findViewById(R.id.view_pager);
    }

    private void initData() {
        //方法一：去掉多个item时的动画效果, 固定显示所有item(默认情况下, 当item数量超过3个时会有默认动画效果)
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        //方法二：去掉多个item时的动画效果, 固定显示所有item(默认情况下, 当item数量超过3个时会有默认动画效果)
        //在layout文件中BottomNavigationView的属性加入app:labelVisibilityMode="labeled"
        //方法三：去掉多个item时的动画效果, 固定显示所有item(默认情况下, 当item数量超过3个时会有默认动画效果)
        bottomNavigationView.setLabelVisibilityMode(1);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        List<Fragment> list = new ArrayList<>();
        list.add(TestFragment.newInstance("首页"));
        list.add(TestFragment.newInstance("钱包"));
        list.add(TestFragment.newInstance("卡片"));
        list.add(TestFragment.newInstance("个人"));
        list.add(TestFragment.newInstance("测试"));
        viewPagerAdapter.setList(list);
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(mOnPageChangeListener);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (menuItem != null) {
                menuItem.setChecked(false);
            } else {
                bottomNavigationView.getMenu().getItem(0).setChecked(false);
            }
            menuItem = bottomNavigationView.getMenu().getItem(position);
            menuItem.setChecked(true);
            BottomNavigationViewHelper.addBadge(MainActivity.this, bottomNavigationView, 2, position, position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            menuItem = item;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_person:
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_person1:
                    viewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };
}
