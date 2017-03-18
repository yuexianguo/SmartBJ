package com.example.administrator.zhihuibj.ui.fragment;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.zhihuibj.R;
import com.example.administrator.zhihuibj.widget.TabPage;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/3/17.
 */

public class HomeFragment extends BaseFragment {

    private HomeFragment.OnHomeChangeListener  mOnHomeChangeListener;
    private SparseArray<TabPage> mTabPageSparseArray = new SparseArray<TabPage>();
    private static final String TAG = "HomeFragment";
    @BindView(R.id.tab_page_container)
    FrameLayout mTabPageContainer;
    @BindView(R.id.tab_container)
    RadioGroup mTabRadiogroup;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }
    protected void init() {
        mTabRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //查找内存缓存中有没有缓存tabpage, 如果有则使用缓存的tabpage，没有就创建一个
                TabPage tabPage = null;
               if(mTabPageSparseArray.get(checkedId)!=null){
                   tabPage=mTabPageSparseArray.get(checkedId);
                   Log.d(TAG, "onCheckedChanged111: "+tabPage);
                   Toast.makeText(getContext(),"从缓存中获取tabpage",Toast.LENGTH_SHORT);
               }else{
                   tabPage = getTabPage(checkedId);
               }
                //确定有人在监听，不为null时候，然后把当前的状态通知给他
                if(mOnHomeChangeListener!=null){
                    mOnHomeChangeListener.OnTabSwitch(checkedId);
                }
                Log.d(TAG, "onCheckedChanged333: "+tabPage);
                //优化，把之前的tabpage移除掉
                mTabPageContainer.removeAllViews();
                mTabPageContainer.addView(tabPage);
            }
        });
        //默认check到首页
        mTabRadiogroup.check(R.id.tab_home);
    }

    @NonNull
    private TabPage getTabPage(int checkedId) {
        TabPage tabPage;
        tabPage= new TabPage(getContext());
        Log.d(TAG, "onCheckedChanged:222 "+tabPage);
        switch (checkedId){
            case R.id.tab_home:
                tabPage.hide();
                tabPage.setTitle("首页");
                break;
            case R.id.tab_news_center:
                tabPage.setTitle("新闻中心");
                break;
            case R.id.tab_smart_service:
                tabPage.setTitle("智慧服务");
                break;
            case R.id.tab_gov_affairs:
                tabPage.setTitle("政务");
                break;
            case R.id.tab_settings:
                tabPage.hide();
                tabPage.setTitle("设置中心");
                break;
        }
        mTabPageSparseArray.put(checkedId,tabPage);
        //Toast.makeText(getContext(),"创建一个新的tabpage",Toast.LENGTH_SHORT);
        return tabPage;
    }

    /**
     * 通知外界发生了tab按钮的切换
     */
    public interface OnHomeChangeListener{
        void OnTabSwitch(int checkId);
    }
    public void setOnHomeChangeListener(HomeFragment.OnHomeChangeListener listener){
        mOnHomeChangeListener=listener;
    }
}
