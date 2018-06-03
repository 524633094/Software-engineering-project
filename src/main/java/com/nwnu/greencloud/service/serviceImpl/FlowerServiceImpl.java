package com.nwnu.greencloud.service.serviceImpl;
import com.nwnu.greencloud.domain.FlowerEntity;
import com.nwnu.greencloud.repository.FlowerRepository;
import com.nwnu.greencloud.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    private FlowerRepository flowerRepository;

    @Override
    public List<FlowerEntity> getFlowerinfoList(String name) {
        HashSet<FlowerEntity> flowerEntityHashSet = new HashSet<>();
        if (name != null) {
            //不进行模糊查询，直接返回查询结果
            List<FlowerEntity> reallist = flowerRepository.findByNameLike(name);
            if (reallist.size() > 0) {
                return reallist;
            }

            List<FlowerEntity> leftlist = flowerRepository.findByNameLike("%" + name);
            List<FlowerEntity> rightlist = flowerRepository.findByNameLike(name + "%");
            List<FlowerEntity> midlist = flowerRepository.findByNameLike("%" + name + "%");
            flowerEntityHashSet.addAll(rightlist);
            flowerEntityHashSet.addAll(leftlist);
            flowerEntityHashSet.addAll(midlist);
            List<FlowerEntity> thereal = new ArrayList<>();
            thereal.addAll(flowerEntityHashSet);
            return thereal;
        }
        return null;
    }

    @Override
    public FlowerEntity getFlowerinfoEnity(String name) {
        List<FlowerEntity> list = getFlowerinfoList(name);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
