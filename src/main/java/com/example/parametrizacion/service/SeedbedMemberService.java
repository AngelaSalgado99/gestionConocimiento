package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parametrizacion.model.SeedbedMember;
import com.example.parametrizacion.repository.ResearchSeedbedRepository;
import com.example.parametrizacion.repository.SeedbedMemberRepository;

@Service
public class SeedbedMemberService {

    @Autowired
    private SeedbedMemberRepository seedbedMemberRepository;
    
    @Autowired
    private ResearchSeedbedRepository researchSeedbedRepository;

    public List<SeedbedMember> getAllSeedbedMembers() {
        return seedbedMemberRepository.findAll();
    }

    public Optional<SeedbedMember> getSeedbedMemberById(Long id) {
        return seedbedMemberRepository.findById(id);
    }

    public List<SeedbedMember> getSeedbedMembersBySeedbed(Long seedbedId) {
        return seedbedMemberRepository.findByResearchSeedbedId(seedbedId);
    }

    public SeedbedMember createSeedbedMember(SeedbedMember member) {
        // Validar que se envió un ResearchSeedbed
        if (member.getResearchSeedbed() == null || 
            member.getResearchSeedbed().getId() == null || 
            !researchSeedbedRepository.existsById(member.getResearchSeedbed().getId())) {
            throw new RuntimeException("ResearchSeedbed not found");
        }

        return seedbedMemberRepository.save(member);
    }

    public SeedbedMember updateSeedbedMember(Long id, SeedbedMember member) {
        if (!seedbedMemberRepository.existsById(id)) {
            throw new RuntimeException("SeedbedMember not found");
        }
        
        // Validar que se envió un ResearchSeedbed
        if (member.getResearchSeedbed() == null || 
            member.getResearchSeedbed().getId() == null || 
            !researchSeedbedRepository.existsById(member.getResearchSeedbed().getId())) {
            throw new RuntimeException("ResearchSeedbed not found");
        }

        member.setId(id);
        return seedbedMemberRepository.save(member);
    }

    public void deleteSeedbedMember(Long id) {
        if (!seedbedMemberRepository.existsById(id)) {
            throw new RuntimeException("SeedbedMember not found");
        }
        seedbedMemberRepository.deleteById(id);
    }
}
