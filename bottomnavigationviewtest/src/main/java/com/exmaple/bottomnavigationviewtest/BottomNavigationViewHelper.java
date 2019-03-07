package com.exmaple.bottomnavigationviewtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import q.rorbin.badgeview.QBadgeView;

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
            e.printStackTrace();
        }
    }

    public static void showBadge(Context context, BottomNavigationView bottomNavigationView, int type, int itemIndex, int count) {
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        if (itemIndex < menuView.getChildCount()) {
            //这里就是获取所添加的每一个Tab(或者叫menu)，
            View tab = menuView.getChildAt(itemIndex);
            if (type == 0) {
                new QBadgeView(context).bindTarget(tab).setBadgeNumber(count);
            } else if (type == 1) {
                new QBadgeView(context).bindTarget(tab).setBadgeTextSize(0, true).setBadgePadding(10, true).setBadgeNumber(1);
            } else {
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.bottom_navigation_item_dot_image_badge);
                new QBadgeView(context).bindTarget(tab).setBadgeBackground(drawable, true);
            }
        }
    }
}
