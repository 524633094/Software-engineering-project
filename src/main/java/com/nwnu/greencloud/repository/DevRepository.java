package com.nwnu.greencloud.repository;

import com.nwnu.greencloud.domain.DevEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DevRepository extends JpaRepository<DevEntity,String> {
    DevEntity findByDevName(@Param("devname") String devname);
}
