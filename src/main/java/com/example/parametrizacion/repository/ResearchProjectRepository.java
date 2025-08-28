package com.example.parametrizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.ResearchProject;

@Repository
public interface ResearchProjectRepository extends JpaRepository<ResearchProject, String> {
    List<ResearchProject> findByResearchGroupId(Long researchGroupId);
    List<ResearchProject> findByResearchSeedbedId(Long researchSeedbedId);
    List<ResearchProject> findByResearchLineId(Long researchLineId);
}