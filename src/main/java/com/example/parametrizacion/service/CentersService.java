
package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parametrizacion.model.Centers;
import com.example.parametrizacion.model.Regionals;
import com.example.parametrizacion.repository.CentersRepository;
import com.example.parametrizacion.repository.RegionalsRepository;

@Service
public class CentersService {

    @Autowired
    private CentersRepository centersRepository;

    @Autowired
    private RegionalsRepository regionalsRepository;

    //Para el global
    public List<Centers> getAll() {
        return centersRepository.findAll();
    }
    public Centers getById(Long id) {
        return centersRepository.findById(id).orElse(null);
    }


    public boolean existsByName(String name) {
        return centersRepository.existsByName(name);
    }

    public List<Centers> getAllByRegionals(Long regionalsId) {
        return centersRepository.findByRegionalsId(regionalsId);
    }

    public Centers getByIdAndRegionalsId(Long id, Long regionalsId) {
    return centersRepository.findByIdAndRegionalsId(id, regionalsId).orElse(null);
    }

    public Centers save(Long regionalsId, Centers centers) {
        Optional<Regionals> regionals = regionalsRepository.findById(regionalsId);
        if (regionals.isPresent()) {
            centers.setRegionals(regionals.get());
            return centersRepository.save(centers);
        } else {
            throw new IllegalArgumentException("Regional not found with ID " + regionalsId);
        }
    }

    //Update Center global
    public Centers updateCenterGlobal(Long id, Centers updateData) {
    Optional<Centers> optional = centersRepository.findById(id);
    if (optional.isPresent()) {
        Centers existing = optional.get();
        existing.setName(updateData.getName());
        existing.setCode(updateData.getCode());
        existing.setAddress(updateData.getAddress());
        existing.setAbbreviation(updateData.getAbbreviation());
        return centersRepository.save(existing);
    }
        return null;
    }

    public Centers update(Long id, Long regionalsId, Centers newData) {
    Optional<Centers> optionalCenter = centersRepository.findByIdAndRegionalsId(id, regionalsId);
        if (optionalCenter.isPresent()) {
            Centers existing = optionalCenter.get();
            existing.setName(newData.getName());
            existing.setCode(newData.getCode());
            existing.setAddress(newData.getAddress());
            existing.setAbbreviation(newData.getAbbreviation());
            return centersRepository.save(existing);
        }
        return null;
    }

    public boolean delete(Long id, Long regionalsId) {
    Optional<Centers> optionalCenter = centersRepository.findByIdAndRegionalsId(id, regionalsId);
    if (optionalCenter.isPresent()) {
        centersRepository.deleteById(id);
        return true;
    }
    return false;
    }
}