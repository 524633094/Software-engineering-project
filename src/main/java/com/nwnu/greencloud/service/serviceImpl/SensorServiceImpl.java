package com.nwnu.greencloud.service.serviceImpl;

import com.nwnu.greencloud.api.apimodel.SensorDataModel;
import com.nwnu.greencloud.domain.DevEntity;
import com.nwnu.greencloud.domain.VisualizationEntity;
import com.nwnu.greencloud.repository.DevRepository;
import com.nwnu.greencloud.repository.VisualizationRepository;
import com.nwnu.greencloud.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {
    @Autowired
    private VisualizationRepository visualizationRepository;
    @Autowired
    private DevRepository devRepository;
    @Override
    public Boolean saveSensorsData(SensorDataModel sensorDataModel,String apiKey) {
        if(checkApi(sensorDataModel.getDevName(),apiKey)){
            VisualizationEntity visualizationEntity = new VisualizationEntity();
            DevEntity devEntity = devRepository.findByDevName(sensorDataModel.getDevName());
            visualizationEntity.setDevId(devEntity.getId());
            visualizationEntity.setSensorOne(sensorDataModel.getSensorOne());
            visualizationEntity.setSensorTwo(sensorDataModel.getSensorTwo());
            visualizationEntity.setSensorThree(sensorDataModel.getSensorThree());
            visualizationEntity.setSensorFour(sensorDataModel.getSensorFour());
            visualizationEntity.setSensorFive(sensorDataModel.getSensorFive());
            visualizationRepository.flush();
            visualizationRepository.save(visualizationEntity);
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkApi(String devName,String apiKey) {
        DevEntity devEntity = devRepository.findByDevName(devName);
        if (devEntity != null){
            String devApiKey = devEntity.getApiKey();
            if(devApiKey != null && devApiKey.equals(apiKey)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<VisualizationEntity> getSensorDataByDevName(String devName) {
        DevEntity devEntity = devRepository.findByDevName(devName);
        List<VisualizationEntity> dataList = null;
        if(devEntity != null){
            dataList = visualizationRepository.findAllByDevId(devEntity.getId());
        }
        return dataList;
    }
}
