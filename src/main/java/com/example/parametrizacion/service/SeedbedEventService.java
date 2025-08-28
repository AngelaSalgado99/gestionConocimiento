package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parametrizacion.model.SeedbedEvent;
import com.example.parametrizacion.repository.ResearchSeedbedRepository;
import com.example.parametrizacion.repository.SeedbedEventRepository;

@Service
public class SeedbedEventService {

    @Autowired
    private SeedbedEventRepository seedbedEventRepository;
    
    @Autowired
    private ResearchSeedbedRepository researchSeedbedRepository;

    public List<SeedbedEvent> getAllSeedbedEvents() {
        return seedbedEventRepository.findAll();
    }

    public Optional<SeedbedEvent> getSeedbedEventById(Long id) {
        return seedbedEventRepository.findById(id);
    }

    public List<SeedbedEvent> getSeedbedEventsBySeedbed(Long seedbedId) {
        return seedbedEventRepository.findByResearchSeedbedId(seedbedId);
    }

    public SeedbedEvent createSeedbedEvent(SeedbedEvent event) {
        // Validar que se envió un ResearchSeedbed
        if (event.getResearchSeedbed() == null || 
            event.getResearchSeedbed().getId() == null || 
            !researchSeedbedRepository.existsById(event.getResearchSeedbed().getId())) {
            throw new RuntimeException("ResearchSeedbed not found");
        }

        return seedbedEventRepository.save(event);
    }

    public SeedbedEvent updateSeedbedEvent(Long id, SeedbedEvent event) {
        if (!seedbedEventRepository.existsById(id)) {
            throw new RuntimeException("SeedbedEvent not found");
        }
        
        // Validar que se envió un ResearchSeedbed
        if (event.getResearchSeedbed() == null || 
            event.getResearchSeedbed().getId() == null || 
            !researchSeedbedRepository.existsById(event.getResearchSeedbed().getId())) {
            throw new RuntimeException("ResearchSeedbed not found");
        }

        event.setId(id);
        return seedbedEventRepository.save(event);
    }

    public void deleteSeedbedEvent(Long id) {
        if (!seedbedEventRepository.existsById(id)) {
            throw new RuntimeException("SeedbedEvent not found");
        }
        seedbedEventRepository.deleteById(id);
    }
}
