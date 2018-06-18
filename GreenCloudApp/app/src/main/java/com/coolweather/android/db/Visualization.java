package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by zyq on 2018/5/5.
 */

public class Visualization extends DataSupport implements Serializable {
    private String devId;
    private String sensorOne;
    private String sensorTwo;
    private String sensorThree;
    private String sensorFour;
    private String sensorFive;

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
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
        return "Visualization{" +
                "devId='" + devId + '\'' +
                ", sensorOne='" + sensorOne + '\'' +
                ", sensorTwo='" + sensorTwo + '\'' +
                ", sensorThree='" + sensorThree + '\'' +
                ", sensorFour='" + sensorFour + '\'' +
                ", sensorFive='" + sensorFive + '\'' +
                '}';
    }
}
