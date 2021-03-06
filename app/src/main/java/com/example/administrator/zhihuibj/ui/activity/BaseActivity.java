package com.example.administrator.zhihuibj.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/16.
 */
//基类的创建
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        init();

    }


    public  void navigateTo(Class activity) {
        Intent intent = new Intent(this,activity);
        startActivity(intent);
        finish();

    }

    public void init() {

    }
    public abstract int getLayoutResId();



}
