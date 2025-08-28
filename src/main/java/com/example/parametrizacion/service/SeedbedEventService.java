package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parametrizacion.model.SeedbedEvent;
import com.example.parametrizacion.model.ResearchSeedbed;
import com.example.parametrizacion.repository.SeedbedEventRepository;
import com.example.parametrizacion.repository.ResearchSeedbedRepository;

@Service
public class SeedbedEventService {

    @Autowired
    private SeedbedEventRepository seedbedEventRepository;

    @Autowired
    private ResearchSeedbedRepository researchSeedbedRepository;

    public List<SeedbedEvent> getAllEvents() {
        return seedbedEventRepository.findAll();
    }

    public Optional<SeedbedEvent> getEventById(Long id) {
        return seedbedEventRepository.findById(id);
    }

    public List<SeedbedEvent> getEventsBySeedbed(Long seedbedId) {
        return seedbedEventRepository.findByResearchSeedbedId(seedbedId);
    }

    public SeedbedEvent createEvent(SeedbedEvent event) {
        if (event.getResearchSeedbed() == null || event.getResearchSeedbed().getId() == null) {
            throw new RuntimeException("El ResearchSeedbed es obligatorio");
        }

        ResearchSeedbed seedbed = researchSeedbedRepository.findById(event.getResearchSeedbed().getId())
                .orElseThrow(() -> new RuntimeException("ResearchSeedbed no encontrado"));

        if (seedbedEventRepository.existsByNameAndResearchSeedbedId(event.getName(), seedbed.getId())) {
            throw new RuntimeException("Ya existe un evento con este nombre en el semillero");
        }

        event.setResearchSeedbed(seedbed);
        return seedbedEventRepository.save(event);
    }

    public SeedbedEvent updateEvent(Long id, SeedbedEvent event) {
        SeedbedEvent existing = seedbedEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        if (event.getName() != null) existing.setName(event.getName());
        if (event.getDescription() != null) existing.setDescription(event.getDescription());
        if (event.getStartDate() != null) existing.setStartDate(event.getStartDate());
        if (event.getEndDate() != null) existing.setEndDate(event.getEndDate());
        if (event.getLocation() != null) existing.setLocation(event.getLocation());
        if (event.getParticipantCount() != null) existing.setParticipantCount(event.getParticipantCount());
        if (event.getEventType() != null) existing.setEventType(event.getEventType());
        if (event.getPresenter() != null) existing.setPresenter(event.getPresenter());

        if (event.getResearchSeedbed() != null && event.getResearchSeedbed().getId() != null) {
            ResearchSeedbed seedbed = researchSeedbedRepository.findById(event.getResearchSeedbed().getId())
                    .orElseThrow(() -> new RuntimeException("ResearchSeedbed no encontrado"));
            existing.setResearchSeedbed(seedbed);
        }

        return seedbedEventRepository.save(existing);
    }

    public void deleteEvent(Long id) {
        if (!seedbedEventRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado");
        }
        seedbedEventRepository.deleteById(id);
    }
}
