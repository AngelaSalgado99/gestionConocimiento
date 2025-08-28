package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parametrizacion.model.ResearchProject;
import com.example.parametrizacion.model.ResearchSeedbed;
import com.example.parametrizacion.repository.ResearchProjectRepository;
import com.example.parametrizacion.repository.ResearchGroupRepository;
import com.example.parametrizacion.repository.ResearchSeedbedRepository;
import com.example.parametrizacion.repository.ResearchLinesRepository;

@Service
public class ResearchProjectService {

    @Autowired
    private ResearchProjectRepository researchProjectRepository;
    
    @Autowired
    private ResearchGroupRepository researchGroupRepository;
    
    @Autowired
    private ResearchSeedbedRepository researchSeedbedRepository;
    
    @Autowired
    private ResearchLinesRepository researchLinesRepository;

    public List<ResearchProject> getAllResearchProjects() {
        return researchProjectRepository.findAll();
    }

    public Optional<ResearchProject> getResearchProjectById(Long id) {
        return researchProjectRepository.findById(id);
    }

    public List<ResearchProject> getResearchProjectsByGroup(Long groupId) {
        return researchProjectRepository.findByResearchGroupId(groupId);
    }

    public List<ResearchProject> getResearchProjectsBySeedbed(Long seedbedId) {
        return researchProjectRepository.findByResearchSeedbedId(seedbedId);
    }

    public List<ResearchProject> getResearchProjectsByResearchLine(Long researchLineId) {
        return researchProjectRepository.findByResearchLineId(researchLineId);
    }

    public ResearchProject createResearchProject(ResearchProject researchProject) {
        // Validar que existe la ResearchLine
        if (!researchLinesRepository.existsById(researchProject.getResearchLine().getId())) {
            throw new RuntimeException("ResearchLine not found");
        }
        
        // Validar ResearchGroup (opcional pero si viene debe existir)
        if (researchProject.getResearchGroup() != null && 
            !researchGroupRepository.existsById(researchProject.getResearchGroup().getId())) {
            throw new RuntimeException("ResearchGroup not found");
        }
        
        // Validar ResearchSeedbed (opcional pero si viene debe existir)
        if (researchProject.getResearchSeedbed() != null && 
            !researchSeedbedRepository.existsById(researchProject.getResearchSeedbed().getId())) {
            throw new RuntimeException("ResearchSeedbed not found");
        }
        
        // Validar consistencia: si hay seedbed, debe pertenecer al group
        if (researchProject.getResearchSeedbed() != null && researchProject.getResearchGroup() != null) {
            ResearchSeedbed seedbed = researchSeedbedRepository.findById(researchProject.getResearchSeedbed().getId())
                    .orElseThrow(() -> new RuntimeException("ResearchSeedbed not found"));
            
            if (!seedbed.getResearchGroup().getId().equals(researchProject.getResearchGroup().getId())) {
                throw new RuntimeException("ResearchSeedbed does not belong to the specified ResearchGroup");
            }
        }

        return researchProjectRepository.save(researchProject);
    }

    public ResearchProject updateResearchProject(Long id, ResearchProject researchProject) {
        if (!researchProjectRepository.existsById(id)) {
            throw new RuntimeException("ResearchProject not found");
        }
        
        // Mismas validaciones del create
        if (!researchLinesRepository.existsById(researchProject.getResearchLine().getId())) {
            throw new RuntimeException("ResearchLine not found");
        }
        
        if (researchProject.getResearchGroup() != null && 
            !researchGroupRepository.existsById(researchProject.getResearchGroup().getId())) {
            throw new RuntimeException("ResearchGroup not found");
        }
        
        if (researchProject.getResearchSeedbed() != null && 
            !researchSeedbedRepository.existsById(researchProject.getResearchSeedbed().getId())) {
            throw new RuntimeException("ResearchSeedbed not found");
        }
        
        if (researchProject.getResearchSeedbed() != null && researchProject.getResearchGroup() != null) {
            ResearchSeedbed seedbed = researchSeedbedRepository.findById(researchProject.getResearchSeedbed().getId())
                    .orElseThrow(() -> new RuntimeException("ResearchSeedbed not found"));
            
            if (!seedbed.getResearchGroup().getId().equals(researchProject.getResearchGroup().getId())) {
                throw new RuntimeException("ResearchSeedbed does not belong to the specified ResearchGroup");
            }
        }

        researchProject.setId(id);
        return researchProjectRepository.save(researchProject);
    }

    public void deleteResearchProject(Long id) {
        if (!researchProjectRepository.existsById(id)) {
            throw new RuntimeException("ResearchProject not found");
        }
        researchProjectRepository.deleteById(id);
    }
}
