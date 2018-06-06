package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

public class Flower extends DataSupport {

    private int id;

    private String name;
    private String othername;
    private String pltype;
    private String plmode;
    private String pltime;
    private String plinfo;
    private String intro;
    private String form;
    private String habit;
    private String plantmehod;
    private String flowerlanguage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getPltype() {
        return pltype;
    }

    public void setPltype(String pltype) {
        this.pltype = pltype;
    }

    public String getPlmode() {
        return plmode;
    }

    public void setPlmode(String plmode) {
        this.plmode = plmode;
    }

    public String getPltime() {
        return pltime;
    }

    public void setPltime(String pltime) {
        this.pltime = pltime;
    }

    public String getPlinfo() {
        return plinfo;
    }

    public void setPlinfo(String plinfo) {
        this.plinfo = plinfo;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getPlantmehod() {
        return plantmehod;
    }

    public void setPlantmehod(String plantmehod) {
        this.plantmehod = plantmehod;
    }

    public String getFlowerlanguage() {
        return flowerlanguage;
    }

    public void setFlowerlanguage(String flowerlanguage) {
        this.flowerlanguage = flowerlanguage;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", othername='" + othername + '\'' +
                ", pltype='" + pltype + '\'' +
                ", plmode='" + plmode + '\'' +
                ", pltime='" + pltime + '\'' +
                ", plinfo='" + plinfo + '\'' +
                ", intro='" + intro + '\'' +
                ", form='" + form + '\'' +
                ", habit='" + habit + '\'' +
                ", plantmehod='" + plantmehod + '\'' +
                ", flowerlanguage='" + flowerlanguage + '\'' +
                '}';
    }
}
