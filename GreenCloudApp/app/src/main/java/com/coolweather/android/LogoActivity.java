package com.coolweather.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.coolweather.android.db.User;
import com.coolweather.android.util.EditTextClearTools;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zyq on 2018/5/5.
 */

public class LogoActivity  extends AppCompatActivity {

    private SystemBarTintManager tintManager;
    private EditText etUserName;
    private EditText etUserPassword;
    private Button btnLogin;
    private CheckBox btnCheck;
    private String userName;
    private String userPassword;
    private String address = "http://123.207.124.113:8888/api/login";
//    private String address = "http://10.0.2.2:8888/api/login";
    public User user = new User();
    String result;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public String getUserName(){
        return userName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_activty);
        init();
    }

    private void init(){

        sp= PreferenceManager.getDefaultSharedPreferences(this);
        btnCheck = (CheckBox) findViewById(R.id.cb_checkbox);
        btnLogin = (Button) findViewById(R.id.btn_login);
        etUserName= (EditText) findViewById(R.id.et_userName);
        etUserPassword = (EditText) findViewById(R.id.et_password);
        ImageView unameClear = (ImageView) findViewById(R.id.iv_unameClear);
        ImageView pwdClear = (ImageView) findViewById(R.id.iv_pwdClear);
        EditTextClearTools.addClearListener(etUserName,unameClear);
        EditTextClearTools.addClearListener(etUserPassword,pwdClear);
        boolean isRemenber=sp.getBoolean("remember_password",false);
        if(isRemenber){
            //将账号和密码都设置到文本中
            String account=sp.getString("account","");
            String password=sp.getString("password","");
            etUserName.setText(account);
            etUserPassword.setText(password);
            btnCheck.setChecked(true);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            private Handler handler = new Handler() {

                public void handleMessage(Message msg) {
                    String str = (String) msg.obj;
                    Toast.makeText(LogoActivity.this, str, Toast.LENGTH_SHORT).show();
                };
            };
            @Override
            public void onClick(View view) {

                userName = etUserName.getText().toString();
                userPassword = etUserPassword.getText().toString();
//                Log.d("userinfo",userName);
                user.setUsername(userName);
                user.setPassword(userPassword);
               if(userName.equals("")||etUserPassword.equals("")){
                   Toast.makeText(LogoActivity.this,"请输入用户名和密码！",Toast.LENGTH_SHORT).show();
               }
                else{
                   new Thread(new Runnable(){
                       @Override
                       public void run() {
                           OkHttpClient okHttpClient = new OkHttpClient();
                           //Form表单格式的参数传递
                           FormBody formBody = new FormBody
                                   .Builder()
                                   .add("username",userName)//设置参数名称和参数值
                                   .add("password",userPassword)
                                   .build();

                           Request request = new Request
                                   .Builder()
                                   .url(address)
                                   .post(formBody)//Post请求的参数传递，此处是和Get请求相比，多出的一句代码</font>
                                   .build();
//                        Log.d("Login", request.toString());
                           Call call = okHttpClient.newCall(request);
                           call.enqueue(new Callback() {
                               @Override
                               public void onFailure(Call call, IOException e) {
                                   Toast.makeText(LogoActivity.this,"网络错误！",Toast.LENGTH_SHORT).show();
                               }


                               @Override
                               public void onResponse(Call call, Response response) throws IOException {
//                                Response response = okHttpClient.newCall(request).execute();
                                   result = response.body().string();
//                                   Log.d("Login",result);
                                   response.body().close();

                                   if(result.contains("成功")){
                                       editor=sp.edit();
                                       if(btnCheck.isChecked()){
                                           editor.putBoolean("remember_password",true);
                                           editor.putString("account",userName);
                                           editor.putString("password",userPassword);
                                       }else {
                                           editor.clear();
                                       }
                                       editor.apply();

                                       Intent intent = new Intent(LogoActivity.this, WeatherActivity.class);
                                       intent.putExtra("user_data",user);
                                       startActivity(intent);
                                       finish();
                                   }else {
                                       Message msg = new Message();
                                       msg.obj ="用户名或密码不正确";
                                       // 把消息发送到主线程，在主线程里现实Toast
                                       handler.sendMessage(msg);
                                   }

                               }
                           });
                       }
                   }).start();
               }


            }
        });
    }

}
