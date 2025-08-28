package com.example.parametrizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.SeedbedEvent;

@Repository
public interface SeedbedEventRepository extends JpaRepository<SeedbedEvent, Long> {
    List<SeedbedEvent> findByResearchSeedbedId(Long seedbedId);
    boolean existsByNameAndResearchSeedbedId(String name, Long seedbedId);
}