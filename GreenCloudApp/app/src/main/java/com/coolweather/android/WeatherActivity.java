package com.coolweather.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coolweather.android.db.Dev;
import com.coolweather.android.db.Flower;
import com.coolweather.android.db.User;
import com.coolweather.android.db.Visualization;
import com.coolweather.android.gson.DevInfo;
import com.coolweather.android.gson.FlowerInfo;
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
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;

    public SwipeRefreshLayout swipeRefresh;

    private ScrollView weatherLayout;

    private Button navButton;

    private TextView titleCity;

    private TextView titleUpdateTime;

    private TextView degreeText;

    private TextView weatherInfoText;

    private LinearLayout forecastLayout;

    private TextView aqiText;

    private TextView pm25Text;

    private TextView aqiText_1;

    private TextView pm25Text_1;

    private TextView comfortText;

    private TextView carWashText;

    private TextView sportText;

    private ImageView bingPicImg;

    private String mWeatherId;
    public User user = null;
    private Dev dev =null;
    public UserInfo userinfo = new UserInfo();
    private VisualizationInfo visualizationInfo = new VisualizationInfo();
    public DevInfo devInfo = new DevInfo();
    private Flower flower;
    private FlowerInfo flowerInfo;
    public String plant_name = "长按设置植物名称";
    public final Timer timer = new Timer();
    private TimerTask task;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 想要执行的事件
            super.handleMessage(msg);
            getUserInfo();
            showWeatherInfo(visualizationInfo,dev);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        if(user == null )  user = (User) getIntent().getSerializableExtra("user_data");
        dev = (Dev) getIntent().getSerializableExtra("choose_dev");

        // 初始化各控件
        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        aqiText = (TextView) findViewById(R.id.aqi_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        aqiText_1 = (TextView) findViewById(R.id.aqi_text_1);
        pm25Text_1 = (TextView) findViewById(R.id.pm25_text_1);
        comfortText = (TextView) findViewById(R.id.comfort_text);
        carWashText = (TextView) findViewById(R.id.car_wash_text);
        sportText = (TextView) findViewById(R.id.sport_text);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navButton = (Button) findViewById(R.id.nav_button);





        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);


        String  userString = prefs.getString("sp_user", "");
        String  devString = prefs.getString("sp_devInfo", "");
        String  vliString = prefs.getString("sp_visualizationInfo", "");

        byte[] base64Student_user = Base64.decode(userString, Base64.DEFAULT);
        byte[] base64Student_dev = Base64.decode(devString, Base64.DEFAULT);
        byte[] base64Student_vli = Base64.decode(vliString, Base64.DEFAULT);

        ByteArrayInputStream bais_user = new ByteArrayInputStream(base64Student_user);
        ByteArrayInputStream bais_dev = new ByteArrayInputStream(base64Student_dev);
        ByteArrayInputStream bais_vli = new ByteArrayInputStream(base64Student_vli);

        try {
            ObjectInputStream ois_user = new ObjectInputStream(bais_user);
            ObjectInputStream ois_dev = new ObjectInputStream(bais_dev);
            ObjectInputStream ois_vli = new ObjectInputStream(bais_vli);
            userinfo = (UserInfo) ois_user.readObject();
            devInfo = (DevInfo) ois_dev.readObject();
            visualizationInfo = (VisualizationInfo) ois_vli.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(user == null) user = userinfo.getUser();
        SharedPreferences.Editor editor = prefs.edit();
        Log.d("user info 111",user.toString());
        editor.putString("auto_username",user.getUsername());
        editor.commit();

        loadBingPic();

        if (userinfo.getCode() != null && devInfo.getCode() != null && visualizationInfo.getCode() !=null ) {
//            Log.d("user111",userinfo.toString());
//            Log.d("dev111",devInfo.toString());
//            Log.d("vius1111",visualizationInfo.toString());
            if(dev == null)  dev = devInfo.getList().get(0);
            // 有缓存时直接解析天气数据
            showWeatherInfo(visualizationInfo,dev);
        } else {
            // 无缓存时去服务器查询天气
//            mWeatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather();
        }

        if(plant_name.equals("长按设置植物名称")&&degreeText.getText().equals("长按设置植物名称")) degreeText.setTextSize(25);

        int re_num = 0;
        while (dev == null && re_num<9 ){
            try {
                Thread.sleep(500);
                getDevInfo();
                re_num++;
               Log.d("111111111","222222222");
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }


        try {
            plant_name = prefs.getString(dev.getDevName(),plant_name);

            degreeText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final EditText editText = new EditText(WeatherActivity.this);
                    AlertDialog.Builder inputDialog =
                            new AlertDialog.Builder(WeatherActivity.this);
                    inputDialog.setTitle("设置植物名称").setView(editText);
                    inputDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    plant_name = editText.getText().toString();
                                    degreeText.setText(editText.getText());
                                    degreeText.setTextSize(60);
                                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString(dev.getDevName(),plant_name);

                                    editor.apply();
                                }
                            }).show();
                    return true;
                }
            });

            swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    requestWeather();
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

            if(dev!=null)
    //            Log.d("choose____dev",dev.toString());

            if(dev!=null&&visualizationInfo!=null)
                showWeatherInfo(visualizationInfo,dev);



            if(!degreeText.getText().equals("长按设置植物名称")) degreeText.setTextSize(60);
        } catch (Exception e) {
            e.printStackTrace();
            degreeText.setText("暂无设备，无法获取信息。");
            comfortText.setText("设置植物名称，可查看养花信息。");
            carWashText.setText("");
            sportText.setText("");
            weatherLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        timer.cancel();
        Log.d("11","0000000000000000000000");

    }

    @Override
    protected void onPause() {
        super.onPause();

        timer.cancel();
        Log.d("11","555555555555555555555");

    }

    public void getUserInfo(){
//        Log.d("username",user.toString());
        final String address = "http://123.207.124.113:8888/api/user/"+user.getUsername();

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
                        //Toast.makeText(WeatherActivity.this,"网络错误！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Log.d("Login",result);
                        response.body().close();
                        Gson gson = new Gson();
                        userinfo = gson.fromJson(result, UserInfo.class);
//                        Log.d("userinfo_info",userinfo.toString());
                        getDevInfo();
                    }
                });
            }
        });
    }


    public void getDevInfo(){
        try {
            final String address = "http://123.207.124.113:8888/api/user/dev/"+userinfo.getUser().getApiKey();

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
//                            Toast.makeText(WeatherActivity.this,"网络错误！",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String result = response.body().string();
                            Log.d("Login",result);
                            response.body().close();
                            Gson gson = new Gson();
                            devInfo = gson.fromJson(result, DevInfo.class);
//                            Log.d("devInfo_info",devInfo.toString());

                            try {
                                if(dev == null)  dev = devInfo.getList().get(0);
                                getVisualizationInfo(6);
                                getFlowerInfo();
                            } catch (Exception e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        degreeText.setText("暂无设备，无法获取信息。");
                                        comfortText.setText("设置植物名称，可查看养花信息。");
                                        carWashText.setText("");
                                        sportText.setText("");
                                        weatherLayout.setVisibility(View.VISIBLE);
                                    }
                                });

                            }
                        }
                    });
                }
            });
        } catch (Exception e) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String  userString = prefs.getString("sp_user", "");
            byte[] base64Student_user = Base64.decode(userString, Base64.DEFAULT);
            ByteArrayInputStream bais_user = new ByteArrayInputStream(base64Student_user);

            try {
                ObjectInputStream ois_user = new ObjectInputStream(bais_user);
                userinfo = (UserInfo) ois_user.readObject();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

            getUserInfo();
            e.printStackTrace();

        }
    }


    public void getVisualizationInfo(int num){
        String address = "";
        address= "http://123.207.124.113:8888/api/sensor/data/"+userinfo.getUser().getApiKey()+"/"+dev.getDevName()+"/"+num;
        final String lastaddress  = address;
        Log.d("lastaddress",lastaddress);
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
                        //Toast.makeText(WeatherActivity.this,"网络错误！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                       Log.d("Login",result);
                        response.body().close();
                        Gson gson = new Gson();
                        visualizationInfo = gson.fromJson(result, VisualizationInfo.class);
//                        Log.d("visualizationInfo_info1",visualizationInfo.toString());


                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                        ByteArrayOutputStream baos_user = new ByteArrayOutputStream();
                        ObjectOutputStream oos_user = new ObjectOutputStream(baos_user);
                        oos_user.writeObject(userinfo);
                        String base64Student_user = Base64.encodeToString(baos_user.toByteArray(), Base64.DEFAULT);

                        ByteArrayOutputStream baos_dev = new ByteArrayOutputStream();
                        ObjectOutputStream oos_dev = new ObjectOutputStream(baos_dev);
                        oos_dev.writeObject(devInfo);
                        String base64Student_dev = Base64.encodeToString(baos_dev.toByteArray(), Base64.DEFAULT);

                        ByteArrayOutputStream baos_vli = new ByteArrayOutputStream();
                        ObjectOutputStream oos_vli = new ObjectOutputStream(baos_vli);
                        oos_vli.writeObject(visualizationInfo);
                        String base64Student_vli = Base64.encodeToString(baos_vli.toByteArray(), Base64.DEFAULT);
                        editor.putString("sp_user", base64Student_user);
                        editor.putString("sp_devInfo", base64Student_dev);
                        editor.putString("sp_visualizationInfo", base64Student_vli);
                        editor.commit();
                        oos_user.close();
                        oos_dev.close();
                        oos_vli.close();
                        WeatherActivity.this.runOnUiThread(new Runnable()
                        {
                            public void run()
                            {

                                showWeatherInfo(visualizationInfo,dev);

                            }

                        });

                    }
                });
            }
        });
    }


    public void getFlowerInfo(){
        final String address = "http://123.207.124.113:8888/api/flowers/"+plant_name;
//        Log.d("flower_address",address);


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
                        //Toast.makeText(WeatherActivity.this,"网络错误！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
//                        Log.d("Login",result);
                        response.body().close();
                        Gson gson = new Gson();
                        flowerInfo = gson.fromJson(result, FlowerInfo.class);
//                        Log.d("flowerInfo_info",flowerInfo.toString());
//                        flower = flowerInfo.getList().get(0);
                    }
                });
            }
        });
    }

    /**
     * 根据天气id请求城市天气信息。
     */
    public void requestWeather() {
        getUserInfo();
        loadBingPic();
        if(plant_name!="长按设置植物名称"||plant_name!=""||plant_name!=null) getFlowerInfo();
    }

    /**
     * 加载必应每日一图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 处理并展示Weather实体类中的数据。
     */
    private void showWeatherInfo(VisualizationInfo vli,Dev dev) {

        if(vli.getList()==null) {
//            degreeText.setText("暂无设备，无法获取信息。");
//            comfortText.setText("设置植物名称，可查看养花信息。");
//            carWashText.setText("");
//            sportText.setText("");
            weatherLayout.setVisibility(View.VISIBLE);
            getVisualizationInfo(1);
        }
        else{
//            Log.d("get devinfo0",devInfo.toString());
            if(dev == null) dev = devInfo.getList().get(0);
            String degree = dev.getDevName();

            degreeText.setText(plant_name);
            forecastLayout.removeAllViews();
            String data = getTime();
            for (Visualization v : vli.getList()) {
                View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
                TextView dateText = (TextView) view.findViewById(R.id.date_text);
                TextView infoText = (TextView) view.findViewById(R.id.info_text);
                TextView maxText = (TextView) view.findViewById(R.id.max_text);
                TextView minText = (TextView) view.findViewById(R.id.min_text);
                TextView midText = (TextView) view.findViewById(R.id.mid_text);
                dateText.setText(data);
                infoText.setText(v.getSensorOne()+"℃");
                maxText.setText(v.getSensorTwo()+"%");

                midText.setText(v.getSensorThree()+"LX");
                minText.setText(v.getSensorFour()+"%");
                forecastLayout.addView(view);
            }
            aqiText.setText(vli.getList().get(0).getSensorOne()+"℃");
            pm25Text.setText(vli.getList().get(0).getSensorTwo()+"%");
            aqiText_1.setText(vli.getList().get(0).getSensorThree()+"lx");
            pm25Text_1.setText(vli.getList().get(0).getSensorFour()+"%");
            String comfort = "";
            String carWash = "";
            String sport = "";
//        comfort = "湿度：50-85%，有条件可以使用加湿器，也可以向叶面喷水。往观花植物叶面喷水的时候切勿喷到花上，花瓣遇水容易发生病害，影响观赏效果。冬天在北方由于供暖的原因，会造成室内高温干燥，此时更应该增加空气湿度。";
//        carWash = "光照：需要根据花卉的习性来调整，阴生的植物，光照过强会导致日灼。各类花卉对光照的需求都不一样的。一般家庭养护中比较折中的做法就是放到有散射光照射的地方。忌阳光直射尤其是夏天。对光照要求比较高的植物还是要适当的多接受阳光照射。";
//        sport = "sport温度：一般适宜花卉生长的温度15-35℃，观叶植物在开花期，应该在保证不发生冻害的前提下尽量降低温蒂，降低温度可以延长花期。";

            try {
                comfort = "植物简介："+flowerInfo.getList().get(0).getIntro();
                carWash = "植物习性："+flowerInfo.getList().get(0).getHabit();
                sport = "养殖方法："+flowerInfo.getList().get(0).getPlantmehod();
            } catch (Exception e) {
                e.printStackTrace();
                comfort = "设置植物名称，可查看养花信息。";
            }

            comfortText.setText(comfort);
            carWashText.setText(carWash);
            sportText.setText(sport);
            weatherLayout.setVisibility(View.VISIBLE);
//            Intent intent = new Intent(WeatherActivity.this, AutoUpdateService.class);
//            Log.d("user 1111",user.toString());
//            intent.putExtra("user_data1",user);
//            startService(intent);
//        Intent intent = new Intent(this, AutoUpdateService.class);
//        intent.putExtra("user_data",user);
//        startService(intent);
        }

    }

    public String getTime(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return year+"/"+month+"/"+day+"/"+hour+"/"+minute;
        //return month+"/"+day+"/"+hour+"/"+minute;
    }



}
