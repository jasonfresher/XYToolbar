package com.xy.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by jason on 2017/7/10.
 */

public class XYScrollView extends ScrollView {

    private AlphaChangeListener mAlphaChangeListener;

    public XYScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAlphaChangeListener(AlphaChangeListener alphaChangeListener){
        mAlphaChangeListener = alphaChangeListener;
    }

    public interface AlphaChangeListener{
        void onAlphaChange(float alpha);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mAlphaChangeListener != null){
            int scrollY = getScrollY();
            int screen_height = getContext().getResources().getDisplayMetrics().heightPixels;
            if(scrollY<=screen_height/3f){//0~1f,而透明度应该是1~0f
                mAlphaChangeListener.onAlphaChange(1-scrollY/(screen_height/3f));//alpha=滑出去的高度/(screen_height/3f)
            }
        }
    }
}
