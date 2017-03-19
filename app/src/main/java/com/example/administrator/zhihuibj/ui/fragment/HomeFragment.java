package com.example.administrator.zhihuibj.ui.fragment;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.zhihuibj.R;
import com.example.administrator.zhihuibj.widget.GovAffairsTabPage;
import com.example.administrator.zhihuibj.widget.HomeTabPage;
import com.example.administrator.zhihuibj.widget.NewsCenterTabPage;
import com.example.administrator.zhihuibj.widget.SettingCenterTabPage;
import com.example.administrator.zhihuibj.widget.SmartServiceTabPage;
import com.example.administrator.zhihuibj.widget.TabPage;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/3/17.
 */

public class HomeFragment extends BaseFragment {

    private HomeFragment.OnHomeChangeListener  mOnHomeChangeListener;
    private SparseArray<TabPage> mTabPageSparseArray = new SparseArray<TabPage>();
    private static final String TAG = "HomeFragment";
    private TabPage mCurrentTabPage;
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
                    mOnHomeChangeListener.onTabSwitch(checkedId);
                }
                Log.d(TAG, "onCheckedChanged333: "+tabPage);

                mCurrentTabPage=tabPage;
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

        TabPage tabPage=null;
        Log.d(TAG, "onCheckedChanged:222 "+tabPage);
        switch (checkedId){
            case R.id.tab_home:
                tabPage = new HomeTabPage(getContext());
                tabPage.hide();
                tabPage.setTitle("首页");
                break;
            case R.id.tab_news_center:
                tabPage = new NewsCenterTabPage(getContext());
                tabPage.setTitle("新闻中心");
                break;
            case R.id.tab_smart_service:
                tabPage = new SmartServiceTabPage(getContext());
                tabPage.setTitle("智慧服务");
                break;
            case R.id.tab_gov_affairs:
                tabPage = new GovAffairsTabPage(getContext());
                tabPage.setTitle("政务");
                break;
            case R.id.tab_settings:
                tabPage = new SettingCenterTabPage(getContext());
                tabPage.hide();
                tabPage.setTitle("设置中心");
                break;
        }
        mTabPageSparseArray.put(checkedId,tabPage);
        //监听从tabPage传过来的侧滑菜单按钮的点击通知，然后接口回调通知MainActivity
        tabPage.setOnTabPageChangeListener(new TabPage.OnTabPageChangeListener() {
            
            public void onTabPageMenuClick() {
                Log.d(TAG, "onTabPageMenuClick: 监听成功");
               Toast.makeText(getContext(),"事件从tabpage传递到homefragment",Toast.LENGTH_SHORT).show();
                if(mOnHomeChangeListener!=null){
                    mOnHomeChangeListener.onTabPageMenuClick();
                }
            }
        });
        //Toast.makeText(getContext(),"创建一个新的tabpage",Toast.LENGTH_SHORT);
        return tabPage;
    }

    public void onMenuSwitch(int position) {
        Toast.makeText(getContext(),"homefragment获得了左侧菜单按钮选项的切换"+position,Toast.LENGTH_SHORT).show();
        mCurrentTabPage.onMenuSwitch(position);
    }

    /**
     * 通知外界发生了tab按钮的切换
     */
    public interface OnHomeChangeListener{
        void onTabSwitch(int checkId);
        void onTabPageMenuClick();
    }
    public void setOnHomeChangeListener(HomeFragment.OnHomeChangeListener listener){
        mOnHomeChangeListener=listener;
    }


}
