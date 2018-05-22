package com.nwnu.greencloud.service.serviceImpl;

import com.nwnu.greencloud.domain.AutomationEntity;
import com.nwnu.greencloud.domain.DevEntity;
import com.nwnu.greencloud.repository.AutomationReporsitory;
import com.nwnu.greencloud.repository.DevRepository;
import com.nwnu.greencloud.service.AutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomationServicelmpl implements AutomationService {
    /**
     * 模块开启关闭信息
     */
    private  static  final int CLOSE = 0;
    private  static final int OPEN = 1;
    @Autowired
    private AutomationReporsitory automationReporsitory;
    @Autowired
    private CheckApiKeyServiceImpl checkApiKeyService;
    @Autowired
    private DevRepository devRepository;
    @Override
    public Boolean watering(String devId) {
        AutomationEntity automationEntity = automationReporsitory.findByDevid(devId);
        if(automationEntity == null){
            return  false;
        }
        if(automationEntity.getWatering() != CLOSE) {
            automationEntity.setWatering(CLOSE);
        }else {
            automationEntity.setWatering(OPEN);
        }
        automationReporsitory.save(automationEntity);
        return true;
    }

    @Override
    public AutomationEntity getAutoMationInfo(String devId) {
        return automationReporsitory.findByDevid(devId);
    }

    @Override
    public Boolean addAutomationMoudle(String apiKey,String devName) {
        if(checkApiKeyService.checkapiKeyAndDevId(apiKey,devName) == false){
            return  false;
        }
        //如果该设备已有控制模块
        String devId = devRepository.findByDevNameAndApiKey(devName,apiKey).getId();
        if (automationReporsitory.findByDevid(devId) != null){
            return false;
        }
        DevEntity devEntity = devRepository.findByDevNameAndApiKey(devName,apiKey);
        AutomationEntity automationEntity = new AutomationEntity();
        automationEntity.setWatering(CLOSE);
        automationEntity.setCurtain(CLOSE);
        automationEntity.setDevid(devEntity.getId());
        automationReporsitory.save(automationEntity);
        return true;
    }
}
