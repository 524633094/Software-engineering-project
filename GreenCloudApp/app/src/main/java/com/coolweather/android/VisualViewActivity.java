package com.coolweather.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.coolweather.android.db.Dev;
import com.coolweather.android.db.User;

/**
 * Created by zyq on 2018/6/3.
 */

public class VisualViewActivity extends AppCompatActivity {

    WebView explorer ;
    LinearLayout ll ;
    String home = "http://www.baidu.com";
    public User user ;
    private Dev dev;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.BLACK);
        }
        setContentView(R.layout.activity_view);
        user = (User) getIntent().getSerializableExtra("user_data");
        dev = (Dev) getIntent().getSerializableExtra("choose_dev");
        explorer = (WebView) findViewById(R.id.explorer);
        ll = (LinearLayout) findViewById(R.id.visual_view);
        ll.setVisibility(View.GONE);


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        WebSettings settings = explorer.getSettings();
        settings.setJavaScriptEnabled(true);
        WebViewClient client = new WebViewClient();
        explorer.setWebViewClient(client);
        home = "http://123.207.124.113:8888/page/visual/"+user.getApiKey()+"/"+dev.getDevName();
        Log.d("home",home);
        explorer.loadUrl(home);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = getIntent();
        myIntent.putExtra("user_data",user);
        myIntent = new Intent(VisualViewActivity.this, WeatherActivity.class);
        startActivity(myIntent);
        finish();
    }




}
