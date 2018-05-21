package com.nwnu.greencloud.service;

import com.nwnu.greencloud.api.apimodel.SensorDataModel;
import com.nwnu.greencloud.domain.VisualizationEntity;

import java.util.List;

/**
* @Author zhangqi
* @Desccription: 传感器接口
* @Date: 11:29 2018/5/10
*/
public interface SensorService {
    Boolean saveSensorsData(SensorDataModel sensorDataModel,String apiKey);
    Boolean checkApi(String devName,String apiKey);
    List<VisualizationEntity> getSensorDataByDevName(String devName);
}
