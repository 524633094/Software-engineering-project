package com.nwnu.greencloud.api.apimodel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class SensorDataModel {
    private String devName;
    private String sensorOne;
    private String sensorTwo;
    private String sensorThree;
    private String sensorFour;
    private String sensorFive;

}
