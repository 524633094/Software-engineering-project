package com.nwnu.greencloud.api;

import com.nwnu.greencloud.common.aop.LoggerManage;
import com.nwnu.greencloud.common.reply.Reply;
import com.nwnu.greencloud.domain.UserEntity;
import com.nwnu.greencloud.repository.UserRepository;
import com.nwnu.greencloud.service.UserService;
import com.nwnu.greencloud.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
* @Author zhangqi
* @Desccription: 用户信息api
* @Date: 11:09 2018/5/5
*/
@RestController
@RequestMapping(value = "/api")
public class User {
    @Autowired
    private UserService userService;

    @LoggerManage(description = "注册")
    @PostMapping("/regist")
    public Reply regist(@RequestParam("username")String username,@RequestParam("password")String password){
        Boolean flag = userService.register(username,password);
        if(!flag){
            return new Reply(20000,"账号已注册,如未激活会发送确认邮件");
        }
        return new Reply(10000,"注册成功，请邮件确认");
    }

    @LoggerManage(description = "邮箱认证")
    @GetMapping("/registdouble/{apikey}")
    public Reply regist(@PathVariable(value = "apikey") String apikey){
        Boolean flag = userService.registerDouble(apikey);
        if(!flag){
            return new Reply(20001,"确认邮件失败");
        }
        return new Reply(10001,"确认成功");
    }

    @LoggerManage(description = "登录")
    @PostMapping("/login")
    public Reply login(@RequestParam(value = "username")String username, @RequestParam(value = "password") String password, HttpSession session){
        Boolean flag = userService.login(username,password,session);
        if (!flag){
            return new Reply(20002,"用户名或密码不正确");
        }
        return new Reply(10002,"登录成功");

    }

}
