package com.UserManager.service;

import com.UserManager.dao.PermissionRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;

import com.UserManager.model.entites.Permission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public Permission save(Permission permission) {
        Optional<Permission> permissionOptional = permissionRepository.findById(permission.getId());
        if (permissionOptional.isPresent()) {
            throw new ConflictException("Permission already exists");
        }
        return permissionRepository.save(permission);
    }

    public Permission update(Permission permission) {
        findById(permission.getId());
        return permissionRepository.save(permission);
    }
    public Permission findById(Long id) {
        Optional<Permission> permissionOptional = permissionRepository.findById(id);
        if (permissionOptional.isEmpty()) {
            throw new NotFoundException("Permission not found");
        }
        return permissionOptional.get();
    }
    public void delete(Long id) {
        findById(id);
        permissionRepository.deleteById(id);
    }
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

}
