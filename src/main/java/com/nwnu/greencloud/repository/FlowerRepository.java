package com.nwnu.greencloud.repository;

import com.nwnu.greencloud.domain.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerRepository extends JpaRepository<FlowerEntity,Integer> {
    List<FlowerEntity> findByNameLike(@Param("name")String name);
}
