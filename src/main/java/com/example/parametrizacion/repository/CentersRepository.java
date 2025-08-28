package com.example.parametrizacion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.Centers;

@Repository
public interface CentersRepository extends JpaRepository<Centers, Long> {
    boolean existsByName(String name);
    List<Centers> findByRegionalsId(Long regionalsId);
    Optional<Centers> findByIdAndRegionalsId(Long id, Long regionalsId);
}