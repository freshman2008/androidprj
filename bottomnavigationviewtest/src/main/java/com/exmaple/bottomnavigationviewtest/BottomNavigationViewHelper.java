package com.exmaple.bottomnavigationviewtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
            e.printStackTrace();
        }
    }

    public static void showBadge(Context context, BottomNavigationView bottomNavigationView, int type, int itemIndex, int count) {
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        if (itemIndex < menuView.getChildCount()) {
            //这里就是获取所添加的每一个Tab(或者叫menu)，
            View tab = menuView.getChildAt(itemIndex);
            BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
            // 从子tab中获得其中显示图片的ImageView
            View iconView = itemView.findViewById(android.support.design.R.id.icon);
            // 获得图标的宽度
            int iconWidth = iconView.getWidth();
            // 获得tab的宽度/2
            int tabWidth = itemView.getWidth();
            // 计算badge要距离右边的距离
            int spaceWidth = (tabWidth - iconWidth) / 2;
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            lp.topMargin = 16;
            lp.rightMargin = spaceWidth - 16;
            lp.gravity = Gravity.RIGHT;

            if (type == 0) {
                //加载我们的角标View，新创建的一个布局
                View badge = LayoutInflater.from(context).inflate(R.layout.bottom_navigation_text_badge, menuView, false);
                itemView.removeView(badge);
                itemView.addView(badge, lp);
                TextView badgeView = badge.findViewById(R.id.badge_item_view);
                badgeView.setText(String.valueOf(count));
                //无消息时可以将它隐藏即可
                badgeView.setVisibility(View.VISIBLE);
            } else if (type == 1) {
                //加载我们的角标View，新创建的一个布局
                View badge = LayoutInflater.from(context).inflate(R.layout.bottom_navigation_dot_image_badge, menuView, false);
                //添加到Tab上
                itemView.removeView(badge);
                itemView.addView(badge, lp);
                TextView badgeView = badge.findViewById(R.id.badge_item_view);
                //无消息时可以将它隐藏即可
                badgeView.setVisibility(View.VISIBLE);
            } else {
                //加载我们的角标View，新创建的一个布局
                View badge = LayoutInflater.from(context).inflate(R.layout.bottom_navigation_image_badge, menuView, false);
                itemView.removeView(badge);
                lp.rightMargin = 16;

                itemView.addView(badge, lp);
                ImageView badgeView = badge.findViewById(R.id.badge_item_view);
                //无消息时可以将它隐藏即可
                badgeView.setVisibility(View.VISIBLE);
            }
        }
    }
}
