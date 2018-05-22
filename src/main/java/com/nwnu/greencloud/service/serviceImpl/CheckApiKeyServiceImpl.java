package com.nwnu.greencloud.service.serviceImpl;

import com.nwnu.greencloud.api.User;
import com.nwnu.greencloud.domain.DevEntity;
import com.nwnu.greencloud.domain.UserEntity;
import com.nwnu.greencloud.repository.DevRepository;
import com.nwnu.greencloud.repository.UserRepository;
import com.nwnu.greencloud.service.CheckapiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckApiKeyServiceImpl implements CheckapiKeyService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DevRepository devRepository;

    @Override
    public Boolean checkapiKeyAndDevId(String apiKey, String devName) {
        UserEntity user  = userRepository.findByApiKey(apiKey);
        DevEntity devEntity = devRepository.findByDevNameAndApiKey(devName,apiKey);
        if(user == null || devEntity == null){
            return false;
        }
        return true;
    }
}
