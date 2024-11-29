package com.UserManager.controller;

import com.UserManager.model.entites.Permission;
import com.UserManager.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission/v1")
@AllArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Permission save(@RequestBody Permission permission) {
        return permissionService.save(permission);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Permission update(@RequestBody Permission permission) {
        return permissionService.update(permission);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        permissionService.delete(id);
    }

    @GetMapping("/find/{id}")
    public Permission findById(@PathVariable Long id) {
        return permissionService.findById(id);
    }

    @GetMapping("/list")
    public List<Permission> findAll() {
        return permissionService.findAll();
    }
}
