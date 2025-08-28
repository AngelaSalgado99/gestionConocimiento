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

import com.example.parametrizacion.model.ResearchGroup;
import com.example.parametrizacion.service.ResearchGroupService;

@RestController
@RequestMapping("/research-groups")
public class ResearchGroupController {

    @Autowired
    private ResearchGroupService researchGroupService;

    @GetMapping
    public List<ResearchGroup> getAllResearchGroups() {
        return researchGroupService.getAllResearchGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearchGroup> getResearchGroupById(@PathVariable Long id) {
        return researchGroupService.getResearchGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/regional/{regionalId}")
    public List<ResearchGroup> getResearchGroupsByRegional(@PathVariable Long regionalId) {
        return researchGroupService.getResearchGroupsByRegional(regionalId);
    }

    @GetMapping("/center/{centerId}")
    public List<ResearchGroup> getResearchGroupsByCenter(@PathVariable Long centerId) {
        return researchGroupService.getResearchGroupsByCenter(centerId);
    }

    @PostMapping
    public ResponseEntity<ResearchGroup> createResearchGroup(@RequestBody ResearchGroup researchGroup) {
        try {
            ResearchGroup created = researchGroupService.createResearchGroup(researchGroup);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResearchGroup> updateResearchGroup(@PathVariable Long id,
            @RequestBody ResearchGroup researchGroup) {
        try {
            ResearchGroup updated = researchGroupService.updateResearchGroup(id, researchGroup);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResearchGroup(@PathVariable Long id) {
        try {
            researchGroupService.deleteResearchGroup(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
