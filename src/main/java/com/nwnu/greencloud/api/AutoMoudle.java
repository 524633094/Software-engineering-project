package com.nwnu.greencloud.api;

import com.nwnu.greencloud.common.reply.Reply;
import com.nwnu.greencloud.domain.AutomationEntity;
import com.nwnu.greencloud.service.AutomationService;
import com.nwnu.greencloud.service.DevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AutoMoudle {
    @Autowired
    private AutomationService automationService;
    @Autowired
    private DevService devService;

    @GetMapping("/auto/data/{apiKey}/{devName}")
    public Reply getAutoControlData(@PathVariable(value = "apiKey") String apiKey,
                                    @PathVariable(value = "devName") String devName){
        String devId = devService.getDevId(apiKey,devName);
        return new Reply(10009,"获取自动化控制信息",
                automationService.getAutoMationInfo(devId));
    }

    @GetMapping("/auto/water/{apiKey}/{devName}")
    public Reply water(@PathVariable(value = "apiKey") String apiKey,
                                    @PathVariable(value = "devName") String devName){
        String devId = devService.getDevId(apiKey,devName);
        return new Reply(10009,"浇水状态开启以及关闭",
                automationService.watering(devId));
    }

    @GetMapping("/auto/{apiKey}/{devName}")
    public Reply addauto(@PathVariable(value = "apiKey") String apiKey,
                         @PathVariable(value = "devName") String devName){
        return new Reply(10010,"添加自动化模块",automationService.addAutomationMoudle(apiKey,devName));

    }

}
