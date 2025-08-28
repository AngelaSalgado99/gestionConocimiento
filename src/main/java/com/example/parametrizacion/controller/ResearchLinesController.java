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

import com.example.parametrizacion.model.ResearchLines;
import com.example.parametrizacion.service.ResearchLinesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/centers/{centersId}/research-lines")
public class ResearchLinesController {

    @Autowired
    private ResearchLinesService researchLinesService;

    @GetMapping
    public ResponseEntity<List<ResearchLines>> getAll(@PathVariable Long centersId) {
    List<ResearchLines> researchLines = researchLinesService.getAllByCenters(centersId);
        if (researchLines.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(researchLines);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long centersId, @PathVariable Long id) {
    ResearchLines researchLines = researchLinesService.getByIdAndCentersId(id, centersId);
        if (researchLines != null) {
            return ResponseEntity.ok(researchLines);
        } else {
        return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@PathVariable Long centersId,
        @Valid @RequestBody ResearchLines researchLines) {
        if (researchLinesService.existsByName(researchLines.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Research line with that name already exists");
        }
        ResearchLines saved = researchLinesService.save(centersId, researchLines);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long centersId, @PathVariable Long id, @RequestBody ResearchLines researchLines) {
    ResearchLines updated = researchLinesService.update(id, centersId, researchLines);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
        return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long centersId, @PathVariable Long id) {
    boolean deleted = researchLinesService.delete(id, centersId);
        if (deleted) {
            return ResponseEntity.ok().body("Research line deleted");
        } else {
        return ResponseEntity.noContent().build();
        }
    }
}
