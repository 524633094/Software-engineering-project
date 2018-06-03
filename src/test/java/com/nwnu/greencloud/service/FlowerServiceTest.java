package com.nwnu.greencloud.service;

import com.nwnu.greencloud.repository.FlowerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlowerServiceTest {
   @Autowired
   FlowerService flowerService;
   @Autowired
    FlowerRepository flowerRepository;
    @Test
    public void getFlowerinfoList() {
        System.out.println(flowerService.getFlowerinfoList("玫瑰"));
        System.out.println(flowerService.getFlowerinfoList("玫"));
    }

    @Test
    public void getFlowerinfoEnity() {
        System.out.println(flowerService.getFlowerinfoEnity("玫瑰"));
        System.out.println(flowerService.getFlowerinfoEnity("玫"));
    }
}