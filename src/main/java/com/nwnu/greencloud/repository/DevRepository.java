package com.nwnu.greencloud.repository;

import com.nwnu.greencloud.domain.DevEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevRepository extends JpaRepository<DevEntity,String> {
    DevEntity findByDevName(@Param("devname") String devname);
    DevEntity findByDevNameAndApiKey(@Param("devname") String devname,@Param("apikey") String apikey);
    List<DevEntity> findByApiKey(@Param("apikey")String apikey);
}
