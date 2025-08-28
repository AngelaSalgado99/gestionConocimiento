package com.example.parametrizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.ResearchSeedbed;

@Repository
public interface ResearchSeedbedRepository extends JpaRepository<ResearchSeedbed, String> {
    List<ResearchSeedbed> findByResearchGroupId(String researchGroupId);
    List<ResearchSeedbed> findByRegionalId(String regionalId);
    List<ResearchSeedbed> findByCenterId(String centerId);
}
