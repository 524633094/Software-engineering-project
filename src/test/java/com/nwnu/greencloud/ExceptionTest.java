package com.nwnu.greencloud;

import com.nwnu.greencloud.common.anyexception.AnyException;
import com.nwnu.greencloud.common.constenum.ExceptionEnum;
import com.nwnu.greencloud.repository.FlowerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionTest {
    @Autowired
    private FlowerRepository flowerRepository;
    @Test
    public  void testException(){
        System.out.println(flowerRepository.findByNameLike("%玫"));
    }
}
