package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parametrizacion.model.ResearchGroup;
import com.example.parametrizacion.model.Regionals;
import com.example.parametrizacion.model.Centers;
import com.example.parametrizacion.repository.ResearchGroupRepository;
import com.example.parametrizacion.repository.RegionalsRepository;
import com.example.parametrizacion.repository.CentersRepository;

@Service
public class ResearchGroupService {

    @Autowired
    private ResearchGroupRepository researchGroupRepository;
    
    @Autowired
    private RegionalsRepository regionalsRepository;
    
    @Autowired
    private CentersRepository centersRepository;

    public List<ResearchGroup> getAllResearchGroups() {
        return researchGroupRepository.findAll();
    }

    public Optional<ResearchGroup> getResearchGroupById(String id) {
        return researchGroupRepository.findById(id);
    }

    public List<ResearchGroup> getResearchGroupsByRegional(Long regionalId) {
        return researchGroupRepository.findByRegionalId(regionalId);
    }

    public List<ResearchGroup> getResearchGroupsByCenter(Long centerId) {
        return researchGroupRepository.findByCenterId(centerId);
    }

    public ResearchGroup createResearchGroup(ResearchGroup researchGroup) {
        // Validar que existe el Regional
        if (!regionalsRepository.existsById(researchGroup.getRegional().getId())) {
            throw new RuntimeException("Regional not found");
        }
        
        // Validar que existe el Center
        if (!centersRepository.existsById(researchGroup.getCenter().getId())) {
            throw new RuntimeException("Center not found");
        }
        
        // Validar que el Center pertenece al Regional
        Centers center = centersRepository.findById(researchGroup.getCenter().getId())
                .orElseThrow(() -> new RuntimeException("Center not found"));
        
        if (!center.getRegional().getId().equals(researchGroup.getRegional().getId())) {
            throw new RuntimeException("Center does not belong to the specified Regional");
        }

        return researchGroupRepository.save(researchGroup);
    }

    public ResearchGroup updateResearchGroup(String id, ResearchGroup researchGroup) {
        if (!researchGroupRepository.existsById(id)) {
            throw new RuntimeException("ResearchGroup not found");
        }
        
        // Validaciones iguales al create
        if (!regionalsRepository.existsById(researchGroup.getRegional().getId())) {
            throw new RuntimeException("Regional not found");
        }
        
        if (!centersRepository.existsById(researchGroup.getCenter().getId())) {
            throw new RuntimeException("Center not found");
        }
        
        Centers center = centersRepository.findById(researchGroup.getCenter().getId())
                .orElseThrow(() -> new RuntimeException("Center not found"));
        
        if (!center.getRegional().getId().equals(researchGroup.getRegional().getId())) {
            throw new RuntimeException("Center does not belong to the specified Regional");
        }

        researchGroup.setId(id);
        return researchGroupRepository.save(researchGroup);
    }

    public void deleteResearchGroup(String id) {
        if (!researchGroupRepository.existsById(id)) {
            throw new RuntimeException("ResearchGroup not found");
        }
        researchGroupRepository.deleteById(id);
    }
}