package com.nwnu.greencloud.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "flower", schema = "SEproject", catalog = "")
public class FlowerEntity {
    @Override
    public String toString() {
        return "FlowerEntity{" +
                "name='" + name + '\'' +
                ", othername='" + othername + '\'' +
                ", pltype='" + pltype + '\'' ;

    }

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
    private int id;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "othername")
    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    @Basic
    @Column(name = "pltype")
    public String getPltype() {
        return pltype;
    }

    public void setPltype(String pltype) {
        this.pltype = pltype;
    }

    @Basic
    @Column(name = "plmode")
    public String getPlmode() {
        return plmode;
    }

    public void setPlmode(String plmode) {
        this.plmode = plmode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowerEntity that = (FlowerEntity) o;
        return
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Basic
    @Column(name = "pltime")
    public String getPltime() {
        return pltime;
    }

    public void setPltime(String pltime) {
        this.pltime = pltime;
    }

    @Basic
    @Column(name = "plinfo")
    public String getPlinfo() {
        return plinfo;
    }

    public void setPlinfo(String plinfo) {
        this.plinfo = plinfo;
    }

    @Basic
    @Column(name = "intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Basic
    @Column(name = "form")
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Basic
    @Column(name = "habit")
    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    @Basic
    @Column(name = "plantmehod")
    public String getPlantmehod() {
        return plantmehod;
    }

    public void setPlantmehod(String plantmehod) {
        this.plantmehod = plantmehod;
    }

    @Basic
    @Column(name = "flowerlanguage")
    public String getFlowerlanguage() {
        return flowerlanguage;
    }

    public void setFlowerlanguage(String flowerlanguage) {
        this.flowerlanguage = flowerlanguage;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
