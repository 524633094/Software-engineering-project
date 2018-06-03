package com.nwnu.greencloud.service;
import com.nwnu.greencloud.domain.DevEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
* @Author zhangqi
* @Desccription: 设备增删改查
* @Date: 11:35 2018/5/10
*/
public interface DevService {
    Boolean addDev(DevEntity devEntity);
    Boolean deleteDev(String devName);
    Boolean updateDev(DevEntity devEntity,DevEntity formerDev);
    List<DevEntity> listDev(String apikey);
    String getDevId(String apiKey,String devName);
    Boolean hasDev(String apiKey,String devName);
    Boolean deleteDev(String apikey,String devName);


}
