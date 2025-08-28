package com.example.parametrizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parametrizacion.model.ResearchGroup;
import com.example.parametrizacion.model.ResearchGroupMember;

@Repository
public interface ResearchGroupMemberRepository extends JpaRepository<ResearchGroupMember, Long> {
    List<ResearchGroupMember> findByResearchGroup(ResearchGroup researchGroup);
    List<ResearchGroupMember> findByResearchGroupId(Long ResearchGroupId);
}
