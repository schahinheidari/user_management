package com.UserManager.controller;

import com.UserManager.api.UserRoleAPI;
import com.UserManager.model.entites.UserRole;
import com.UserManager.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserRoleController implements UserRoleAPI {
    private final UserRoleService userRoleService;

    @Override
    public UserRole save(@RequestBody UserRole userRole) {
        return userRoleService.save(userRole);
    }

    @Override
    public UserRole update(@RequestBody UserRole userRole) {
        return userRoleService.update(userRole);
    }

    @Override
    public void delete(@PathVariable Long id) {
        userRoleService.delete(id);
    }

    @Override
    public UserRole findById(@PathVariable Long id) {
        return userRoleService.findById(id);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleService.findAll();
    }

}
