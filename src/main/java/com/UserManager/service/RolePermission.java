package com.UserManager.service;

import com.UserManager.dao.RolePermissionRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RolePermission {
    
    private final RolePermissionRepository rolePermissionRepository;

    public com.UserManager.model.entites.RolePermission save(com.UserManager.model.entites.RolePermission rolePermission) {
        Optional<com.UserManager.model.entites.RolePermission> rolePermissionOptional = rolePermissionRepository.findById(rolePermission.getId());
        if (rolePermissionOptional.isPresent()) {
            throw new ConflictException("RolePermission already exists");
        }
        return rolePermissionRepository.save(rolePermission);
    }

    public com.UserManager.model.entites.RolePermission update(com.UserManager.model.entites.RolePermission rolePermission) {
        findById(rolePermission.getId());
        return rolePermissionRepository.save(rolePermission);
    }
    public com.UserManager.model.entites.RolePermission findById(Long id) {
        Optional<com.UserManager.model.entites.RolePermission> rolePermissionOptional = rolePermissionRepository.findById(id);
        if (rolePermissionOptional.isEmpty()) {
            throw new NotFoundException("RolePermission not found");
        }
        return rolePermissionOptional.get();
    }
    public void delete(Long id) {
        findById(id);
        rolePermissionRepository.deleteById(id);
    }
    public List<com.UserManager.model.entites.RolePermission> findAll() {
        return rolePermissionRepository.findAll();
    }
}
