package com.example.parametrizacion.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.parametrizacion.model.ResearchSeedbed;
import com.example.parametrizacion.service.ResearchSeedbedService;

@RestController
@RequestMapping("/research-seedbeds")
public class ResearchSeedbedController {

    @Autowired
    private ResearchSeedbedService researchSeedbedService;

    @GetMapping
    public List<ResearchSeedbed> getAllResearchSeedbeds() {
        return researchSeedbedService.getAllResearchSeedbeds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearchSeedbed> getResearchSeedbedById(@PathVariable String id) {
        return researchSeedbedService.getResearchSeedbedById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/group/{groupId}")
    public List<ResearchSeedbed> getResearchSeedbedsByGroup(@PathVariable String groupId) {
        return researchSeedbedService.getResearchSeedbedsByGroup(groupId);
    }

    @GetMapping("/regional/{regionalId}")
    public List<ResearchSeedbed> getResearchSeedbedsByRegional(@PathVariable Long regionalId) {
        return researchSeedbedService.getResearchSeedbedsByRegional(regionalId);
    }

    @PostMapping
    public ResponseEntity<ResearchSeedbed> createResearchSeedbed(@RequestBody ResearchSeedbed researchSeedbed) {
        try {
            ResearchSeedbed created = researchSeedbedService.createResearchSeedbed(researchSeedbed);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResearchSeedbed> updateResearchSeedbed(@PathVariable String id, 
            @RequestBody ResearchSeedbed researchSeedbed) {
        try {
            ResearchSeedbed updated = researchSeedbedService.updateResearchSeedbed(id, researchSeedbed);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResearchSeedbed(@PathVariable String id) {
        try {
            researchSeedbedService.deleteResearchSeedbed(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}