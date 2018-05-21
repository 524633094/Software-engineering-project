package com.nwnu.greencloud.api;
import com.nwnu.greencloud.api.apimodel.SensorDataModel;
import com.nwnu.greencloud.common.aop.LoggerManage;
import com.nwnu.greencloud.common.reply.Reply;
import com.nwnu.greencloud.service.SensorService;
import com.nwnu.greencloud.service.UserService;
import com.nwnu.greencloud.service.serviceImpl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
* @Author zhangqi
* @Desccription: 传感器传输数
* @Date: 10:50 2018/5/10
*/
@Log4j2
@RestController
@RequestMapping(value = "/api")
public class Sensordata {
    @Autowired
    private SensorService sensorService;
    @Autowired
    private UserService userService;
    @LoggerManage(description = "用户传入数据")
    @PostMapping(value = "/sensor/{apikey}")
    public Reply postTensorData(@PathVariable(value = "apikey") String apikey, @RequestBody SensorDataModel tensorDataModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new Reply(20005,"参数错误");
        }
        log.info(tensorDataModel.toString());
        if(!sensorService.saveSensorsData(tensorDataModel,apikey)){
            return new Reply(20009,"apikey不匹配");
        }
        return new Reply(10005,"成功传输");
    }

    @PostMapping(value = "/sensor")
    public Reply postTensorHeader(HttpServletRequest httpServletRequest){
//            log.info(httpServletRequest.getRequestURL());
//            log.info(httpServletRequest.getCookies().length);
//            log.info(httpServletRequest.getRequestURI());
        return new Reply(10005,"成功传输");
    }

    @GetMapping(value = "/data/{apikey}/{devname}")
    public Reply getSensorData(@PathVariable(value = "apikey") String apikey,
                               @PathVariable(value = "devname") String devname){
        if(userService.checkUserApiKey(apikey) == false){
            return new Reply(20009,"apikey不匹配");
        }
        return new Reply(10006,"传输成功",sensorService.getSensorDataByDevName(devname));

    }


}
