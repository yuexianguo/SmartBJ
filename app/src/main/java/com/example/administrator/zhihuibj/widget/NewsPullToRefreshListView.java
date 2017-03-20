package com.example.administrator.zhihuibj.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.zhihuibj.R;
import com.example.administrator.zhihuibj.bean.NewsListsBean;
import com.example.administrator.zhihuibj.netWork.SmartBJRequest;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.leon.loopviewpagerlib.FunBanner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/19.
 */

public class NewsPullToRefreshListView extends PullToRefreshListView {

    private int[] imageResIds = {R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3, R.mipmap.icon_4, R.mipmap.icon_5};
    private String mUrl;
    private NewsListsBean mNewsListsBean;
    private static final String TAG = "NewsPullToRefreshListVi";
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

        FunBanner.Builder builder = new FunBanner.Builder(getContext());
        FunBanner funBanner = builder.setEnableAutoLoop(true)
                .setImageResIds(imageResIds)
                .setDotSelectedColor(Color.RED)
                .setHeightWidthRatio(0.5556f)
                .setLoopInterval(3000)
                .setEnableAutoLoop(true)
                .setIndicatorBackgroundColor(R.color.indicator_bg)
                .build();
            getRefreshableView().addHeaderView(funBanner);
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
            viewHolder.mPublishTime.setText(newsBean.getPubdate());

            return convertView;
        }
    };

    public class ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.publishTime)
        TextView mPublishTime;

        public ViewHolder(View root){
            ButterKnife.bind(this,root);
        }

    }

    public void setUrl(String url) {
        mUrl = url;
        //创建请求
        SmartBJRequest<NewsListsBean> request = new SmartBJRequest<>(Request.Method.GET,
                NewsListsBean.class,
                url,
                null,
                mNewsListsBeanListener,
               mErrorListener
                );
        //发送网络请求：
        Volley.newRequestQueue(getContext()).add(request);


    }

    private Response.Listener<NewsListsBean> mNewsListsBeanListener = new Response.Listener<NewsListsBean>() {
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
    };
}