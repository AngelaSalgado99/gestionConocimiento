package com.example.parametrizacion.controller;

import com.example.parametrizacion.model.Centers;
import com.example.parametrizacion.service.CentersService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centers")
public class CentersGlobalController {

    @Autowired
    private CentersService centersService;

    @GetMapping
    public ResponseEntity<List<Centers>> getAll() {
        List<Centers> centers = centersService.getAll();
        if (centers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Centers center = centersService.getById(id);
        if (center != null) {
            return ResponseEntity.ok(center);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Centers centers) {
        Centers updated = centersService.updateCenterGlobal(id, centers);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
        return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Centers center = centersService.getById(id);
        if (center != null) {
            centersService.delete(id, center.getRegionals().getId());
            return ResponseEntity.ok("Center deleted");
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}

