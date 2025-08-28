package com.example.parametrizacion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.ResearchLines;

@Repository
public interface ResearchLinesRepository extends JpaRepository<ResearchLines, Long> {
    boolean existsByName(String name);
    List<ResearchLines> findByCentersId(Long centersId);
    Optional<ResearchLines> findByIdAndCentersId(Long id, Long centersId);
}