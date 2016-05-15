package com.dalong.tantan.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 自定义状态栏工具类
 */
public class StatusBarUtils {

    //初始化状态栏
    public static void initStatusBar(Activity activity, int color){
        if (activity ==null||color<=0)
            return;
        //取消状态栏修改颜色
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);//显示状态栏
			setTranslucentStatus(activity,true);
		}
    }

    @TargetApi(19)
    public static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    //状态栏显示隐藏设置
    public static void setStatusBarViewVisibility(View view) {
        if (view == null) {
            return;
        }
        //注释掉状态栏view
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			view.setVisibility(View.VISIBLE);
		} else{
			view.setVisibility(View.GONE);
		}
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context mContext) {
        int result = 25;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
