package com.UserManager.service;

import com.UserManager.dao.RoleRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImp {

    private final RoleRepository roleRepository;

    public Role save(Role role) {
        Optional<Role> roleOptional = roleRepository.findById(role.getId());
        if (roleOptional.isPresent()) {
            throw new ConflictException("Role already exists");
        }
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        findById(role.getId());
        return roleRepository.save(role);
    }
    public Role findById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isEmpty()) {
            throw new NotFoundException("Role not found");
        }
        return roleOptional.get();
    }
    public void delete(Long id) {
        findById(id);
        roleRepository.deleteById(id);
    }
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}
