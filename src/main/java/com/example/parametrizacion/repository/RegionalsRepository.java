package com.example.parametrizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.Regionals;

@Repository
public interface RegionalsRepository extends JpaRepository<Regionals, Long> {
    boolean existsByName(String name);
}