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

    @GetMapping(value = "/user/dev/{apikey}")
    public Reply dev(@PathVariable(value = "apikey") String apiKey){

        return new Reply(10010,"获取用户设备信息",devService.listDev(apiKey));
    }

    @RequestMapping(value = "/user/dev/{apikey}/{devname}",method = RequestMethod.DELETE)
    public Reply deleteDev(@PathVariable(value = "apikey") String apiKey,@PathVariable(value = "devname") String devName){
        if (!devService.hasDev(apiKey,devName)){
            return new Reply(20015,"无该设备");
        }
        boolean flag = devService.deleteDev(apiKey,devName);
        return new Reply(10025,"删除设备",flag);
    }
}
