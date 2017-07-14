package com.xy.toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView searchClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        XYScrollView scrollView = (XYScrollView) findViewById(R.id.scrollview);
        scrollView.setAlphaChangeListener(new XYScrollView.AlphaChangeListener() {
            @Override
            public void onAlphaChange(float alpha) {
                toolbar.setAlpha(alpha);
            }
        });
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(10);

    }


    public void toTabLayout(View view){
        Intent intent = new Intent(this,TabLayoutActivity.class);
        startActivity(intent);
    }

    public void toTranslucent(View view){
        Intent intent = new Intent(this,TranslucentActivity2.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        /**
         * 设置搜索图标
         */
        ImageView searchButton = (ImageView) searchView.findViewById(R.id.search_button);
        searchButton.setImageResource(R.mipmap.ic_search);

        /**
         * 设置搜索获取结果图标
         */
        ImageView searchGo = (ImageView) searchView.findViewById(R.id.search_go_btn);
//        searchGo.setImageResource(R.mipmap.ic_notifications);
//        searchGo.setVisibility(View.VISIBLE);
//        searchView.setSubmitButtonEnabled(true);

        /**
         * 设置关闭图标
         */
        searchClose = (ImageView) searchView.findViewById(R.id.search_close_btn);
//        searchClose.setImageResource(R.mipmap.ic_notifications);
        /**
         * 设置输入框提示文本
         */
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setHint("XY SearchView");
        searchAutoComplete.setHintTextColor(Color.WHITE);
        searchAutoComplete.setTextColor(getResources().getColor(R.color.colorText));


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("jason","@@@===OnSearchClickListener====>");
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.d("jason","@@@===OnCloseListener====>");
                return false;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)searchClose.setAlpha(0.5f);
                Log.d("jason","@@@===OnQueryTextFocusChangeListener====>" + hasFocus);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("jason","@@@===onQueryTextSubmit====>" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("jason","@@@===onQueryTextChange====>" + newText);
                final boolean hasText = !TextUtils.isEmpty(newText);
                final boolean showClose = hasText;
                if(showClose){
                    searchClose.setAlpha(1.0f);
                }else{
                    searchClose.setAlpha(0.5f);
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            Log.d("jason","action_share");
        }
        return super.onOptionsItemSelected(item);
    }
}
