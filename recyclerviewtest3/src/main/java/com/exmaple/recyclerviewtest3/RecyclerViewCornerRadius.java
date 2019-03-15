package com.exmaple.recyclerviewtest3;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * 圆角 RecyclerView
 *
 * Created by afon on 2017/1/5.
 */

public class RecyclerViewCornerRadius extends RecyclerView.ItemDecoration {
    public static final String TAG = "RecyclerViewCornerRadius";

    private RectF rectF;
    private Path path;

    private int topLeftRadius = 0;
    private int topRightRadius = 0;
    private int bottomLeftRadius = 0;
    private int bottomRightRadius = 0;

    public RecyclerViewCornerRadius(final RecyclerView recyclerView) {
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rectF = new RectF(0, 0, recyclerView.getMeasuredWidth(), recyclerView.getMeasuredHeight());

                path = new Path();
                path.reset();
                path.addRoundRect(rectF, new float[]{
                        topLeftRadius, topLeftRadius,
                        topRightRadius, topRightRadius,
                        bottomLeftRadius, bottomLeftRadius,
                        bottomRightRadius, bottomRightRadius
                }, Path.Direction.CCW);

                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void setCornerRadius(int radius) {
        this.topLeftRadius = radius;
        this.topRightRadius = radius;
        this.bottomLeftRadius = radius;
        this.bottomRightRadius = radius;
    }

    public void setCornerRadius(int topLeftRadius, int topRightRadius, int bottomLeftRadius, int bottomRightRadius) {
        this.topLeftRadius = topLeftRadius;
        this.topRightRadius = topRightRadius;
        this.bottomLeftRadius = bottomLeftRadius;
        this.bottomRightRadius = bottomRightRadius;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        c.clipRect(rectF);
        c.clipPath(path, Region.Op.REPLACE);
    }


    /*设置item之间的间隔*/
    private int itemSpace;
    private int itemNum;

    /**
     *
     * @param itemSpace item间隔
     * @param itemNum 每行item的个数
     */
    public void setItemOffsets(int itemSpace, int itemNum) {
        this.itemSpace = itemSpace;
        this.itemNum = itemNum;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildLayoutPosition(view);
        if (position % itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
            outRect.left = 0;
        } else {
            outRect.left = itemSpace;
        }

        int count = parent.getAdapter().getItemCount();
        if (position < (itemNum * ((count / itemNum) - 1))) {
            outRect.bottom = itemSpace;
        }
    }
}
