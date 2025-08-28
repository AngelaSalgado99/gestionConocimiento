
package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parametrizacion.model.Regionals;
import com.example.parametrizacion.repository.RegionalsRepository;

@Service
public class RegionalsService {

    @Autowired
    private RegionalsRepository regionalsRepository;

    public boolean existsByName(String name) {
        return regionalsRepository.existsByName(name);
    }

    public List<Regionals> getAll() {
        return regionalsRepository.findAll();
    }

    public Regionals getById(Long id) {
        return regionalsRepository.findById(id).orElse(null);
    }

    public Regionals save(Regionals regionals) {
        return regionalsRepository.save(regionals);
    }

    public Regionals update(Long id, Regionals newData) {
    Optional<Regionals> optional = regionalsRepository.findById(id);
    if (optional.isPresent()) {
        Regionals existing = optional.get();
        existing.setName(newData.getName());
        existing.setDescription(newData.getDescription());
        return regionalsRepository.save(existing);
    }
        return null;
    }

    public boolean delete(Long regionalsId) {
        if (regionalsRepository.existsById(regionalsId)) {
        regionalsRepository.deleteById(regionalsId);
            return true;
        } else {
            return false;
        }
    }
}
