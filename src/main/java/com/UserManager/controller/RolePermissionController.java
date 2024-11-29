package com.UserManager.controller;

import com.UserManager.api.RolePermissionAPI;
import com.UserManager.model.entites.RolePermission;
import com.UserManager.service.RolePermissionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RolePermissionController implements RolePermissionAPI {

    private final RolePermissionService rolePermissionService;

    @Override
    public RolePermission save(@RequestBody RolePermission rolePermission) {
        return rolePermissionService.save(rolePermission);
    }
    @Override
    public RolePermission update(@RequestBody RolePermission rolePermission) {
        return rolePermissionService.update(rolePermission);
    }

    @Override
    public void delete(@PathVariable Long id) {
        rolePermissionService.delete(id);
    }

    @Override
    public RolePermission findById(@PathVariable Long id) {
        return rolePermissionService.findById(id);
    }

    @Override
    public List<RolePermission> findAll() {
        return rolePermissionService.findAll();
    }
}
