package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parametrizacion.model.ResearchLines;
import com.example.parametrizacion.model.TrainingPrograms;

import com.example.parametrizacion.repository.ResearchLinesRepository;
import com.example.parametrizacion.repository.TrainingProgramsRepository;

@Service
public class TrainingProgramsService {
    
    @Autowired
    private TrainingProgramsRepository trainingProgramsRepository;

    @Autowired
    private ResearchLinesRepository researchLinesRepository;

    //Para el global
    public List<TrainingPrograms> getAll() {
        return trainingProgramsRepository.findAll();
    }
    public TrainingPrograms getById(Long id) {
        return trainingProgramsRepository.findById(id).orElse(null);
    }


    public boolean existsByName(String name) {
        return trainingProgramsRepository.existsByName(name);
    }

    public List<TrainingPrograms> getAllByResearchLines(Long researchLinesId) {
        return trainingProgramsRepository.findByResearchLinesId(researchLinesId);
    }

    public TrainingPrograms getByIdAndResearchLinesId(Long id, Long researchLinesId) {
    return trainingProgramsRepository.findByIdAndResearchLinesId(id, researchLinesId).orElse(null);
    }

    public TrainingPrograms save(Long researchLinesId, TrainingPrograms trainingPrograms) {
        Optional<ResearchLines> researchLines = researchLinesRepository.findById(researchLinesId);
        if (researchLines.isPresent()) {
            trainingPrograms.setResearchLines(researchLines.get());
            return trainingProgramsRepository.save(trainingPrograms);
        } else {
            throw new IllegalArgumentException("research line not found with ID " + researchLinesId);
        }
    }

    //Update Training Programs global
    public TrainingPrograms updateTrainingProgramsGlobal(Long id, TrainingPrograms updateData) {
    Optional<TrainingPrograms> optional = trainingProgramsRepository.findById(id);
    if (optional.isPresent()) {
        TrainingPrograms existing = optional.get();
        existing.setName(updateData.getName());
        existing.setDescription(updateData.getDescription());
        return trainingProgramsRepository.save(existing);
    }
        return null;
    }

    public TrainingPrograms update(Long id, Long researchLinesId, TrainingPrograms newData) {
    Optional<TrainingPrograms> optionalTrainingPrograms = trainingProgramsRepository.findByIdAndResearchLinesId(id, researchLinesId);
        if (optionalTrainingPrograms.isPresent()) {
            TrainingPrograms existing = optionalTrainingPrograms.get();
            existing.setName(newData.getName());
            existing.setDescription(newData.getDescription());
            return trainingProgramsRepository.save(existing);
        }
        return null;
    }

    //Delete global
    public boolean deleteGlobal(Long id) {
    Optional<TrainingPrograms> optional = trainingProgramsRepository.findById(id);
    if (optional.isPresent()) {
        trainingProgramsRepository.deleteById(id);
        return true;
    }
    return false;
}

    public boolean delete(Long id, Long researchLinesId) {
    Optional<TrainingPrograms> optionalTrainingPrograms = trainingProgramsRepository.findByIdAndResearchLinesId(id, researchLinesId);
    if (optionalTrainingPrograms.isPresent()) {
        trainingProgramsRepository.deleteById(id);
        return true;
    }
    return false;
    }
}
