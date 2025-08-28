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
@RequestMapping("/seedbed-events")
public class SeedbedEventController {
    @Autowired
    private SeedbedEventService seedbedEventService;
    @GetMapping
    public List<SeedbedEvent> getAllSeedbedEvents() {
        return seedbedEventService.getAllSeedbedEvents();
    }
    @GetMapping("/{id}")
    public ResponseEntity<SeedbedEvent> getSeedbedEventById(@PathVariable Long id) {
        return seedbedEventService.getSeedbedEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/seedbed/{seedbedId}")
    public List<SeedbedEvent> getSeedbedEventsBySeedbed(@PathVariable Long seedbedId) {  // CAMBIO: String -> Long
        return seedbedEventService.getSeedbedEventsBySeedbed(seedbedId);
    }
    @PostMapping
    public ResponseEntity<SeedbedEvent> createSeedbedEvent(@RequestBody SeedbedEvent event) {
        try {
            SeedbedEvent created = seedbedEventService.createSeedbedEvent(event);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<SeedbedEvent> updateSeedbedEvent(@PathVariable Long id,
            @RequestBody SeedbedEvent event) {
        try {
            SeedbedEvent updated = seedbedEventService.updateSeedbedEvent(id, event);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeedbedEvent(@PathVariable Long id) {
        try {
            seedbedEventService.deleteSeedbedEvent(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}