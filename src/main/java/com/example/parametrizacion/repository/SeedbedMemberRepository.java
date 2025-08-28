package com.example.parametrizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.SeedbedMember;

@Repository
public interface SeedbedMemberRepository extends JpaRepository<SeedbedMember, Long> {
    List<SeedbedMember> findByResearchSeedbedId(Long seedbedId);
}