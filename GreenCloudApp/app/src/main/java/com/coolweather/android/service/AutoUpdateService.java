package com.coolweather.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.coolweather.android.R;
import com.coolweather.android.db.Dev;
import com.coolweather.android.db.Flower;
import com.coolweather.android.db.User;
import com.coolweather.android.db.Visualization;
import com.coolweather.android.gson.DevInfo;
import com.coolweather.android.gson.FlowerInfo;
import com.coolweather.android.gson.UserInfo;
import com.coolweather.android.gson.VisualizationInfo;
import com.coolweather.android.util.HttpUtil;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AutoUpdateService extends Service {
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
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;
    private ImageView bingPicImg;

    public User user = new User();
    private Dev dev ;
    private UserInfo userinfo = new UserInfo();
    private VisualizationInfo visualizationInfo = new VisualizationInfo();
    public DevInfo devInfo = new DevInfo();
    String username = "";
    private Flower flower;
    private FlowerInfo flowerInfo;
    public String plant_name = "长按设置植物名称";




    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        username = prefs.getString("auto_username",null);

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        user = (User) intent.getSerializableExtra("user_data1");
        if(user ==  null) {
            user = new User();
            user.setUsername(username);
        }
        getUserInfo();
        updateBingPic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        int anHour = 8 * 60 * 60 * 1000; // 这是8小时的毫秒数
        int anHour = 3*1000; // 这是8小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    public void getUserInfo(){
        Log.d("username",user.toString());
        final String address = "http://123.207.124.113:8888/api/user/"+user.getUsername();

        new Thread(new Runnable(){
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
                        Toast.makeText(AutoUpdateService.this,"网络错误！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Log.d("Login",result);
                        response.body().close();
                        Gson gson = new Gson();
                        userinfo = gson.fromJson(result, UserInfo.class);
                        Log.d("userinfo_info",userinfo.toString());
                        getDevInfo();
                    }
                });
            }
        }).start();
    }


    public void getDevInfo(){
        try {
            final String address = "http://123.207.124.113:8888/api/user/dev/"+userinfo.getUser().getApiKey();

            new Thread(new Runnable(){
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
                            Toast.makeText(AutoUpdateService.this,"网络错误！",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String result = response.body().string();
                            Log.d("Login",result);
                            response.body().close();
                            Gson gson = new Gson();
                            devInfo = gson.fromJson(result, DevInfo.class);
                            Log.d("devInfo_info",devInfo.toString());

                            if(dev == null)  dev = devInfo.getList().get(1);
                            getVisualizationInfo(6);
                            getFlowerInfo();
                        }
                    });
                }
            }).start();
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
        new Thread(new Runnable(){
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
                        Toast.makeText(AutoUpdateService.this,"网络错误！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Log.d("Login",result);
                        response.body().close();
                        Gson gson = new Gson();
                        visualizationInfo = gson.fromJson(result, VisualizationInfo.class);
                        Log.d("visualizationInfo_info1",visualizationInfo.toString());


                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
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
                        editor.apply();
                        oos_user.close();
                        oos_dev.close();
                        oos_vli.close();
//                        AutoUpdateService.this.runOnUiThread(new Runnable()
//                        {
//                            public void run()
//                            {
//
//                                showWeatherInfo(visualizationInfo,dev);
//
//                            }
//
//                        });

                    }
                });
            }
        }).start();
    }


    public void getFlowerInfo(){
        final String address = "http://123.207.124.113:8888/api/flowers/"+plant_name;
        Log.d("flower_address",address);


        new Thread(new Runnable(){
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
                        Toast.makeText(AutoUpdateService.this,"网络错误！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Log.d("Login",result);
                        response.body().close();
                        Gson gson = new Gson();
                        flowerInfo = gson.fromJson(result, FlowerInfo.class);
                        Log.d("flowerInfo_info",flowerInfo.toString());
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
        updateBingPic();
    }




    /**
     * 更新必应每日一图
     */
    private void updateBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }


    private void showWeatherInfo(VisualizationInfo vli,Dev dev) {


        try {
            Log.d("get devinfo0",devInfo.toString());
            if(dev == null) dev = devInfo.getList().get(1);
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
                dateText.setText(data);
                infoText.setText(v.getSensorTwo()+"℃");
                maxText.setText(v.getSensorThree()+"%");
                minText.setText(v.getSensorThree()+"LX");
                forecastLayout.addView(view);
            }
            aqiText.setText(vli.getList().get(0).getSensorTwo()+"℃");
            pm25Text.setText(vli.getList().get(0).getSensorThree()+"lx");
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
            }
            comfortText.setText(comfort);
            carWashText.setText(carWash);
            sportText.setText(sport);
            weatherLayout.setVisibility(View.VISIBLE);
            Intent intent = new Intent(AutoUpdateService.this, AutoUpdateService.class);
            Log.d("user 1111",user.toString());
            intent.putExtra("user_data1",user);
            startService(intent);
//        Intent intent = new Intent(this, AutoUpdateService.class);
//        intent.putExtra("user_data",user);
//        startService(intent);
        } catch (Exception e) {
            getVisualizationInfo(1);
            e.printStackTrace();
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
    }

}
