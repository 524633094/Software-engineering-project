package com.nwnu.greencloud.controller;

import com.nwnu.greencloud.common.aop.LoggerManage;
import com.nwnu.greencloud.domain.UserEntity;
import com.nwnu.greencloud.service.DevService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
* @Author zhangqi
* @Desccription: 页面路由
* @Date: 14:40 2018/5/6
*/
@Log4j2
@Controller
@RequestMapping("/page")
public class AdminController {
    @Autowired
    private DevService devService;
    @LoggerManage(description = "主页面")
    @GetMapping("/home")
    public String getHome(){
        return "greencloud";
    }
    @GetMapping("/widgets.html")
    public String getEquip(Model model, HttpSession httpSession){
        UserEntity userEntity = (UserEntity) httpSession.getAttribute("user");
        if(userEntity != null) {
            model.addAttribute("devlist",devService.listDev(userEntity.getApiKey()));
        }
        log.info(devService.listDev(userEntity.getApiKey()));
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
    public String getIndex3(Model model, HttpSession httpSession){
        UserEntity userEntity = (UserEntity) httpSession.getAttribute("user");
        if(userEntity != null) {
            model.addAttribute("devlist",devService.listDev(userEntity.getApiKey()));
        }
        return "index3";
    }
}
