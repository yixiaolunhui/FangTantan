package com.dalong.tantan;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dalong.sidemenu.SlidingMenu;
import com.dalong.sidemenu.lib.SlidingFragmentActivity;
import com.dalong.tantan.fragment.LeftFragment;
import com.dalong.tantan.fragment.MainFragment;
import com.dalong.tantan.fragment.RightFragment;
import com.dalong.tantan.utils.StatusBarUtils;

/**
 * 高仿探探首页
 */
public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener {


    private MainFragment mMainFragment;
    private SlidingMenu mSlidingMenu;
    private Fragment mContent;
    private View mStatusBar;
    private ImageView mLeftIcon,mRightIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        StatusBarUtils.initStatusBar(this, R.color.transparent);
        initSlidingMenu();
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        mStatusBar = (View)findViewById(R.id.status_bar);
        mRightIcon = (ImageView)findViewById(R.id.icon_slidingmenu_right);
        mLeftIcon = (ImageView)findViewById(R.id.icon_slidingmenu_left);
        ViewGroup.LayoutParams linearParams = (ViewGroup.LayoutParams) mStatusBar.getLayoutParams();
        linearParams.height = StatusBarUtils.getStatusBarHeight(this);
        mStatusBar.setLayoutParams(linearParams);
        StatusBarUtils.setStatusBarViewVisibility(mStatusBar);
        mLeftIcon.setOnClickListener(this);
        mRightIcon.setOnClickListener(this);
    }


    /**
     * 初始化菜单配置
     */
    private void initSlidingMenu() {
        mMainFragment=new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mMainFragment).commit();
        setBehindContentView(R.layout.sliding_left_layout);

        FragmentTransaction leftFragementTransaction = getSupportFragmentManager().beginTransaction();
        Fragment leftFragment = new LeftFragment();
        leftFragementTransaction.replace(R.id.main_left_fragment, leftFragment);
        leftFragementTransaction.commit();

        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置是左滑还是右滑，还是左右都可以滑，我这里只做了左滑
        mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset_left);// 设置左边菜单宽度
        mSlidingMenu.setRightMenuOffsetRes(R.dimen.slidingmenu_offset_right);// 设置右边菜单宽度
        mSlidingMenu.setFadeDegree(0.35f);// 设置淡入淡出的比例
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置手势模式
//        mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置手势模式
        mSlidingMenu.setShadowDrawable(R.color.transparent);// 设置左菜单阴影图片
        mSlidingMenu.setFadeEnabled(true);// 设置滑动时菜单的是否淡入淡出
        mSlidingMenu.setBehindScrollScale(0.333f);// 设置滑动时拖拽效果
        mSlidingMenu.setSecondaryMenu(R.layout.sliding_right_layout);

        FragmentTransaction rightFragementTransaction = getSupportFragmentManager().beginTransaction();
        Fragment rightFragment = new RightFragment();
        rightFragementTransaction.replace(R.id.main_right_fragment, rightFragment);
        rightFragementTransaction.commit();


        mSlidingMenu.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
            }
        });

        mSlidingMenu.setAboveCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                setSlidingMenuIconAlpha(percentOpen,mLeftIcon );
            }
        });
    }


    /**
     *  左侧菜单点击切换首页的内容
     */
    public void switchContent(Fragment fragment) {
        mContent = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        getSlidingMenu().showContent();
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.icon_slidingmenu_right:
                mSlidingMenu.showSecondaryMenu(true);
                break;
            case R.id.icon_slidingmenu_left:
                Toast.makeText(MainActivity.this,"点击了左面图片",Toast.LENGTH_LONG).show();
                mSlidingMenu.toggle();
                break;
        }
    }


    /**
     * 侧边栏滑动时通知
     * @param percentOpen
     *
     */
    public void setSlidingMenuIconAlpha(float percentOpen,ImageView mIcon) {
        if(mIcon == null) {
            return;
        }
        float alphaPoint = 1 - percentOpen;
        if(alphaPoint < 0.0f) {
            alphaPoint = 0.0f;
        } else if(alphaPoint > 1.0f) {
            alphaPoint = 1.0f;
        }
        int alpha = (int) (alphaPoint * 255);
        mIcon.getBackground().setAlpha(alpha);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if(mSlidingMenu.isMenuShowing())
                    break;
            default:
                break;

        }
        return super.onKeyDown(keyCode, event);

    }

}
