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
import com.example.parametrizacion.model.SeedbedMember;
import com.example.parametrizacion.service.SeedbedMemberService;

@RestController
@RequestMapping("/seedbed-members")
public class SeedbedMemberController {

    @Autowired
    private SeedbedMemberService seedbedMemberService;

    @GetMapping
    public List<SeedbedMember> getAllSeedbedMembers() {
        return seedbedMemberService.getAllSeedbedMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeedbedMember> getSeedbedMemberById(@PathVariable Long id) {
        return seedbedMemberService.getSeedbedMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/seedbed/{seedbedId}")
    public List<SeedbedMember> getSeedbedMembersBySeedbed(@PathVariable String seedbedId) {
        return seedbedMemberService.getSeedbedMembersBySeedbed(seedbedId);
    }

    @PostMapping
    public ResponseEntity<SeedbedMember> createSeedbedMember(@RequestBody SeedbedMember member) {
        try {
            SeedbedMember created = seedbedMemberService.createSeedbedMember(member);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeedbedMember> updateSeedbedMember(@PathVariable Long id, 
            @RequestBody SeedbedMember member) {
        try {
            SeedbedMember updated = seedbedMemberService.updateSeedbedMember(id, member);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeedbedMember(@PathVariable Long id) {
        try {
            seedbedMemberService.deleteSeedbedMember(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}