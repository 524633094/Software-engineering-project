package com.coolweather.android.gson;

import com.coolweather.android.db.User;

import java.io.Serializable;

/**
 * Created by zyq on 2018/5/24.
 */

public class UserInfo implements Serializable {
    private String code;
    private String message;
    private String list;
    private User data;

    @Override
    public String toString() {
        return "UserInfo{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", list='" + list + '\'' +
                ", data=" + data +
                '}';
    }


    public User getUser() {
        return data;
    }

    public void setUser(User user) {
        this.data = user;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
