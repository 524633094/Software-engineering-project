package com.coolweather.android.gson;

import com.coolweather.android.db.Dev;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyq on 2018/5/30.
 */

public class DevInfo implements Serializable {
    private String code;
    private String message;
    private List<Dev>  list;

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

    public List<Dev> getList() {
        return list;
    }

    public void setList(List<Dev> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "DevInfo{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", list=" + list +
                '}';
    }
}
