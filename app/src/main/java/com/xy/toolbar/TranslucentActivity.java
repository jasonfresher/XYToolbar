package com.xy.toolbar;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by jason on 2017/7/12.
 */

public class TranslucentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * android4.4需要设置状态栏透明
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_translucent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        View bottom_navigation_bar = findViewById(R.id.bottom_navigation_bar);
        setTranslucentToolbar(toolbar,bottom_navigation_bar,getResources().getColor(R.color.colorPrimary));
    }

    /**
     * 修改toolbar的高度为自身高度+系统状态栏的高度
     * 修改navigatoinbar的高度为状态栏的高度
     * @param toolbar
     * @param bottomNavigationBar
     * @param translucentPrimaryColor
     */
    public void setTranslucentToolbar(Toolbar toolbar, View bottomNavigationBar , int translucentPrimaryColor){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            /**
             * 兼容android4.4系统
             */
            int statusHeight = getSystemComponentDimen(this, "status_bar_height");//获取系统中状态栏的高度
            //设置toolbar高度
            ViewGroup.LayoutParams toolbarLayoutParams = toolbar.getLayoutParams();
            toolbarLayoutParams.height += statusHeight;
            toolbar.setLayoutParams(toolbarLayoutParams);
            //设置toolbar的padding，防止内容被状态栏遮挡
            toolbar.setPadding(toolbar.getPaddingLeft(),
                    toolbar.getPaddingTop() + statusHeight,
                    toolbar.getPaddingLeft(),
                    toolbar.getPaddingBottom());
            //设置toolbar背景色
            toolbar.setBackgroundColor(translucentPrimaryColor);

            int navigationHeight = getSystemComponentDimen(this, "navigation_bar_height");//获取系统中底部虚拟导航栏的高度
            //设置bottomNavigationBar高度
            ViewGroup.LayoutParams bottomNavigationBarLayoutParams = bottomNavigationBar.getLayoutParams();
            bottomNavigationBarLayoutParams.height += navigationHeight;
            bottomNavigationBar.setLayoutParams(bottomNavigationBarLayoutParams);
            //设置bottomNavigationBar背景色
            bottomNavigationBar.setBackgroundColor(translucentPrimaryColor);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            /**
             * android5.0以上可以直接使用api设置状态栏
             */
            getWindow().setStatusBarColor(translucentPrimaryColor);
            getWindow().setNavigationBarColor(translucentPrimaryColor);
        }

    }


    /**
     * 反射获取系统状态栏的高度
     * @param context
     * @param dimenName
     * @return
     */
    public int getSystemComponentDimen(Context context, String dimenName){
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field statusBarHeightField = clazz.getField(dimenName);
            String height = statusBarHeightField.get(obj).toString();
            int statusHeight = context.getResources().getDimensionPixelSize(Integer.parseInt(height));
            return statusHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
