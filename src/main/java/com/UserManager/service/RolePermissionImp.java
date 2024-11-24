package com.UserManager.service;

import com.UserManager.dao.RolePermissionRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.RolePermission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RolePermissionImp {
    
    private final RolePermissionRepository rolePermissionRepository;

    public RolePermission save(RolePermission rolePermission) {
        Optional<RolePermission> rolePermissionOptional = rolePermissionRepository.findById(rolePermission.getId());
        if (rolePermissionOptional.isPresent()) {
            throw new ConflictException("RolePermission already exists");
        }
        return rolePermissionRepository.save(rolePermission);
    }

    public RolePermission update(RolePermission rolePermission) {
        findById(rolePermission.getId());
        return rolePermissionRepository.save(rolePermission);
    }
    public RolePermission findById(Long id) {
        Optional<RolePermission> rolePermissionOptional = rolePermissionRepository.findById(id);
        if (rolePermissionOptional.isEmpty()) {
            throw new NotFoundException("RolePermission not found");
        }
        return rolePermissionOptional.get();
    }
    public void delete(Long id) {
        findById(id);
        rolePermissionRepository.deleteById(id);
    }
    public List<RolePermission> findAll() {
        return rolePermissionRepository.findAll();
    }
}
