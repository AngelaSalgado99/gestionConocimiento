// src/main/java/com/example/parametrizacion/service/RolService.java
package com.example.parametrizacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parametrizacion.model.Role;
import com.example.parametrizacion.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Long id, Role newData) {
    Optional<Role> optional = roleRepository.findById(id);
    if (optional.isPresent()) {
        Role existing = optional.get();
        existing.setName(newData.getName());
        existing.setDescription(newData.getDescription());
        return roleRepository.save(existing);
    }
        return null;
    }

    public boolean delete(Long roleId) {
        if (roleRepository.existsById(roleId)) {
        roleRepository.deleteById(roleId);
            return true;
        } else {
            return false;
        }
    }
}