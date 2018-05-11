package com.nwnu.greencloud.service.serviceImpl;

import com.nwnu.greencloud.domain.DevEntity;
import com.nwnu.greencloud.repository.DevRepository;
import com.nwnu.greencloud.repository.UserRepository;
import com.nwnu.greencloud.service.DevService;
import com.nwnu.greencloud.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DevServiceImpl implements DevService {
    @Autowired
    private DevRepository devRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean addDev(DevEntity devEntity) {
        String devName = devEntity.getDevName();
        if(devRepository.findByDevName(devName) != null){
            updateDev(devEntity);
        }
        devEntity.setId(UuidUtil.generateUuid());
        devRepository.save(devEntity);
        return true;
    }

    @Override
    public Boolean deleteDev(String devName) {
        return null;
    }

    @Override
    public Boolean updateDev(DevEntity devEntity) {

        return null;
    }

    @Override
    public List<DevEntity> listDev(String username) {
        return null;
    }
}
