package com.exmaple.recyclerviewtest;

import android.content.Context;
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

public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGirdLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGirdLayoutManager;
    private MyAdapter mAdapter;
    private List<String> mDataList;
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

        this.mContext = ListViewActivity.this;
        initData();
        initView();
        initListener();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    mDataList.set(pos, "pos:" + pos + ", ddd:" + ddd );
                    mAdapter.notifyItemChanged(pos);
                    ddd++;
                    mHandler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        };
    }

    private void initView() {
        addBtn = findViewById(R.id.add_item);
        delBtn = findViewById(R.id.del_item);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        //2.设置布局方式
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // use a linear layout manager
        mGirdLayoutManager = new GridLayoutManager(this, 3);
        mStaggeredGirdLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager/*mStaggeredGirdLayoutManager*//*mGirdLayoutManager*//*mLinearLayoutManager*/);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        if(mAdapter == null) {
            mAdapter = new MyAdapter(mContext, mDataList);
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
            mDataList = new ArrayList<String>();
            for (int i=0; i<50; i++){
                mDataList.add("内容 - "+i);
            }
        }
    }

    private void initListener() {
        addBtn.setOnClickListener(this);
        delBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_item:
                mDataList.add("内容 - " + (mDataList.size()+1));
//                mAdapter.notifyDataSetChanged();
                mAdapter.notifyItemInserted(mDataList.size()-1);
                mRecyclerView.scrollToPosition(mDataList.size()-1);
                break;
            case R.id.del_item:
                pos = mDataList.size() - 3;
                mHandler.sendEmptyMessageDelayed(1, 1000);
                break;
        }
    }
}
