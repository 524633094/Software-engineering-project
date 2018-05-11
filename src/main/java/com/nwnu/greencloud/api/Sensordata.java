package com.nwnu.greencloud.api;
import com.nwnu.greencloud.api.apimodel.SensorDataModel;
import com.nwnu.greencloud.common.reply.Reply;
import lombok.extern.log4j.Log4j2;
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
    @PostMapping(value = "/tensor/{apikey}")
    public Reply postTensorData(@PathVariable(value = "apikey") String apikey, @RequestBody SensorDataModel tensorDataModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new Reply(20005,"参数错误");
        }
        log.info(tensorDataModel.toString());
        return new Reply(10005,"成功传输");
    }
    @PostMapping(value = "/tensor")
    public Reply postTensorHeader(HttpServletRequest httpServletRequest){
            log.info(httpServletRequest.getRequestURL());
            log.info(httpServletRequest.getCookies().length);
            log.info(httpServletRequest.getRequestURI());
        return new Reply(10005,"成功传输");
    }


}
