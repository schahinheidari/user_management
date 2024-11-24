package com.UserManager.service;

import com.UserManager.dao.UserRoleRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRoleImp {
    
    private final UserRoleRepository userRoleRepository;

    public UserRole save(UserRole userRole) {
        Optional<UserRole> userRoleOptional = userRoleRepository.findById(userRole.getId());
        if (userRoleOptional.isPresent()) {
            throw new ConflictException("UserRole already exists");
        }
        return userRoleRepository.save(userRole);
    }

    public UserRole update(UserRole userRole) {
        findById(userRole.getId());
        return userRoleRepository.save(userRole);
    }
    public UserRole findById(Long id) {
        Optional<UserRole> userRoleOptional = userRoleRepository.findById(id);
        if (userRoleOptional.isEmpty()) {
            throw new NotFoundException("UserRole not found");
        }
        return userRoleOptional.get();
    }
    public void delete(Long id) {
        findById(id);
        userRoleRepository.deleteById(id);
    }
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }
}
