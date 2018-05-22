package com.nwnu.greencloud;

import com.nwnu.greencloud.service.serviceImpl.CheckApiKeyServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class checkApiKeyTest {
    @Autowired
    private CheckApiKeyServiceImpl checkApiKeyService;
    @Test
    public void testcheckApiKeyAnddevName(){
        log.info(checkApiKeyService.checkapiKeyAndDevId("b8c177eca7644f4a8b6d365d1291b262",
               "树莓派1"));
        log.info(checkApiKeyService.checkapiKeyAndDevId("b8c177eca7644f4a8b6d365d1291b262",
                "树莓派3213"));
        log.info(checkApiKeyService.checkapiKeyAndDevId("b8c177eca7644f4a8b6d365d1291ddb262",
                "树莓派3213"));
        log.info(checkApiKeyService.checkapiKeyAndDevId("1935fa43fe6c4f56b63d1fd8c2866e4e",
                "树莓派1"));


    }
}
