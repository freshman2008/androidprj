package com.exmaple.recyclerviewtest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    //总数据源
    private List<Object> mObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        if (mObjects == null) {
            mObjects = new ArrayList<>();
        }

        //设置轮播图数据
        mObjects.add(DataTestUtils.getBannerData());
        //设置品牌
        mObjects.add(DataTestUtils.getBrandData());
        //设置榜单
        mObjects.add(DataTestUtils.getFocusData());
        //设置榜单
        mObjects.add(DataTestUtils.getSelectData());
        //设置底部数据
        DataTestUtils.getBottomData(mObjects);

        HomeAdapter adapter = new HomeAdapter(this, mObjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
