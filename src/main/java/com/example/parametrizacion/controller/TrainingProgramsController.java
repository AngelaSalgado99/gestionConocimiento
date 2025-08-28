package com.example.parametrizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parametrizacion.model.TrainingPrograms;
import com.example.parametrizacion.service.TrainingProgramsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/research-lines/{researchLinesId}/training-programs")
public class TrainingProgramsController {

    @Autowired
    private TrainingProgramsService trainingProgramsService;

    @GetMapping
    public ResponseEntity<List<TrainingPrograms>> getAll(@PathVariable Long researchLinesId) {
    List<TrainingPrograms> trainingPrograms = trainingProgramsService.getAllByResearchLines(researchLinesId);
        if (trainingPrograms.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(trainingPrograms);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long researchLinesId, @PathVariable Long id) {
    TrainingPrograms trainingPrograms = trainingProgramsService.getByIdAndResearchLinesId(id, researchLinesId);
        if (trainingPrograms != null) {
            return ResponseEntity.ok(trainingPrograms);
        } else {
        return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@PathVariable Long researchLinesId,
        @Valid @RequestBody TrainingPrograms trainingPrograms) {
        if (trainingProgramsService.existsByName(trainingPrograms.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Training Program with that name already exists");
        }
        TrainingPrograms saved = trainingProgramsService.save(researchLinesId, trainingPrograms);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long researchLinesId, @PathVariable Long id, @RequestBody TrainingPrograms trainingPrograms) {
    TrainingPrograms updated = trainingProgramsService.update(id, researchLinesId, trainingPrograms);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
        return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long researchLinesId, @PathVariable Long id) {
    boolean deleted = trainingProgramsService.delete(id, researchLinesId);
        if (deleted) {
            return ResponseEntity.ok().body("Training Program deleted");
        } else {
        return ResponseEntity.noContent().build();
        }
    }
}
