package com.nwnu.greencloud.service;

import com.nwnu.greencloud.domain.FlowerEntity;

import java.util.List;

public interface FlowerService {
    List<FlowerEntity> getFlowerinfoList(String name);
    FlowerEntity getFlowerinfoEnity(String name);
}
