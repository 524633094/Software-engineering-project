package com.nwnu.greencloud.domain;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "visualization", schema = "SEproject", catalog = "")
@ToString
public class VisualizationEntity {
    private String devId;
    private String sensorOne;
    private String sensorTwo;
    private String sensorThree;
    private String sensorFour;
    private String sensorFive;
    private int id;

    @Basic
    @Column(name = "dev_id")
    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    @Basic
    @Column(name = "sensor_one")
    public String getSensorOne() {
        return sensorOne;
    }

    public void setSensorOne(String sensorOne) {
        this.sensorOne = sensorOne;
    }

    @Basic
    @Column(name = "sensor_two")
    public String getSensorTwo() {
        return sensorTwo;
    }

    public void setSensorTwo(String sensorTwo) {
        this.sensorTwo = sensorTwo;
    }

    @Basic
    @Column(name = "sensor_three")
    public String getSensorThree() {
        return sensorThree;
    }

    public void setSensorThree(String sensorThree) {
        this.sensorThree = sensorThree;
    }

    @Basic
    @Column(name = "sensor_four")
    public String getSensorFour() {
        return sensorFour;
    }

    public void setSensorFour(String sensorFour) {
        this.sensorFour = sensorFour;
    }

    @Basic
    @Column(name = "sensor_five")
    public String getSensorFive() {
        return sensorFive;
    }

    public void setSensorFive(String sensorFive) {
        this.sensorFive = sensorFive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisualizationEntity that = (VisualizationEntity) o;

        if (devId != null ? !devId.equals(that.devId) : that.devId != null) return false;
        if (sensorOne != null ? !sensorOne.equals(that.sensorOne) : that.sensorOne != null) return false;
        if (sensorTwo != null ? !sensorTwo.equals(that.sensorTwo) : that.sensorTwo != null) return false;
        if (sensorThree != null ? !sensorThree.equals(that.sensorThree) : that.sensorThree != null) return false;
        if (sensorFour != null ? !sensorFour.equals(that.sensorFour) : that.sensorFour != null) return false;
        if (sensorFive != null ? !sensorFive.equals(that.sensorFive) : that.sensorFive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = devId != null ? devId.hashCode() : 0;
        result = 31 * result + (sensorOne != null ? sensorOne.hashCode() : 0);
        result = 31 * result + (sensorTwo != null ? sensorTwo.hashCode() : 0);
        result = 31 * result + (sensorThree != null ? sensorThree.hashCode() : 0);
        result = 31 * result + (sensorFour != null ? sensorFour.hashCode() : 0);
        result = 31 * result + (sensorFive != null ? sensorFive.hashCode() : 0);
        return result;
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
