package com.nwnu.greencloud.domain;

import javax.persistence.*;
import java.util.Objects;

/**
* @Author zhangqi
* @Desccription: 自动化控制实体
* @Date: 19:24 2018/5/22
*/
@Entity
@Table(name = "automation", schema = "SEproject", catalog = "")
public class AutomationEntity {
    private int id;
    private int watering;
    private int curtain;
    private String devid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "watering")
    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    @Basic
    @Column(name = "curtain")
    public int getCurtain() {
        return curtain;
    }

    public void setCurtain(int curtain) {
        this.curtain = curtain;
    }

    @Basic
    @Column(name = "devid")
    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutomationEntity that = (AutomationEntity) o;
        return id == that.id &&
                watering == that.watering &&
                curtain == that.curtain &&
                Objects.equals(devid, that.devid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, watering, curtain, devid);
    }
}
