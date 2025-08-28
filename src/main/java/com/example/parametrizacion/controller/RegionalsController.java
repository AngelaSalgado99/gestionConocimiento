
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

import com.example.parametrizacion.model.Regionals;
import com.example.parametrizacion.service.RegionalsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/regionals")
public class RegionalsController {

    @Autowired
    private RegionalsService regionalsService;

    @GetMapping
    public ResponseEntity<List<Regionals>> getAll() {
    List<Regionals> regionals = regionalsService.getAll();
        if (regionals.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(regionals);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Regionals regionals = regionalsService.getById(id);
        if (regionals != null) {
            return ResponseEntity.ok(regionals);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Regionals regionals) {
        if (regionalsService.existsByName(regionals.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Regional with that name already exists");
        }
        Regionals saved = regionalsService.save(regionals);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Regionals regionals) {
        Regionals updated = regionalsService.update(id, regionals);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = regionalsService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Regional deleted");
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}