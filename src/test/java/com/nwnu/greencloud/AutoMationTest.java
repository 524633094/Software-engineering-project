package com.nwnu.greencloud;

import com.nwnu.greencloud.service.AutomationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoMationTest {
    @Autowired
    private AutomationService automationService;

    @Test
    public void addAutoMation(){
        System.out.println(automationService.addAutomationMoudle("b8c177eca7644f4a8b6d365d1291b262",
                "树莓派"));
    }
    @Test
    public void TestWarting(){
        System.out.println(automationService.watering("d3d85744287c4274887396456e719436"));
    }
}
