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

import com.example.parametrizacion.model.ResearchSeedbed;
import com.example.parametrizacion.service.ResearchSeedbedService;

@RestController
@RequestMapping("/api/research-seedbeds")
public class ResearchSeedbedController {

    @Autowired
    private ResearchSeedbedService researchSeedbedService;

    @GetMapping
    public List<ResearchSeedbed> getAll() {
        return researchSeedbedService.getAllResearchSeedbeds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearchSeedbed> getById(@PathVariable String id) {
        return researchSeedbedService.getResearchSeedbedById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/group/{groupId}")
    public List<ResearchSeedbed> getByGroup(@PathVariable String groupId) {
        return researchSeedbedService.getResearchSeedbedsByGroup(groupId);
    }

    @GetMapping("/regional/{regionalId}")
    public List<ResearchSeedbed> getByRegional(@PathVariable String regionalId) {
        return researchSeedbedService.getResearchSeedbedsByRegional(regionalId);
    }

    @GetMapping("/center/{centerId}")
    public List<ResearchSeedbed> getByCenter(@PathVariable String centerId) {
        return researchSeedbedService.getResearchSeedbedsByCenter(centerId);
    }

    @PostMapping
    public ResearchSeedbed create(@RequestBody ResearchSeedbed researchSeedbed) {
        return researchSeedbedService.createResearchSeedbed(researchSeedbed);
    }

    @PutMapping("/{id}")
    public ResearchSeedbed update(@PathVariable String id, @RequestBody ResearchSeedbed researchSeedbed) {
        return researchSeedbedService.updateResearchSeedbed(id, researchSeedbed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        researchSeedbedService.deleteResearchSeedbed(id);
        return ResponseEntity.noContent().build();
    }
}
