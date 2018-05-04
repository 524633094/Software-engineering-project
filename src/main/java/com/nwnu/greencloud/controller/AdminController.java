package com.nwnu.greencloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class AdminController {
    @GetMapping("/home")
    public String getHome(){
        return "index";
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
