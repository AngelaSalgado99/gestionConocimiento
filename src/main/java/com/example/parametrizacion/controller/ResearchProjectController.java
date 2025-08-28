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
import com.example.parametrizacion.model.ResearchProject;
import com.example.parametrizacion.service.ResearchProjectService;

@RestController
@RequestMapping("/research-projects")
public class ResearchProjectController {

    @Autowired
    private ResearchProjectService researchProjectService;

    @GetMapping
    public List<ResearchProject> getAllResearchProjects() {
        return researchProjectService.getAllResearchProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearchProject> getResearchProjectById(@PathVariable String id) {
        return researchProjectService.getResearchProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/group/{groupId}")
    public List<ResearchProject> getResearchProjectsByGroup(@PathVariable String groupId) {
        return researchProjectService.getResearchProjectsByGroup(groupId);
    }

    @GetMapping("/seedbed/{seedbedId}")
    public List<ResearchProject> getResearchProjectsBySeedbed(@PathVariable String seedbedId) {
        return researchProjectService.getResearchProjectsBySeedbed(seedbedId);
    }

    @GetMapping("/research-line/{researchLineId}")
    public List<ResearchProject> getResearchProjectsByResearchLine(@PathVariable Long researchLineId) {
        return researchProjectService.getResearchProjectsByResearchLine(researchLineId);
    }

    @PostMapping
    public ResponseEntity<ResearchProject> createResearchProject(@RequestBody ResearchProject researchProject) {
        try {
            ResearchProject created = researchProjectService.createResearchProject(researchProject);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResearchProject> updateResearchProject(@PathVariable String id, 
            @RequestBody ResearchProject researchProject) {
        try {
            ResearchProject updated = researchProjectService.updateResearchProject(id, researchProject);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResearchProject(@PathVariable String id) {
        try {
            researchProjectService.deleteResearchProject(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}