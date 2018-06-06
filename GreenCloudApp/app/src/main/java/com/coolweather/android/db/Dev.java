package com.coolweather.android.db;

/**
 * Created by zyq on 2018/5/5.
 */
import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class Dev extends DataSupport implements Serializable {
    private String id;
    private String devName;
    private String apiey;
    private String sensorOne;
    private String sensorTwo;
    private String sensorThree;
    private String sensorFour;
    private String sensorFive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getApiey() {
        return apiey;
    }

    public void setApiey(String apiey) {
        this.apiey = apiey;
    }

    public String getSensorOne() {
        return sensorOne;
    }

    public void setSensorOne(String sensorOne) {
        this.sensorOne = sensorOne;
    }

    public String getSensorTwo() {
        return sensorTwo;
    }

    public void setSensorTwo(String sensorTwo) {
        this.sensorTwo = sensorTwo;
    }

    public String getSensorThree() {
        return sensorThree;
    }

    public void setSensorThree(String sensorThree) {
        this.sensorThree = sensorThree;
    }

    public String getSensorFour() {
        return sensorFour;
    }

    public void setSensorFour(String sensorFour) {
        this.sensorFour = sensorFour;
    }

    public String getSensorFive() {
        return sensorFive;
    }

    public void setSensorFive(String sensorFive) {
        this.sensorFive = sensorFive;
    }

    @Override
    public String toString() {
        return "Dev{" +
                "id='" + id + '\'' +
                ", devName='" + devName + '\'' +
                ", apiey='" + apiey + '\'' +
                ", sensorOne='" + sensorOne + '\'' +
                ", sensorTwo='" + sensorTwo + '\'' +
                ", sensorThree='" + sensorThree + '\'' +
                ", sensorFour='" + sensorFour + '\'' +
                ", sensorFive='" + sensorFive + '\'' +
                '}';
    }
}
