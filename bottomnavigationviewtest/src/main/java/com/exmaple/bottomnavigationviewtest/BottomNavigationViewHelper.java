package com.exmaple.bottomnavigationviewtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

@SuppressLint("RestrictedApi")
public class BottomNavigationViewHelper {
    public static void disableShiftMode(BottomNavigationView bottomNavigationView) {

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        try {
            menuView.setLabelVisibilityMode(1);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShifting(false);
            }
        } catch (Exception e) {

        }
    }

    public static void addBadge(Context context, BottomNavigationView bottomNavigationView, int type, int position, int count) {
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        //这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(position);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
        if (type == 0) {
            //加载我们的角标View，新创建的一个布局
            View badge = LayoutInflater.from(context).inflate(R.layout.bottom_navigation_item_badge, menuView, false);
            //添加到Tab上
            itemView.addView(badge);
            TextView textView = badge.findViewById(R.id.tv_msg_count);
            textView.setText(String.valueOf(count));
            //无消息时可以将它隐藏即可
            textView.setVisibility(View.VISIBLE);
        } else {
            //加载我们的角标View，新创建的一个布局
            View badge = LayoutInflater.from(context).inflate(R.layout.bottom_navigation_dot_image_badge, menuView, false);
            //添加到Tab上
            itemView.addView(badge);
            TextView textView = badge.findViewById(R.id.tv_msg_count);
//            textView.setText(String.valueOf(count));
            //无消息时可以将它隐藏即可
            textView.setVisibility(View.VISIBLE);
        }
    }
}
