package com.example.parametrizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.SeedbedEvent;

@Repository
public interface SeedbedEventRepository extends JpaRepository<SeedbedEvent, Long> {

    // Buscar eventos por semillero
    List<SeedbedEvent> findByResearchSeedbedId(Long seedbedId);

    // Verificar si existe un evento con un nombre específico en un semillero usando @Query
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END " +
        "FROM SeedbedEvent e " +
        "WHERE e.name = :name AND e.researchSeedbed.id = :seedbedId")
    boolean existsByNameAndSeedbedId(@Param("name") String name, @Param("seedbedId") Long seedbedId);
}
