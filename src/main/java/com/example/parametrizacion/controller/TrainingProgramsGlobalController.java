package com.example.parametrizacion.controller;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.parametrizacion.model.TrainingPrograms;
import com.example.parametrizacion.service.TrainingProgramsService;

import java.util.List;

@RestController
@RequestMapping("/training-programs")
public class TrainingProgramsGlobalController {

    @Autowired
    private TrainingProgramsService trainingProgramsService;

    @GetMapping
    public ResponseEntity<List<TrainingPrograms>> getAll() {
        List<TrainingPrograms> trainingPrograms = trainingProgramsService.getAll();
        if (trainingPrograms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trainingPrograms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        TrainingPrograms trainingPrograms = trainingProgramsService.getById(id);
        if (trainingPrograms != null) {
            return ResponseEntity.ok(trainingPrograms);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TrainingPrograms trainingPrograms) {
        TrainingPrograms updated = trainingProgramsService.updateTrainingProgramsGlobal(id, trainingPrograms);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
        return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = trainingProgramsService.deleteGlobal(id);
        if (deleted) {
            return ResponseEntity.ok("Training Program deleted");
        } else {
        return ResponseEntity.noContent().build();
        }
    }
}

