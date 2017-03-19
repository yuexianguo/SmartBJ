package com.example.administrator.zhihuibj.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.zhihuibj.R;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/19.
 */

public class NewsPage extends RelativeLayout {
    @BindView(R.id.indicator)
    TabPageIndicator mIndicator;
    @BindView(R.id.pager)
    ViewPager mPager;
    private static final String[] CONTENT = new String[]{"Recent", "Artists", "Albums", "Songs", "Playlists", "Genres"};


    public NewsPage(Context context) {
        this(context, null);
    }

    public NewsPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_news_page, this);
        ButterKnife.bind(this,this);
        mPager.setAdapter(mPagerAdapter);
        mIndicator.setViewPager(mPager);
    }
    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return CONTENT.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            /*TextView textView = new TextView(getContext());
            textView.setText(CONTENT[position]);
            container.addView(textView);
            return textView;*/

            //下拉加载的图标放里面
            NewsPullToRefreshListView newsPullToRefreshListView = new NewsPullToRefreshListView(getContext());
            container.addView(newsPullToRefreshListView);
            return newsPullToRefreshListView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return CONTENT[position];
        }
    } ;

}
