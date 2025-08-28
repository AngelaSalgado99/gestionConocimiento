
package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parametrizacion.model.DocumentType;
import com.example.parametrizacion.repository.DocumentTypeRepository;

@Service
public class DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    public boolean existsByType(String type) {
        return documentTypeRepository.existsByType(type);
    }

    public List<DocumentType> getAll() {
        return documentTypeRepository.findAll();
    }

    public DocumentType getById(Long id) {
        return documentTypeRepository.findById(id).orElse(null);
    }

    public DocumentType save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    public DocumentType update(Long id, DocumentType newData) {
    Optional<DocumentType> optional = documentTypeRepository.findById(id);
    if (optional.isPresent()) {
        DocumentType existing = optional.get();
        existing.setType(newData.getType());
        existing.setDescription(newData.getDescription());
            return documentTypeRepository.save(existing);
        }
        return null;
    }

    public boolean delete(Long documentTypeId) {
        if (documentTypeRepository.existsById(documentTypeId)) {
        documentTypeRepository.deleteById(documentTypeId);
            return true;
        } else {
            return false;
        }
    }
}
