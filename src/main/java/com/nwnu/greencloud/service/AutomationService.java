package com.nwnu.greencloud.service;

import com.nwnu.greencloud.domain.AutomationEntity;

/**
* @Author zhangqi
* @Desccription: 自动化控制模块
* @Date: 19:27 2018/5/22
*/
public interface AutomationService {
    //改变浇水状态
    Boolean watering(String devId);
    //查询控制信息
    AutomationEntity getAutoMationInfo(String devId);
    //添加自动化控制模块
    Boolean addAutomationMoudle(String apiKey,String devName);
}
