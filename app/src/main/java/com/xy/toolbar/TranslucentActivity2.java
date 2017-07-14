package com.xy.toolbar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by jason on 2017/7/14.
 */

public class TranslucentActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_translucent2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        int statusHeight = getStatusBarHeight(this);//获取系统中状态栏的高度
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
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    private int getStatusBarHeight(Context context) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field statusBarHeightField = clazz.getField("status_bar_height");
            String height = statusBarHeightField.get(obj).toString();
            int statusHeight = context.getResources().getDimensionPixelSize(Integer.parseInt(height));
            return statusHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
