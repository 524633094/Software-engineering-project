package com.nwnu.greencloud.service.serviceImpl;

import com.nwnu.greencloud.api.Dev;
import com.nwnu.greencloud.domain.DevEntity;
import com.nwnu.greencloud.repository.DevRepository;
import com.nwnu.greencloud.repository.UserRepository;
import com.nwnu.greencloud.service.DevService;
import com.nwnu.greencloud.util.UuidUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class DevServiceImpl implements DevService {
    @Autowired
    private DevRepository devRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean addDev(DevEntity devEntity) {
        String devName = devEntity.getDevName();
        String apiKey = devEntity.getApiKey();
        DevEntity formerDev = devRepository.findByDevNameAndApiKey(devName,devEntity.getApiKey());
        if( formerDev != null){
            return false;
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
    public Boolean updateDev(DevEntity devEntity,DevEntity formerDev) {

        devEntity.setId(formerDev.getId());
        devRepository.save(devEntity);
        return true;
    }


    @Override
    public List<DevEntity> listDev(String apikey) {
        return devRepository.findByApiKey(apikey);
    }

    @Override
    public String getDevId(String apiKey, String devName) {
        return devRepository.findByDevNameAndApiKey(devName,apiKey).getId();
    }

    @Override
    public Boolean hasDev(String apiKey, String devName) {
        if(devRepository.findByDevNameAndApiKey(devName,apiKey) != null){
            return true;
        }
        return false;
    }

    @Transactional
    @Modifying
    @Override
    public Boolean deleteDev(String apikey, String devName) {
        devRepository.deleteByApiKeyAndDevName(apikey,devName);
        return true;
    }
}
