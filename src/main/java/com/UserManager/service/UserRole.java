package com.UserManager.service;

import com.UserManager.dao.UserRoleRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRole {
    
    private final UserRoleRepository userRoleRepository;

    public com.UserManager.model.entites.UserRole save(com.UserManager.model.entites.UserRole userRole) {
        Optional<com.UserManager.model.entites.UserRole> userRoleOptional = userRoleRepository.findById(userRole.getId());
        if (userRoleOptional.isPresent()) {
            throw new ConflictException("UserRole already exists");
        }
        return userRoleRepository.save(userRole);
    }

    public com.UserManager.model.entites.UserRole update(com.UserManager.model.entites.UserRole userRole) {
        findById(userRole.getId());
        return userRoleRepository.save(userRole);
    }
    public com.UserManager.model.entites.UserRole findById(Long id) {
        Optional<com.UserManager.model.entites.UserRole> userRoleOptional = userRoleRepository.findById(id);
        if (userRoleOptional.isEmpty()) {
            throw new NotFoundException("UserRole not found");
        }
        return userRoleOptional.get();
    }
    public void delete(Long id) {
        findById(id);
        userRoleRepository.deleteById(id);
    }
    public List<com.UserManager.model.entites.UserRole> findAll() {
        return userRoleRepository.findAll();
    }
}
