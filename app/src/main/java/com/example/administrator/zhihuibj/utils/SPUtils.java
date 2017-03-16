package com.example.administrator.zhihuibj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/3/16.
 */

public class SPUtils {
    private static final String FILE_NAME = "smartbj35";


    public static void  saveBoolean(Context context, String key, boolean value){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        mSharedPreferences.edit().putBoolean(key,value).apply();
    }
    public static boolean  getBoolean(Context context,String key){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(key,false);
    }
}
