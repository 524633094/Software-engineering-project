package com.coolweather.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.coolweather.android.db.User;

/**
 * Created by zyq on 2018/6/3.
 */

public class ViewActivity extends AppCompatActivity {

    WebView explorer ;
    EditText rec;
    Button submit;
    Button reload;
    Button forward;
    Button goback;
    Button home_btn;
    String home = "http://www.baidu.com";
    public User user ;



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
        explorer = (WebView) findViewById(R.id.explorer);
        rec = (EditText) findViewById(R.id.rec);
        reload = (Button) findViewById(R.id.reload);

        goback = (Button) findViewById(R.id.goback);
        submit = (Button) findViewById(R.id.submit);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        WebSettings settings = explorer.getSettings();
        settings.setJavaScriptEnabled(true);
        WebViewClient client = new WebViewClient();
        explorer.setWebViewClient(client);
        //home = rec.getText().toString()+":8080/?action=stream";
        explorer.loadUrl(home);


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                explorer.loadUrl("http://"+rec.getText()+":8080/?action=stream");

            }
        });
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explorer.reload();
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = getIntent();
                myIntent.putExtra("user_data",user);
                myIntent = new Intent(ViewActivity.this, WeatherActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        rec.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    explorer.loadUrl("http://"+rec.getText()+":8080/?action=stream");
                    return true;
                }
                return false;
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = getIntent();
        myIntent.putExtra("user_data",user);
        myIntent = new Intent(ViewActivity.this, WeatherActivity.class);
        startActivity(myIntent);
        finish();
    }




}
