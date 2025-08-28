package com.example.parametrizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.ResearchGroup;

@Repository
public interface ResearchGroupRepository extends JpaRepository<ResearchGroup, Long> {
    List<ResearchGroup> findByRegionalsId(Long regionalsId);
    List<ResearchGroup> findByCenterId(Long centerId);
}
