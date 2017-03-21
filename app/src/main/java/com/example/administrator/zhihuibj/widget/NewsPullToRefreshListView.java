package com.example.administrator.zhihuibj.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.example.administrator.zhihuibj.R;
import com.example.administrator.zhihuibj.app.Constant;
import com.example.administrator.zhihuibj.bean.NewsListsBean;
import com.example.administrator.zhihuibj.netWork.NetworkListener;
import com.example.administrator.zhihuibj.netWork.NetworkManager;
import com.example.administrator.zhihuibj.netWork.SmartBJRequest;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.leon.loopviewpagerlib.FunBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/19.
 */

public class NewsPullToRefreshListView extends PullToRefreshListView {

   // private int[] imageResIds = {R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3, R.mipmap.icon_4, R.mipmap.icon_5};
    private String mUrl;
    private NewsListsBean mNewsListsBean;
    private static final String TAG = "NewsPullToRefreshListVi";
    private FunBanner mFunBanner;
    private String mMore;
    public NewsPullToRefreshListView(Context context) {
        this(context, null);
    }

    public NewsPullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

        setAdapter(mBaseAdapter);
        setMode(Mode.BOTH);
        int height = getResources().getDimensionPixelOffset(R.dimen.indicator_height);
        FunBanner.Builder builder = new FunBanner.Builder(getContext());
        mFunBanner = builder.setEnableAutoLoop(true)

                .setDotSelectedColor(Color.RED)
                .setHeightWidthRatio(0.5f)
                .setLoopInterval(3000)
                .setEnableAutoLoop(true)
                .setIndicatorBarHeight(height)
                .setIndicatorBackgroundColor(R.color.indicator_bg)
                .build();
            getRefreshableView().addHeaderView(mFunBanner);
             setOnRefreshListener(mOnRefreshListener2);
    }

    private OnRefreshListener2 mOnRefreshListener2 = new OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {
            //重新发送网络请求获取最新的数据
            SmartBJRequest<NewsListsBean> request = new SmartBJRequest<NewsListsBean>(                NewsListsBean.class,
                    mUrl,
                    mRefreshListener);

            NetworkManager.sendRequest(request);

        }
        //上拉加载更多
        @Override
        public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
            if(mMore.length()>0){
                //可以下拉更多：
                String url = Constant.HOST + mMore;
                SmartBJRequest<NewsListsBean> request = new SmartBJRequest<NewsListsBean>(
                        NewsListsBean.class,
                        url,
                        mLoadMoreListener
                );
                NetworkManager.sendRequest(request);
            }else {
                Toast.makeText(getContext(),"不能加载更多图片",Toast.LENGTH_SHORT).show();
                //延迟刷新
                post(new Runnable() {
                    @Override
                    public void run() {
                        onRefreshComplete();//重置上拉刷新头
                    }
                });

            }
        }
    };

    private  NetworkListener<NewsListsBean> mLoadMoreListener = new NetworkListener<NewsListsBean>(){
        @Override
        public void onResponse(NewsListsBean response) {
            Toast.makeText(getContext(), "加载更多数据成功", Toast.LENGTH_SHORT).show();
            //保存mMore;
            mMore = response.getData().getMore();
            //加载的更多的数据加到原来的新闻的集合中
            mNewsListsBean.getData().getNews().addAll(response.getData().getNews());
            mBaseAdapter.notifyDataSetChanged();

            onRefreshComplete();


        }
    };



    private NetworkListener<NewsListsBean> mRefreshListener = new NetworkListener<NewsListsBean>(){
        @Override
        public void onResponse(NewsListsBean response) {
            //加载网络数据
            updateNewsList(response);
            //刷新图片复位
            onRefreshComplete();//下拉刷新结束
        }
    };


    private  void  updateNewsList(NewsListsBean response){
        mNewsListsBean = response;
        //刷新
        mBaseAdapter.notifyDataSetChanged();

        //刷新轮播图

        List<String> imageList = new ArrayList<String>();
        List<String> titleList = new ArrayList<String>();
        List<NewsListsBean.DataBean.TopnewsBean> topnewsBean  = mNewsListsBean.getData().getTopnews();
        for(int i=0;i<topnewsBean.size();i++){
            titleList.add(topnewsBean.get(i).getTitle());
            imageList.add(topnewsBean.get(i).getTopimage().replace("10.0.2.2","10.0.3.2"));

        }
        mFunBanner.setImageUrlsAndTitles(imageList,titleList);
        mMore=mNewsListsBean.getData().getMore();

    }

    private BaseAdapter mBaseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {

            if(mNewsListsBean!=null){

                return mNewsListsBean.getData().getNews().size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override     //view_news_list_item
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if(convertView==null){

                convertView=View.inflate(getContext(), R.layout.view_news_list_item,null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            NewsListsBean.DataBean.NewsBean newsBean  =  mNewsListsBean.getData().getNews().get(position);
           //标题
            viewHolder.mTitle.setText(newsBean.getTitle());
            viewHolder.mTitle.setTextColor(Color.BLACK);
            //设置时间
            //新闻列表里的图片和标题的显示
            viewHolder.mPublishTime.setText(newsBean.getPubdate());
            String url = newsBean.getListimage().replace("10.0.2.2","10.0.3.2");
            viewHolder.mNetworkImageView.setImageUrl(url,NetworkManager.getImageLoder());
            return convertView;
        }
    };

    public class ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.publishTime)
        TextView mPublishTime;
        @BindView(R.id.news_images)
        NetworkImageView mNetworkImageView;

        public ViewHolder(View root){
            ButterKnife.bind(this,root);
        }

    }

    public void setUrl(String url) {
        mUrl = url;
        //创建请求
        SmartBJRequest<NewsListsBean> request = new SmartBJRequest<>(
                NewsListsBean.class,
                url,
                mNewsListsBeanListener
                );
        //发送网络请求：
        NetworkManager.sendRequest(request);
        //Volley.newRequestQueue(getContext()).add(request);


    }

    private  NetworkListener<NewsListsBean> mNewsListsBeanListener = new NetworkListener<NewsListsBean>(){
        @Override
        public void onResponse(NewsListsBean response) {

            updateNewsList(response);
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            super.onErrorResponse(error);
        }
    };


/*    private Response.Listener<NewsListsBean> mNewsListsBeanListener = new Response.Listener<NewsListsBean>() {
        @Override
        public void onResponse(NewsListsBean response) {
            //Log.d(TAG, "NewsPullToRefreshListVi: "+response.getData().getNews().get(0).getTitle());
           mNewsListsBean =  response;
            //刷新
            mBaseAdapter.notifyDataSetChanged();

        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };*/
}