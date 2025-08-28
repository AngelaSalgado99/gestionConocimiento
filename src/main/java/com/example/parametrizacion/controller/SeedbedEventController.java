package com.example.parametrizacion.controller;

import com.example.parametrizacion.model.SeedbedEvent;
import com.example.parametrizacion.service.SeedbedEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seedbed-events")
public class SeedbedEventController {

    @Autowired
    private SeedbedEventService seedbedEventService;

    // 📌 Obtener todos los eventos
    @GetMapping
    public ResponseEntity<List<SeedbedEvent>> getAllSeedbedEvents() {
        List<SeedbedEvent> events = seedbedEventService.getAllSeedbedEvents();
        return ResponseEntity.ok(events);
    }

    // 📌 Obtener un evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<SeedbedEvent> getSeedbedEventById(@PathVariable Long id) {
        return seedbedEventService.getSeedbedEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 📌 Obtener eventos por semillero
    @GetMapping("/seedbed/{seedbedId}")
    public ResponseEntity<List<SeedbedEvent>> getSeedbedEventsBySeedbed(@PathVariable Long seedbedId) {
        List<SeedbedEvent> events = seedbedEventService.getSeedbedEventsBySeedbed(seedbedId);
        return ResponseEntity.ok(events);
    }

    // 📌 Crear un evento
    @PostMapping
    public ResponseEntity<?> createSeedbedEvent(@RequestBody SeedbedEvent event) {
        try {
            SeedbedEvent created = seedbedEventService.createSeedbedEvent(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 📌 Actualizar un evento
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSeedbedEvent(@PathVariable Long id, @RequestBody SeedbedEvent event) {
        try {
            SeedbedEvent updated = seedbedEventService.updateSeedbedEvent(id, event);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 📌 Eliminar un evento
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeedbedEvent(@PathVariable Long id) {
        try {
            seedbedEventService.deleteSeedbedEvent(id);
            return ResponseEntity.ok("SeedbedEvent eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
