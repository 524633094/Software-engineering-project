package com.nwnu.greencloud.service;

/**
* @Author zhangqi
* @Desccription: 检查api是否正确
* @Date: 19:45 2018/5/22
*/
public interface CheckapiKeyService {
    //检查api+设备id
    Boolean checkapiKeyAndDevId(String apiKey,String devName);
}
