package com.example.parametrizacion.service;

import com.example.parametrizacion.model.ResearchSeedbed;
import com.example.parametrizacion.model.SeedbedEvent;
import com.example.parametrizacion.repository.ResearchSeedbedRepository;
import com.example.parametrizacion.repository.SeedbedEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeedbedEventService {

    @Autowired
    private SeedbedEventRepository seedbedEventRepository;

    @Autowired
    private ResearchSeedbedRepository researchSeedbedRepository;

    // 📌 Obtener todos los eventos
    public List<SeedbedEvent> getAllSeedbedEvents() {
        return seedbedEventRepository.findAll();
    }

    // 📌 Obtener un evento por ID
    public Optional<SeedbedEvent> getSeedbedEventById(Long id) {
        return seedbedEventRepository.findById(id);
    }

    // 📌 Obtener eventos por semillero
    public List<SeedbedEvent> getSeedbedEventsBySeedbed(Long seedbedId) {
        return seedbedEventRepository.findByResearchSeedbedId(seedbedId);
    }

    // 📌 Crear un evento
    public SeedbedEvent createSeedbedEvent(SeedbedEvent event) {
        if (event.getResearchSeedbed() == null || event.getResearchSeedbed().getId() == null) {
            throw new RuntimeException("El ResearchSeedbed es obligatorio");
        }

        ResearchSeedbed seedbed = researchSeedbedRepository.findById(event.getResearchSeedbed().getId())
                .orElseThrow(() -> new RuntimeException("ResearchSeedbed no encontrado"));

        event.setResearchSeedbed(seedbed);
        return seedbedEventRepository.save(event);
    }

    // 📌 Actualizar un evento
    public SeedbedEvent updateSeedbedEvent(Long id, SeedbedEvent event) {
        SeedbedEvent existing = seedbedEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SeedbedEvent no encontrado"));

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

    // 📌 Eliminar un evento
    public void deleteSeedbedEvent(Long id) {
        if (!seedbedEventRepository.existsById(id)) {
            throw new RuntimeException("SeedbedEvent no encontrado");
        }
        seedbedEventRepository.deleteById(id);
    }
}
