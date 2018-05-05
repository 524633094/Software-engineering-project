package com.nwnu.greencloud;

import com.nwnu.greencloud.common.constenum.UserStateEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class constenumtest {
    @Test
    public  void testUserState(){
        UserStateEnum.ACTIVATE.toString();
        UserStateEnum.UNACTIVATE.toString();
    }
}
