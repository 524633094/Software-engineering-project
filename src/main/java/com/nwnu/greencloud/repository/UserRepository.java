package com.nwnu.greencloud.repository;

import com.nwnu.greencloud.domain.UserEntity;
import javafx.scene.chart.ValueAxis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
    UserEntity findByUsername(@Param(value = "username")String username);
    UserEntity findByApiKey(@Param(value = "apikey")String apiKey);
    UserEntity findByUsernameAndPassword(String username,String password);
}
