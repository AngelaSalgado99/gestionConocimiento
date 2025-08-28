
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

import com.example.parametrizacion.model.Centers;
import com.example.parametrizacion.service.CentersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/regionals/{regionalsId}/centers")
public class CentersController {

    @Autowired
    private CentersService centersService;

    @GetMapping
    public ResponseEntity<List<Centers>> getAll(@PathVariable Long regionalsId) {
    List<Centers> centers = centersService.getAllByRegionals(regionalsId);
        if (centers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(centers);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long regionalsId, @PathVariable Long id) {
    Centers centers = centersService.getByIdAndRegionalsId(id, regionalsId);
        if (centers != null) {
            return ResponseEntity.ok(centers);
        } else {
        return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@PathVariable Long regionalsId,
        @Valid @RequestBody Centers centers) {
        if (centersService.existsByName(centers.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Center with that name already exists");
        }
        Centers saved = centersService.save(regionalsId, centers);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long regionalsId, @PathVariable Long id, @RequestBody Centers centers) {
    Centers updated = centersService.update(id, regionalsId, centers);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
        return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long regionalsId, @PathVariable Long id) {
    boolean deleted = centersService.delete(id, regionalsId);
        if (deleted) {
            return ResponseEntity.ok().body("Center deleted");
        } else {
        return ResponseEntity.noContent().build();
        }
    }

}