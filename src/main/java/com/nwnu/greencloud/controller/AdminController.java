package com.nwnu.greencloud.controller;

import com.nwnu.greencloud.common.aop.LoggerManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/index.html")
    public String getIndex1(){
        return "index";
    }
    @GetMapping("/index2.html")
    public String getIndex2(){
        return "index2";
    }
    @GetMapping("/index3.html")
    public String getIndex3(){
        return "index3";
    }
}
