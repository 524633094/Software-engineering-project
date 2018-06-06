package com.coolweather.android.gson;

import com.coolweather.android.db.Visualization;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyq on 2018/5/24.
 */

public class VisualizationInfo implements Serializable {
    private String code;
    private String message;
    private List<Visualization> list;

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

    public List<Visualization> getList() {
        return list;
    }

    public void setList(List<Visualization> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "VisualizationInfo{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", list=" + list +
                '}';
    }
}
