package com.nwnu.greencloud.api;

import com.nwnu.greencloud.common.reply.Reply;
import com.nwnu.greencloud.domain.DevEntity;
import com.nwnu.greencloud.service.serviceImpl.DevServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class Dev {
    @Autowired
    private  DevServiceImpl devService;
    @PostMapping(value = "/dev/{apikey}")
    public Reply addDev(@PathVariable(value = "apikey") String apikey, @RequestBody DevEntity devEntity, BindingResult bindingResult){
        devEntity.setApiKey(apikey);
        if(devService.addDev(devEntity) == true){
            return new Reply(10008,"添加设备成功");
        }

        return new Reply(20008,"添加设备失败");
    }
}
