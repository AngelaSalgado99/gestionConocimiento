package com.example.parametrizacion.controller;

import com.example.parametrizacion.model.ResearchLines;
import com.example.parametrizacion.service.ResearchLinesService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/research-lines")
public class ResearchLinesGlobalController {

    @Autowired
    private ResearchLinesService researchLinesService;

    @GetMapping
    public ResponseEntity<List<ResearchLines>> getAll() {
        List<ResearchLines> researchLines = researchLinesService.getAll();
        if (researchLines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(researchLines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ResearchLines researchLines = researchLinesService.getById(id);
        if (researchLines != null) {
            return ResponseEntity.ok(researchLines);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ResearchLines researchLines) {
        ResearchLines updated = researchLinesService.updateResearchLinesGlobal(id, researchLines);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
        return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = researchLinesService.deleteGlobal(id);
        if (deleted) {
            return ResponseEntity.ok("Research line deleted");
        } else {
        return ResponseEntity.noContent().build();
        }
    }
}

