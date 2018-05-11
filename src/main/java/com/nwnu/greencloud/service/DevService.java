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
    Boolean updateDev(DevEntity devEntity);
    List<DevEntity> listDev(String username);


}
