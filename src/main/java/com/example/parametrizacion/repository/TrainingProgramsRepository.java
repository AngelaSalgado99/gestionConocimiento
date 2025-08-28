package com.example.parametrizacion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.TrainingPrograms;

@Repository
public interface TrainingProgramsRepository extends JpaRepository<TrainingPrograms, Long> {
    boolean existsByName(String name);
    List<TrainingPrograms> findByResearchLinesId(Long researchLinesId);
    Optional<TrainingPrograms> findByIdAndResearchLinesId(Long id, Long researchLinesId);
}
