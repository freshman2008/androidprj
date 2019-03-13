package com.exmaple.recyclerviewtest3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyHomeAdapter mAdapter;
    private Context mContext;
    private List<String> picList;
    private List<Map<String, Object>> channelList;
    private List<MyItem> girlList;
    private List<String> normalLis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mContext = MainActivity.this;
        initData();
        initView();
        initListener();
    }


    private void initData() {
//        picList = new ArrayList<String>();
//        channelList = new ArrayList<Map<String, Object>>();
//        girlList = new ArrayList<Integer>();
//        normalLis = new ArrayList<String>();

        picList = MockDataUtils.getBannerData();
        channelList = MockDataUtils.getChannelData();
        girlList = MockDataUtils.getGirlData();
    }

    private void initListener() {

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.home_rv_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        if(mAdapter == null) {
            mAdapter = new MyHomeAdapter(mContext, picList, channelList, girlList, normalLis);
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
}
