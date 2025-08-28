package com.example.parametrizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parametrizacion.model.SeedbedEvent;
import com.example.parametrizacion.service.SeedbedEventService;

@RestController
@RequestMapping("/api/seedbed-events")
public class SeedbedEventController {

    @Autowired
    private SeedbedEventService seedbedEventService;

    // Obtener todos los eventos
    @GetMapping
    public ResponseEntity<List<SeedbedEvent>> getAllEvents() {
        return ResponseEntity.ok(seedbedEventService.getAllEvents());
    }

    // Obtener evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<SeedbedEvent> getEventById(@PathVariable Long id) {
        return seedbedEventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener eventos por semillero
    @GetMapping("/seedbed/{seedbedId}")
    public ResponseEntity<List<SeedbedEvent>> getEventsBySeedbed(@PathVariable Long seedbedId) {
        return ResponseEntity.ok(seedbedEventService.getEventsBySeedbed(seedbedId));
    }

    // Crear un evento
    @PostMapping
    public ResponseEntity<SeedbedEvent> createEvent(@RequestBody SeedbedEvent event) {
        SeedbedEvent created = seedbedEventService.createEvent(event);
        return ResponseEntity.ok(created);
    }

    // Actualizar un evento
    @PutMapping("/{id}")
    public ResponseEntity<SeedbedEvent> updateEvent(@PathVariable Long id, @RequestBody SeedbedEvent event) {
        SeedbedEvent updated = seedbedEventService.updateEvent(id, event);
        return ResponseEntity.ok(updated);
    }

    // Eliminar un evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        seedbedEventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
