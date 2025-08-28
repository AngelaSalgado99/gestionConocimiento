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

    public Optional<ResearchSeedbed> getResearchSeedbedById(String id) {
        return researchSeedbedRepository.findById(id);
    }

    public List<ResearchSeedbed> getResearchSeedbedsByGroup(String groupId) {
        return researchSeedbedRepository.findByResearchGroupId(groupId);
    }

    public List<ResearchSeedbed> getResearchSeedbedsByRegional(String regionalId) {
        return researchSeedbedRepository.findByRegionalId(regionalId);
    }

    public List<ResearchSeedbed> getResearchSeedbedsByCenter(String centerId) {
        return researchSeedbedRepository.findByCenterId(centerId);
    }

    public ResearchSeedbed createResearchSeedbed(ResearchSeedbed researchSeedbed) {
        validateEntities(researchSeedbed);
        return researchSeedbedRepository.save(researchSeedbed);
    }

    public ResearchSeedbed updateResearchSeedbed(String id, ResearchSeedbed researchSeedbed) {
        if (!researchSeedbedRepository.existsById(id)) {
            throw new RuntimeException("ResearchSeedbed not found");
        }
        validateEntities(researchSeedbed);
        researchSeedbed.setId(id);
        return researchSeedbedRepository.save(researchSeedbed);
    }

    public void deleteResearchSeedbed(String id) {
        if (!researchSeedbedRepository.existsById(id)) {
            throw new RuntimeException("ResearchSeedbed not found");
        }
        researchSeedbedRepository.deleteById(id);
    }

    private void validateEntities(ResearchSeedbed researchSeedbed) {
        // Validar ResearchGroup
        if (researchSeedbed.getResearchGroup() == null ||
            !researchGroupRepository.existsById(researchSeedbed.getResearchGroup().getId())) {
            throw new RuntimeException("ResearchGroup not found");
        }

        // Validar Regional
        if (researchSeedbed.getRegional() == null ||
            !regionalsRepository.existsById(researchSeedbed.getRegional().getId())) {
            throw new RuntimeException("Regional not found");
        }

        // Validar Center
        if (researchSeedbed.getCenter() == null ||
            !centersRepository.existsById(researchSeedbed.getCenter().getId())) {
            throw new RuntimeException("Center not found");
        }

        // Validar consistencia: que el Group pertenezca al Regional y Center especificados
        ResearchGroup group = researchGroupRepository.findById(researchSeedbed.getResearchGroup().getId())
                .orElseThrow(() -> new RuntimeException("ResearchGroup not found"));

        if (!group.getRegional().getId().equals(researchSeedbed.getRegional().getId()) ||
            !group.getCenter().getId().equals(researchSeedbed.getCenter().getId())) {
            throw new RuntimeException("ResearchGroup does not belong to the specified Regional/Center");
        }
    }
}
