package com.exmaple.recyclerviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_gird_view;
    private Button btn_list_view;
    private Button btn_staggered_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView() {
        btn_list_view = (Button) findViewById(R.id.btn_list_view);
        btn_gird_view = (Button) findViewById(R.id.btn_gird_view);
        btn_staggered_view = (Button) findViewById(R.id.btn_staggered_view);
    }

    private void initListener() {
        btn_list_view.setOnClickListener(this);
        btn_gird_view.setOnClickListener(this);
        btn_staggered_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list_view:
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_gird_view:
                break;
            case R.id.btn_staggered_view:
                break;
        }
    }
}
