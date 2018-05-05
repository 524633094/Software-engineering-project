package com.nwnu.greencloud.service;

/**
* @Author zhangqi
* @Desccription: 用户服务
* @Date: 12:09 2018/5/5
*/
public interface UserService {
    //注册
    Boolean register(String username,String password);
    //登录
    Boolean login(String username,String password);
}
