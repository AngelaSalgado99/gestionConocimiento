package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parametrizacion.model.ResearchGroupMember;
import com.example.parametrizacion.repository.ResearchGroupMemberRepository;
import com.example.parametrizacion.repository.ResearchGroupRepository;

@Service
public class ResearchGroupMemberService {

    @Autowired
    private ResearchGroupMemberRepository researchGroupMemberRepository;
    
    @Autowired
    private ResearchGroupRepository researchGroupRepository;

    public List<ResearchGroupMember> getAllResearchGroupMembers() {
        return researchGroupMemberRepository.findAll();
    }

    public Optional<ResearchGroupMember> getResearchGroupMemberById(Long id) {
        return researchGroupMemberRepository.findById(id);
    }

    public List<ResearchGroupMember> getResearchGroupMembersByGroup(Long groupId) {
        return researchGroupMemberRepository.findByResearchGroupId(groupId);
    }

    public ResearchGroupMember createResearchGroupMember(ResearchGroupMember researchGroupMember) {
        // Solo validar que existe el ResearchGroup
        if (!researchGroupRepository.existsById(researchGroupMember.getResearchGroup().getId())) {
            throw new RuntimeException("ResearchGroup not found");
        }

        return researchGroupMemberRepository.save(researchGroupMember);
    }

    public ResearchGroupMember updateResearchGroupMember(Long id, ResearchGroupMember researchGroupMember) {
        if (!researchGroupMemberRepository.existsById(id)) {
            throw new RuntimeException("ResearchGroupMember not found");
        }
        
        // Validar que existe el ResearchGroup
        if (!researchGroupRepository.existsById(researchGroupMember.getResearchGroup().getId())) {
            throw new RuntimeException("ResearchGroup not found");
        }

        researchGroupMember.setId(id);
        return researchGroupMemberRepository.save(researchGroupMember);
    }

    public void deleteResearchGroupMember(Long id) {
        if (!researchGroupMemberRepository.existsById(id)) {
            throw new RuntimeException("ResearchGroupMember not found");
        }
        researchGroupMemberRepository.deleteById(id);
    }
}
