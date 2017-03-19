package com.example.administrator.zhihuibj.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.zhihuibj.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MenuFragment extends BaseFragment {

    private String[] mMenuTitles = {"新闻","组图","专题","互动"};
    @BindView(R.id.list_view)
    ListView mListView;
    private int mLastSelectedPosition=0;
    private OnMenuChangeListener mOnMenuChangeListener;


    @Override
    public int getLayoutResId() {

        return R.layout.fragment_menu;
    }

    @Override
    protected void init() {
        super.init();
        mListView.setAdapter(mBaseAdapter);
        mListView.setOnItemClickListener(mOnItemClickListener);
    }
    private AdapterView.OnItemClickListener mOnItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          boolean isSwitch =  position!=mLastSelectedPosition;
            if(mOnMenuChangeListener!=null){
                mOnMenuChangeListener.onMenuItemSwitch(position,isSwitch);
            }
            if(mLastSelectedPosition==position){
                return;
            }
           View clickChild= parent.getChildAt(position);
            clickChild.setEnabled(true);
            View preChild = parent.getChildAt(mLastSelectedPosition);
            preChild.setEnabled(false);
            mLastSelectedPosition=position;
        }
    };

   private BaseAdapter mBaseAdapter = new BaseAdapter() {
       @Override
       public int getCount() {
           return mMenuTitles.length;
       }

       @Override
       public Object getItem(int position) {
           return null;
       }

       @Override
       public long getItemId(int position) {
           return 0;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           if(convertView==null){
               convertView=View.inflate(getContext(),R.layout.view_menu_item,null);

           }
           ((TextView) convertView).setText(mMenuTitles[position]);
           if(position==0){
               convertView.setEnabled(true);
           }else {
               convertView.setEnabled(false);
           }
           return convertView;
       }
   };
    //position:指明切换到哪个位置
    //isSwitch判断是否要切换，点击的是同一个按钮就不用通知tabpage切换。
    public interface OnMenuChangeListener{
        void onMenuItemSwitch(int position,boolean isSwitch);
    }
    public void setOnMenuChangeListener(OnMenuChangeListener listener ) {
        mOnMenuChangeListener=listener;
    }
}
