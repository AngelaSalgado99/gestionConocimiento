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

import com.example.parametrizacion.model.DocumentType;
import com.example.parametrizacion.service.DocumentTypeService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/document-types")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping
    public ResponseEntity<List<DocumentType>> getAll() {
    List<DocumentType> documentType = documentTypeService.getAll();
        if (documentType.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(documentType);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        DocumentType documentType = documentTypeService.getById(id);
        if (documentType != null) {
            return ResponseEntity.ok(documentType);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DocumentType documentType) {
        if (documentTypeService.existsByType(documentType.getType())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Document with that type already exists");
        }
        DocumentType saved = documentTypeService.save(documentType);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody DocumentType documentType) {
        DocumentType updated = documentTypeService.update(id, documentType);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = documentTypeService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Document type deleted");
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}