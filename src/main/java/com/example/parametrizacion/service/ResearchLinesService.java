// src/main/java/com/example/parametrizacion/service/RolService.java
package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parametrizacion.model.Centers;
import com.example.parametrizacion.model.ResearchLines;
import com.example.parametrizacion.repository.CentersRepository;
import com.example.parametrizacion.repository.ResearchLinesRepository;

@Service
public class ResearchLinesService {

    @Autowired
    private ResearchLinesRepository researchLinesRepository;

    @Autowired
    private CentersRepository centersRepository;

    //Para el global
    public List<ResearchLines> getAll() {
        return researchLinesRepository.findAll();
    }
    public ResearchLines getById(Long id) {
        return researchLinesRepository.findById(id).orElse(null);
    }


    public boolean existsByName(String name) {
        return researchLinesRepository.existsByName(name);
    }

    public List<ResearchLines> getAllByCenters(Long centersId) {
        return researchLinesRepository.findByCentersId(centersId);
    }

    public ResearchLines getByIdAndCentersId(Long id, Long centersId) {
    return researchLinesRepository.findByIdAndCentersId(id, centersId).orElse(null);
    }

    public ResearchLines save(Long centersId, ResearchLines researchLines) {
        Optional<Centers> centers = centersRepository.findById(centersId);
        if (centers.isPresent()) {
            researchLines.setCenters(centers.get());
            return researchLinesRepository.save(researchLines);
        } else {
            throw new IllegalArgumentException("Center not found with ID " + centersId);
        }
    }

    //Update Research Lines global
    public ResearchLines updateResearchLinesGlobal(Long id, ResearchLines updateData) {
    Optional<ResearchLines> optional = researchLinesRepository.findById(id);
    if (optional.isPresent()) {
        ResearchLines existing = optional.get();
        existing.setName(updateData.getName());
        existing.setDescription(updateData.getDescription());
        return researchLinesRepository.save(existing);
    }
        return null;
    }

    public ResearchLines update(Long id, Long centersId, ResearchLines newData) {
    Optional<ResearchLines> optionalResearchLines = researchLinesRepository.findByIdAndCentersId(id, centersId);
        if (optionalResearchLines.isPresent()) {
            ResearchLines existing = optionalResearchLines.get();
            existing.setName(newData.getName());
            existing.setDescription(newData.getDescription());
            return researchLinesRepository.save(existing);
        }
        return null;
    }

    //Delete global
    public boolean deleteGlobal(Long id) {
    Optional<ResearchLines> optional = researchLinesRepository.findById(id);
    if (optional.isPresent()) {
        researchLinesRepository.deleteById(id);
        return true;
    }
    return false;
}

    public boolean delete(Long id, Long centersId) {
    Optional<ResearchLines> optionalResearchLines = researchLinesRepository.findByIdAndCentersId(id, centersId);
    if (optionalResearchLines.isPresent()) {
        researchLinesRepository.deleteById(id);
        return true;
    }
    return false;
    }
}