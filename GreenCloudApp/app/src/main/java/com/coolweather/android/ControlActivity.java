package com.coolweather.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coolweather.android.db.Dev;
import com.coolweather.android.db.User;
import com.coolweather.android.db.Visualization;
import com.coolweather.android.gson.DevInfo;
import com.coolweather.android.gson.UserInfo;
import com.coolweather.android.gson.VisualizationInfo;
import com.coolweather.android.service.AutoUpdateService;
import com.coolweather.android.util.HttpUtil;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zyq on 2018/6/3.
 */

public class ControlActivity extends AppCompatActivity {
    private Button navButton;
    public DrawerLayout drawerLayout;
    public SwipeRefreshLayout swipeRefresh;
    private ScrollView weatherLayout;
    private TextView degreeText;
    private ImageView bingPicImg;

    private TextView water_num;
    private Button btn_water;
    private TextView curtain_num;
    private Button btn_curtain;



    public User user ;
    private Dev dev ;
    public UserInfo userinfo = new UserInfo();
    private VisualizationInfo visualizationInfo = new VisualizationInfo();
    public DevInfo devInfo = new DevInfo();

    private int water_state= 0;
    private int curtain_state = 0;
    private final Timer timer = new Timer();
    private TimerTask task;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 想要执行的事件
            super.handleMessage(msg);
            getVisualizationInfo(1);
            showWeatherInfo(visualizationInfo,dev);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_control);

        navButton = (Button) findViewById(R.id.nav_button);
        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        degreeText = (TextView) findViewById(R.id.degree_text);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        water_num = (TextView) findViewById(R.id.water_num);
        btn_water = (Button) findViewById(R.id.btn_water);
        curtain_num = (TextView) findViewById(R.id.curtain_num);
        btn_curtain = (Button) findViewById(R.id.btn_curtain);
        userinfo = (UserInfo) getIntent().getSerializableExtra("user_data");
        dev = (Dev) getIntent().getSerializableExtra("choose_dev");
        loadBingPic();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String  vliString = prefs.getString("sp_visualizationInfo", "");
        byte[] base64Student_vli = Base64.decode(vliString, Base64.DEFAULT);
        ByteArrayInputStream bais_vli = new ByteArrayInputStream(base64Student_vli);
        try {
            ObjectInputStream ois_vli = new ObjectInputStream(bais_vli);
            visualizationInfo = (VisualizationInfo) ois_vli.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        btn_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opem_close_water();
            }
        });

        btn_curtain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getVisualizationInfo(1);
//                showWeatherInfo(visualizationInfo,dev);
                if(curtain_state==0) {
                    btn_curtain.setText("关闭遮光板");
                    curtain_state = 1;
                }else if(curtain_state==1) {
                    btn_curtain.setText("打开遮光板");
                    curtain_state = 0;
                }
            }
        });

        if (userinfo.getCode() != null && devInfo.getCode() != null && visualizationInfo.getCode() !=null ) {
//            Log.d("user111",userinfo.toString());
//            Log.d("dev111",devInfo.toString());
//            Log.d("vius1111",visualizationInfo.toString());

            showWeatherInfo(visualizationInfo,dev);
        } else {
            weatherLayout.setVisibility(View.INVISIBLE);
            getVisualizationInfo(1);
            //showWeatherInfo(visualizationInfo,dev);
        }
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getVisualizationInfo(1);
                showWeatherInfo(visualizationInfo,dev);
                swipeRefresh.setRefreshing(false);
            }
        });

        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
//        String bingPic = prefs.getString("bing_pic", null);
//        if (bingPic != null) {
//            Glide.with(this).load(bingPic).into(bingPicImg);
//        } else {
//            loadBingPic();
//        }


        if(dev!=null)
//            Log.d("choose____dev",dev.toString());

        if(dev!=null&&visualizationInfo!=null)
            showWeatherInfo(visualizationInfo,dev);

        task = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };

        timer.schedule(task, 2000, 3000);


    }

    public void opem_close_water(){
        final String address = "http://123.207.124.113:8888/api/auto/water/"+userinfo.getUser().getApiKey()+"/"+dev.getDevName();
//        Log.d("opem_close_water",address);
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request
                        .Builder()
                        .url(address)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //Toast.makeText(ControlActivity.this,"网络错误！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
//                        Log.d("Login",result);
                        getVisualizationInfo(1);
                        ControlActivity.this.runOnUiThread(new Runnable()
                        {
                            public void run()
                            {
//                                Log.d("Login",btn_water.getText().toString());
                                if(water_state==0) {

                                    btn_water.setText("关闭浇水");
//                                    Log.d("Login",btn_water.getText().toString());
                                    water_state = 1;
                                }else if(water_state==1) {
                                    btn_water.setText("开启浇水");
                                    water_state = 0;
                                }

                                showWeatherInfo(visualizationInfo,dev);
                            }

                        });

                    }
                });
            }
        });
    }

    public void opem_close_curtain(){
        final String address = "http://123.207.124.113:8888/api/auto/curtain/"+userinfo.getUser().getApiKey()+"/"+dev.getDevName();

        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request
                        .Builder()
                        .url(address)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //Toast.makeText(ControlActivity.this,"网络错误！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        getVisualizationInfo(1);

                        ControlActivity.this.runOnUiThread(new Runnable()
                        {
                            public void run()
                            {
                                if(curtain_state==0) {
                                    btn_water.setText("关闭遮光板");
                                    water_state = 1;
                                }else if(curtain_state==1) {
                                    btn_water.setText("打开遮光板");
                                    water_state = 0;
                                }
                                showWeatherInfo(visualizationInfo,dev);
                            }

                        });

                    }
                });
            }
        });
    }


    public void getVisualizationInfo(int num){
        String address = "";
        address= "http://123.207.124.113:8888/api/sensor/data/"+userinfo.getUser().getApiKey()+"/"+dev.getDevName()+"/"+num;
        final String lastaddress  = address;
//        Log.d("lastaddress",lastaddress);
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request
                        .Builder()
                        .url(lastaddress)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //Toast.makeText(ControlActivity.this,"网络错误！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
//                        Log.d("Login",result);
                        response.body().close();
                        Gson gson = new Gson();
                        visualizationInfo = gson.fromJson(result, VisualizationInfo.class);
//                        Log.d("visualizationInfo_info1",visualizationInfo.toString());
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(ControlActivity.this).edit();
                        ByteArrayOutputStream baos_vli = new ByteArrayOutputStream();
                        ObjectOutputStream oos_vli = new ObjectOutputStream(baos_vli);
                        oos_vli.writeObject(visualizationInfo);
                        String base64Student_vli = Base64.encodeToString(baos_vli.toByteArray(), Base64.DEFAULT);
                        editor.putString("sp_visualizationInfo", base64Student_vli);
                        editor.apply();
                        oos_vli.close();

                    }
                });
            }
        });
    }

    private void showWeatherInfo(VisualizationInfo vli,Dev dev) {

//        Log.d("get devinfo0",devInfo.toString());
        Log.d("vil",vli.toString());
        String degree = dev.getDevName();
        degreeText.setText(degree.toString());
        if(vli.getList()!=null){
            water_num.setText(vli.getList().get(0).getSensorFour()+"%");
            curtain_num.setText(vli.getList().get(0).getSensorThree()+"lx");
        }


       weatherLayout.setVisibility(View.VISIBLE);

    }



    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(ControlActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(ControlActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

}
