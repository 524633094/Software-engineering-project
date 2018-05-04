package com.nwnu.greencloud.domain;
import javax.persistence.*;

@Entity
@Table(name = "dev", schema = "SEproject", catalog = "")
public class DevEntity {
    private String id;
    private String devName;
    private String apiKey;
    private String sensorOne;
    private String sensorTwo;
    private String sensorThree;
    private String sensorFour;
    private String sensorFive;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "dev_name")
    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    @Basic
    @Column(name = "api_key")
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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

        DevEntity devEntity = (DevEntity) o;

        if (id != null ? !id.equals(devEntity.id) : devEntity.id != null) return false;
        if (devName != null ? !devName.equals(devEntity.devName) : devEntity.devName != null) return false;
        if (apiKey != null ? !apiKey.equals(devEntity.apiKey) : devEntity.apiKey != null) return false;
        if (sensorOne != null ? !sensorOne.equals(devEntity.sensorOne) : devEntity.sensorOne != null) return false;
        if (sensorTwo != null ? !sensorTwo.equals(devEntity.sensorTwo) : devEntity.sensorTwo != null) return false;
        if (sensorThree != null ? !sensorThree.equals(devEntity.sensorThree) : devEntity.sensorThree != null)
            return false;
        if (sensorFour != null ? !sensorFour.equals(devEntity.sensorFour) : devEntity.sensorFour != null) return false;
        if (sensorFive != null ? !sensorFive.equals(devEntity.sensorFive) : devEntity.sensorFive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (devName != null ? devName.hashCode() : 0);
        result = 31 * result + (apiKey != null ? apiKey.hashCode() : 0);
        result = 31 * result + (sensorOne != null ? sensorOne.hashCode() : 0);
        result = 31 * result + (sensorTwo != null ? sensorTwo.hashCode() : 0);
        result = 31 * result + (sensorThree != null ? sensorThree.hashCode() : 0);
        result = 31 * result + (sensorFour != null ? sensorFour.hashCode() : 0);
        result = 31 * result + (sensorFive != null ? sensorFive.hashCode() : 0);
        return result;
    }
}
