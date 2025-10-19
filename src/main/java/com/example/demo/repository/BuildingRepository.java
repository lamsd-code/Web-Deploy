package com.example.demo.repository;


import com.example.demo.entity.Building;
import com.example.demo.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long>, BuildingRepositoryCustom {
    void deleteAllByIdIn(List<Long> buildingIds);
    
}
