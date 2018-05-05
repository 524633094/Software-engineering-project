package com.nwnu.greencloud.service;
/**
* @Author zhangqi
* @Desccription: 邮件服务
* @Date: 11:37 2018/5/5
*/
public interface EmailService {
    //发送注册邮件
    Boolean sendRegistEmail(String receiver);
}
