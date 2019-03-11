package com.exmaple.recyclerviewtest;

import android.graphics.Bitmap;

public class MyItem {
    Bitmap icon;
    String title;
    String datetime;
    Bitmap bookmark;

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Bitmap getBookmark() {
        return bookmark;
    }

    public void setBookmark(Bitmap bookmark) {
        this.bookmark = bookmark;
    }
}
