package com.UserManager.controller;

import com.UserManager.api.RoleAPI;
import com.UserManager.model.entites.Role;
import com.UserManager.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoleController implements RoleAPI {

    private RoleService roleService;

    @Override
    public Role save(@RequestBody Role role) {
        return roleService.save(role);
    }

    @Override
    public Role update(@RequestBody Role role) {
        return roleService.update(role);
    }

    @Override
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }

    @Override
    public Role findById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleService.findAll();
    }
}
