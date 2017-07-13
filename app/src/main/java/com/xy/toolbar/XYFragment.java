package com.xy.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jason on 2017/7/11.
 */

public class XYFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        TextView textView = new TextView(getContext());
        textView.setText(title);
        textView.setBackgroundColor(Color.rgb((int)(1 - Math.random() * 255),(int)(1 - Math.random() * 255),(int)(1 - Math.random() * 255)));
        return textView;
    }
}
