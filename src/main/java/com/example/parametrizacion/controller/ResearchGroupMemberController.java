package com.example.parametrizacion.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.parametrizacion.model.ResearchGroupMember;
import com.example.parametrizacion.service.ResearchGroupMemberService;

@RestController
@RequestMapping("/research-group-members")
public class ResearchGroupMemberController {

    @Autowired
    private ResearchGroupMemberService researchGroupMemberService;

    @GetMapping
    public List<ResearchGroupMember> getAllResearchGroupMembers() {
        return researchGroupMemberService.getAllResearchGroupMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearchGroupMember> getResearchGroupMemberById(@PathVariable Long id) {
        return researchGroupMemberService.getResearchGroupMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/group/{groupId}")
    public List<ResearchGroupMember> getResearchGroupMembersByGroup(@PathVariable String groupId) {
        return researchGroupMemberService.getResearchGroupMembersByGroup(groupId);
    }

    @PostMapping
    public ResponseEntity<ResearchGroupMember> createResearchGroupMember(@RequestBody ResearchGroupMember member) {
        try {
            ResearchGroupMember created = researchGroupMemberService.createResearchGroupMember(member);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResearchGroupMember> updateResearchGroupMember(@PathVariable Long id, 
            @RequestBody ResearchGroupMember member) {
        try {
            ResearchGroupMember updated = researchGroupMemberService.updateResearchGroupMember(id, member);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResearchGroupMember(@PathVariable Long id) {
        try {
            researchGroupMemberService.deleteResearchGroupMember(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}