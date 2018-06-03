package com.nwnu.greencloud.api;

import com.nwnu.greencloud.common.reply.Reply;
import com.nwnu.greencloud.domain.FlowerEntity;
import com.nwnu.greencloud.service.FlowerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api")
/**
* @Author zhangqi
* @Desccription: 花卉信息
* @Date: 22:37 2018/6/3
*/
public class Flower {
    @Autowired
    private FlowerService flowerService;

    @GetMapping("/flowers/{name}")
    public Reply flowerList(@PathVariable(value = "name") String name){
        return new Reply(10020,"查询花卉列表成功",flowerService.getFlowerinfoList(name));
    }

    @GetMapping("/flower/{name}")
    public Reply flowerinfo(@PathVariable(value = "name")String name){
        return new Reply(10021,"查询花卉成功",flowerService.getFlowerinfoEnity(name));
    }
}
