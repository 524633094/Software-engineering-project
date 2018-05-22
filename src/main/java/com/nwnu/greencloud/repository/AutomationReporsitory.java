package com.nwnu.greencloud.repository;

import com.nwnu.greencloud.domain.AutomationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomationReporsitory extends JpaRepository<AutomationEntity,Integer> {
    AutomationEntity findByDevid(String devId);
}
