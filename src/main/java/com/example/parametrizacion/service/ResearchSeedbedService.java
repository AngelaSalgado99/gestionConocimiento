package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parametrizacion.model.ResearchSeedbed;
import com.example.parametrizacion.model.ResearchGroup;
import com.example.parametrizacion.repository.ResearchSeedbedRepository;
import com.example.parametrizacion.repository.ResearchGroupRepository;
import com.example.parametrizacion.repository.RegionalsRepository;
import com.example.parametrizacion.repository.CentersRepository;

@Service
public class ResearchSeedbedService {

    @Autowired
    private ResearchSeedbedRepository researchSeedbedRepository;
    
    @Autowired
    private ResearchGroupRepository researchGroupRepository;
    
    @Autowired
    private RegionalsRepository regionalsRepository;
    
    @Autowired
    private CentersRepository centersRepository;

    public List<ResearchSeedbed> getAllResearchSeedbeds() {
        return researchSeedbedRepository.findAll();
    }

    public Optional<ResearchSeedbed> getResearchSeedbedById(Long id) {
        return researchSeedbedRepository.findById(id);
    }

    public List<ResearchSeedbed> getResearchSeedbedsByGroup(Long groupId) {
        return researchSeedbedRepository.findByResearchGroupId(groupId);
    }

    public List<ResearchSeedbed> getResearchSeedbedsByRegional(Long regionalId) {
        return researchSeedbedRepository.findByRegionalsId(regionalId);  // CAMBIO: era findByRegionalId
    }

    public ResearchSeedbed createResearchSeedbed(ResearchSeedbed researchSeedbed) {
        // Validar que existe el ResearchGroup
        if (researchSeedbed.getResearchGroup() == null ||
            !researchGroupRepository.existsById(researchSeedbed.getResearchGroup().getId())) {
            throw new RuntimeException("ResearchGroup not found");
        }
        
        // Validar que existe el Regional
        if (researchSeedbed.getRegionals() == null ||  // CAMBIO: era getRegional()
            !regionalsRepository.existsById(researchSeedbed.getRegionals().getId())) {
            throw new RuntimeException("Regional not found");
        }
        
        // Validar que existe el Center
        if (researchSeedbed.getCenters() == null ||  // CAMBIO: era getCenter()
            !centersRepository.existsById(researchSeedbed.getCenters().getId())) {
            throw new RuntimeException("Center not found");
        }
        
        // Validar consistencia: que el Group pertenezca al Regional y Center especificados
        ResearchGroup group = researchGroupRepository.findById(researchSeedbed.getResearchGroup().getId())
                .orElseThrow(() -> new RuntimeException("ResearchGroup not found"));
        
        if (!group.getRegionals().getId().equals(researchSeedbed.getRegionals().getId()) ||  // CAMBIO: era getRegional()
            !group.getCenter().getId().equals(researchSeedbed.getCenters().getId())) {  // CAMBIO: era getCenter()
            throw new RuntimeException("ResearchGroup does not belong to the specified Regional/Center");
        }

        return researchSeedbedRepository.save(researchSeedbed);
    }

    public ResearchSeedbed updateResearchSeedbed(Long id, ResearchSeedbed researchSeedbed) {
        if (!researchSeedbedRepository.existsById(id)) {
            throw new RuntimeException("ResearchSeedbed not found");
        }
        
        // Validaciones iguales al create
        if (researchSeedbed.getResearchGroup() == null ||
            !researchGroupRepository.existsById(researchSeedbed.getResearchGroup().getId())) {
            throw new RuntimeException("ResearchGroup not found");
        }
        
        if (researchSeedbed.getRegionals() == null ||  // CAMBIO: era getRegional()
            !regionalsRepository.existsById(researchSeedbed.getRegionals().getId())) {
            throw new RuntimeException("Regional not found");
        }
        
        if (researchSeedbed.getCenters() == null ||  // CAMBIO: era getCenter()
            !centersRepository.existsById(researchSeedbed.getCenters().getId())) {
            throw new RuntimeException("Center not found");
        }
        
        ResearchGroup group = researchGroupRepository.findById(researchSeedbed.getResearchGroup().getId())
                .orElseThrow(() -> new RuntimeException("ResearchGroup not found"));
        
        if (!group.getRegionals().getId().equals(researchSeedbed.getRegionals().getId()) ||  // CAMBIO: era getRegional()
            !group.getCenter().getId().equals(researchSeedbed.getCenters().getId())) {  // CAMBIO: era getCenter()
            throw new RuntimeException("ResearchGroup does not belong to the specified Regional/Center");
        }

        researchSeedbed.setId(id);
        return researchSeedbedRepository.save(researchSeedbed);
    }

    public void deleteResearchSeedbed(Long id) {
        if (!researchSeedbedRepository.existsById(id)) {
            throw new RuntimeException("ResearchSeedbed not found");
        }
        researchSeedbedRepository.deleteById(id);
    }
}