package com.nwnu.greencloud.repository;

import com.nwnu.greencloud.domain.VisualizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisualizationRepository extends JpaRepository<VisualizationEntity,String> {
        List<VisualizationEntity> findAllByDevId(String id);
}
