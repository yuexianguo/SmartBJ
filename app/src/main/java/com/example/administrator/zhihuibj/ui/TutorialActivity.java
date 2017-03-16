package com.example.administrator.zhihuibj.ui;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.zhihuibj.BaseActivity;
import com.example.administrator.zhihuibj.MainActivity;
import com.example.administrator.zhihuibj.R;
import com.example.administrator.zhihuibj.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/16.
 */

public class TutorialActivity extends BaseActivity {

    @BindView(R.id.view_page)
    ViewPager mViewPage;
    @BindView(R.id.start)
    Button mStart;
    private int[] mImages={R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};

    @Override
    public int getLayoutResId() {
        return R.layout.activity_tutorial;
    }

    @Override
    public void init() {
        super.init();
        mViewPage.addOnPageChangeListener(mOnPageChangeListener);
        mViewPage.setAdapter(mPagerAdapter);
    }
    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(position==mImages.length-1){
                mStart.setVisibility(View.VISIBLE);
            }else {
                mStart.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /*viewpager的加载图片,ViewPager页面切换*/
        private PagerAdapter mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mImages.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(TutorialActivity.this);
                imageView.setImageResource(mImages[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        };


    @OnClick(R.id.start)
    public void onClick() {
      //打开activity耗性能，所以封装到方法中
        navigateTo(MainActivity.class);
        SPUtils.saveBoolean(this,"start",true);
    }
}
