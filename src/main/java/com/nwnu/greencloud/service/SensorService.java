package com.nwnu.greencloud.service;

import com.nwnu.greencloud.api.apimodel.SensorDataModel;

/**
* @Author zhangqi
* @Desccription: 传感器接口
* @Date: 11:29 2018/5/10
*/
public interface SensorService {
    Boolean saveSensorsData(SensorDataModel sensorDataModel);
}
