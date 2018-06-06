package com.coolweather.android.gson;

import com.coolweather.android.db.Flower;
import com.coolweather.android.db.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyq on 2018/5/24.
 */

public class FlowerInfo implements Serializable {
    private String code;
    private String message;
    private List<Flower> list;
    private String data;

    @Override
    public String toString() {
        return "UserInfo{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", list='" + list + '\'' +
                ", data=" + data +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Flower> getList() {
        return list;
    }

    public void setList(List<Flower> list) {
        this.list = list;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
