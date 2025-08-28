package com.example.parametrizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.ResearchSeedbed;

@Repository
public interface ResearchSeedbedRepository extends JpaRepository<ResearchSeedbed, Long> {
    List<ResearchSeedbed> findByResearchGroupId(Long researchGroupId);
    List<ResearchSeedbed> findByRegionalsId(Long regionalsId);
    List<ResearchSeedbed> findByCentersId(Long centersId);
}