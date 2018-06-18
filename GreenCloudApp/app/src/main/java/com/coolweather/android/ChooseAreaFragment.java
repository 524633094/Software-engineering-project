package com.coolweather.android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.coolweather.android.db.Dev;
import com.coolweather.android.db.User;
import com.coolweather.android.gson.DevInfo;
import com.coolweather.android.gson.UserInfo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ChooseAreaFragment extends Fragment {

    private static final String TAG = "ChooseAreaFragment";
    public static final int LEVEL_ONE = 0;
    private ProgressDialog progressDialog;
    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<String>();
    private List<String> dataList1 = new ArrayList<String>();
    private List<String> menuList = new ArrayList<String>();
    private List<String> devList = new ArrayList<String>();
    private int currentLevel=0;
    private User user = new User();
    private DevInfo devInfo = new DevInfo();
    public UserInfo userinfo = new UserInfo();
    WeatherActivity activity = (WeatherActivity) getActivity();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);



        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String  userString = prefs.getString("sp_user", "");
        String  devString = prefs.getString("sp_devInfo", "");

        byte[] base64Student_user = Base64.decode(userString, Base64.DEFAULT);
        byte[] base64Student_dev = Base64.decode(devString, Base64.DEFAULT);

        ByteArrayInputStream bais_user = new ByteArrayInputStream(base64Student_user);
        ByteArrayInputStream bais_dev = new ByteArrayInputStream(base64Student_dev);

        try {
            ObjectInputStream ois_user = new ObjectInputStream(bais_user);
            ObjectInputStream ois_dev = new ObjectInputStream(bais_dev);
            userinfo = (UserInfo) ois_user.readObject();
            devInfo = (DevInfo) ois_dev.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        menuList.add("我的设备");
        menuList.add("设备管理");
        menuList.add("设备数据可视化");
        menuList.add("设备监控");


        dataList1.clear();
        for(String menu:menuList)
            dataList1.add(menu);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList1);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            int flag = 4 ; // 当前打开的是 我的设备-》1  设备管理-》2  设备数据可视化-》4   设备监控-》4
            int falg1 = 1; // 标示 我的设备 序号
            int falg2 = 2;// 标示 设备管理 序号
            int falg3 = 3;// 标示 设备数据可视化 序号
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String  userString = prefs.getString("sp_user", "");
                    String  devString = prefs.getString("sp_devInfo", "");

                    byte[] base64Student_user = Base64.decode(userString, Base64.DEFAULT);
                    byte[] base64Student_dev = Base64.decode(devString, Base64.DEFAULT);

                    ByteArrayInputStream bais_user = new ByteArrayInputStream(base64Student_user);
                    ByteArrayInputStream bais_dev = new ByteArrayInputStream(base64Student_dev);

                    try {
                        ObjectInputStream ois_user = new ObjectInputStream(bais_user);
                        ObjectInputStream ois_dev = new ObjectInputStream(bais_dev);
                        userinfo = (UserInfo) ois_user.readObject();
                        devInfo = (DevInfo) ois_dev.readObject();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }

//                    Log.d("position1111111",position+"");
//                    Log.d("id1111111",id+"");
//                    Log.d("2222",getActivity().getClass().toString());

                    Log.d("devinfo",devInfo.toString());
                    devList.clear();
                    for(Dev d : devInfo.getList()){
                        devList.add(d.getDevName());
                    }
                    dataList1.clear();
//                falg1=1;
                    if(position == 0){
                        dataList1.add(menuList.get(0));
                        for (int j=0;j<devList.size();j++){
                            dataList1.add("--> "+devList.get(j));
                        }
                        dataList1.add(menuList.get(1));
                        dataList1.add(menuList.get(2));
                        dataList1.add(menuList.get(3));
                        falg1 = 1+devList.size();
                        falg2 = 2+devList.size();
                        falg3 = 3+devList.size();
                        flag = 1;
                    } else if(position == falg1){
                        dataList1.add(menuList.get(0));
                        dataList1.add(menuList.get(1));
                        for (int j=0;j<devList.size();j++){
                            dataList1.add("--> "+devList.get(j));
                        }
                        dataList1.add(menuList.get(2));
                        dataList1.add(menuList.get(3));
                        falg1 = 1;
                        falg2 = 2+devList.size();
                        falg3 = 3+devList.size();
                        flag = 2;
                    }else if(position == falg2){
                        dataList1.add(menuList.get(0));
                        dataList1.add(menuList.get(1));
                        dataList1.add(menuList.get(2));
                        for (int j=0;j<devList.size();j++){
                            dataList1.add("--> "+devList.get(j));
                        }
                        dataList1.add(menuList.get(3));
                        falg1 = 1;
                        falg2 = 2;
                        falg3 = 3+devList.size();
                        flag = 3;
                    }else if(position == falg3){
                        dataList1.add(menuList.get(0));
                        dataList1.add(menuList.get(1));
                        dataList1.add(menuList.get(2));
                        dataList1.add(menuList.get(3));
                        falg1 = 1;
                        falg2 = 2;
                        falg3 = 3;
                        flag = 4;
                        Intent intent = new Intent(getActivity(), ViewActivity.class);
                        intent.putExtra("user_data", userinfo.getUser());
                        startActivity(intent);
                        getActivity().finish();
                    } else{
                        if(flag == 1){
                            Intent intent = new Intent(getActivity(), WeatherActivity.class);
                            intent.putExtra("choose_dev", devInfo.getList().get(position-1));
                            intent.putExtra("user_data", userinfo.getUser());
                            startActivity(intent);

                            getActivity().finish();

                        }else if(flag == 2){
                            Intent intent = new Intent(getActivity(), ControlActivity.class);
                            intent.putExtra("choose_dev", devInfo.getList().get(position-2));
                            intent.putExtra("user_data", userinfo);
                            startActivity(intent);
                            getActivity().finish();
                        }else if(flag == 3){
                            Intent intent = new Intent(getActivity(), VisualViewActivity.class);
                            intent.putExtra("choose_dev", devInfo.getList().get(position-3));
                            intent.putExtra("user_data", userinfo.getUser());
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String  userString = prefs.getString("sp_user", "");
                    String  devString = prefs.getString("sp_devInfo", "");

                    byte[] base64Student_user = Base64.decode(userString, Base64.DEFAULT);
                    byte[] base64Student_dev = Base64.decode(devString, Base64.DEFAULT);

                    ByteArrayInputStream bais_user = new ByteArrayInputStream(base64Student_user);
                    ByteArrayInputStream bais_dev = new ByteArrayInputStream(base64Student_dev);

                    try {
                        ObjectInputStream ois_user = new ObjectInputStream(bais_user);
                        ObjectInputStream ois_dev = new ObjectInputStream(bais_dev);
                        userinfo = (UserInfo) ois_user.readObject();
                        devInfo = (DevInfo) ois_dev.readObject();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    WeatherActivity activity = (WeatherActivity) getActivity();
                    activity.drawerLayout.closeDrawers();
                }


            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLevel = LEVEL_ONE;
                WeatherActivity activity = (WeatherActivity) getActivity();
                activity.drawerLayout.closeDrawers();

            }
        });
    }



    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
