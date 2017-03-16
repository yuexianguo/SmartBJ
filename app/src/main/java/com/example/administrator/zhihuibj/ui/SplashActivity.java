package com.example.administrator.zhihuibj.ui;

import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.administrator.zhihuibj.BaseActivity;
import com.example.administrator.zhihuibj.MainActivity;
import com.example.administrator.zhihuibj.R;
import com.example.administrator.zhihuibj.utils.SPUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/16.
 */

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";

   private static final int ANIMATION_DURATION=3000;
    @BindView(R.id.splash_image)
    ImageView mSplashImage;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        super.init();
        startAni();
    }

    /*执行动画*/
    private void startAni() {
        Log.d(TAG, "startAnimation: 执行动画");

        AnimationSet animationSet = new AnimationSet(false);

        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0,720,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(ANIMATION_DURATION);
        animationSet.addAnimation(rotateAnimation);

        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(ANIMATION_DURATION);
        animationSet.addAnimation(scaleAnimation);

        //透明度动画 从透明到不透明
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(ANIMATION_DURATION);

        animationSet.addAnimation(alphaAnimation);

        animationSet.setAnimationListener(mAnimationListener);

        mSplashImage.startAnimation(animationSet);


    }
    private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }
       /* 监听动画，判断跳转到主界面还是导航界面*/
        @Override
        public void onAnimationEnd(Animation animation) {

            if(SPUtils.getBoolean(SplashActivity.this,"start")){
                navigateTo(MainActivity.class);
            }else{
                navigateTo(TutorialActivity.class);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
