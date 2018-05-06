package com.nwnu.greencloud.controller;

import com.nwnu.greencloud.common.aop.LoggerManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @Author zhangqi
* @Desccription: 页面路由
* @Date: 14:40 2018/5/6
*/
@Controller
@RequestMapping("/page")
public class AdminController {
    @LoggerManage(description = "主页面")
    @GetMapping("/home")
    public String getHome(){
        return "index3";
    }
    @GetMapping("/widgets.html")
    public String getEquip(){
        return "widgets";
    }
    @GetMapping("/chartjs.html")
    public String getChart(){
        return "chartjs";
    }
    @GetMapping("/inline.html")
    public String getMap(){
        return "inline";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "index";
    }
    @GetMapping("/reg.html")
    public String getReg(){
        return "reg";
    }
    @GetMapping("/index.html")
    public String getIndex(){
        return "index";
    }
    @GetMapping("/getpass.html")
    public String getPass(){
        return "getpass";
    }
    @GetMapping("/index3.html")
    public String getIndex3(){
        return "index3";
    }
}
