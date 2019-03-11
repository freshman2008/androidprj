package com.exmaple.recyclerviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GirdViewActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGirdLayoutManager;
    private MyGirdViewAdapter mAdapter;
    private List<MyItem> mDataList;
    private Context mContext;
    private Button addBtn;
    private Button delBtn;
    private int ddd = 100;
    private Handler mHandler;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        this.mContext = GirdViewActivity.this;
        initData();
        initView();
        initListener();
    }

    private void initView() {
        addBtn = findViewById(R.id.add_item);
        delBtn = findViewById(R.id.del_item);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        //2.设置布局方式
        // use a linear layout manager
        mGirdLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mGirdLayoutManager);

//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        if(mAdapter == null) {
            mAdapter = new MyGirdViewAdapter(mContext, mDataList);
        }
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "Activity Click on item " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(mContext, "Activity Long Click on item " + position, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(null);//设置动画为null来解决闪烁问题
    }

    private void initData() {
        if (mDataList == null) {
            mDataList = new ArrayList<MyItem>();
            for (int i=0; i< 50; i++) {
                MyItem item = new MyItem();
                Bitmap bmp = null;
                switch (i) {
                    case 0:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book1);
                        break;
                    case 1:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book2);
                        break;
                    case 2:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book3);
                        break;
                    case 3:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book4);
                        break;
                    case 4:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book5);
                        break;
                    case 5:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book6);
                        break;
                    case 6:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book7);
                        break;
                    case 7:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book8);
                        break;
                    case 8:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book9);
                        break;
                    case 9:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book10);
                        break;
                    case 10:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book11);
                        break;
                    case 11:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book12);
                        break;
                    case 12:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book13);
                        break;
                    case 13:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book14);
                        break;
                    case 14:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book15);
                        break;
                    default:
                        bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.book16);
                        break;
                }
                item.setTitle("内容 - "+i);
                item.setIcon(bmp);
                mDataList.add(item);
            }
        }
    }

    private void initListener() {
        addBtn.setOnClickListener(this);
        delBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
