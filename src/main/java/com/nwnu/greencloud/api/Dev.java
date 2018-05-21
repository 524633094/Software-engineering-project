package com.nwnu.greencloud.api;

import com.nwnu.greencloud.common.aop.LoggerManage;
import com.nwnu.greencloud.common.reply.Reply;
import com.nwnu.greencloud.domain.DevEntity;
import com.nwnu.greencloud.service.serviceImpl.DevServiceImpl;
import com.nwnu.greencloud.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class Dev {
    @Autowired
    private  DevServiceImpl devService;
    @Autowired
    private UserServiceImpl userService;
    @LoggerManage(description = "用户添加设备")
    @PostMapping(value = "/dev/{apikey}")
    public Reply addDev(@PathVariable(value = "apikey") String apikey, @RequestBody DevEntity devEntity, BindingResult bindingResult){
        devEntity.setApiKey(apikey);
        if(!userService.checkUserApiKey(apikey)){
            return new Reply(20007,"apikey不匹配");
        }
        if(devService.addDev(devEntity) == true){
            return new Reply(10008,"添加设备成功");
        }

        return new Reply(20008,"添加设备失败,设备名重复");
    }
}
