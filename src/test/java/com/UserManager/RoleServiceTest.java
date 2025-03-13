package com.UserManager;

import com.UserManager.dao.RoleRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.Role;
import com.UserManager.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void testSaveRoleSuccess() {
        Role role = new Role();
        role.setId(1L);

        when(roleRepository.findById(role.getId())).thenReturn(Optional.empty());
        when(roleRepository.save(role)).thenReturn(role);

        Role savedRole = roleService.save(role);

        assertNotNull(savedRole);
        verify(roleRepository).findById(role.getId());
        verify(roleRepository).save(role);
    }

    @Test
    public void testSaveRoleConflict() {
        Role role = new Role();
        role.setId(1L);

        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));

        assertThrows(ConflictException.class, () -> roleService.save(role));
        verify(roleRepository).findById(role.getId());
        verify(roleRepository, never()).save(any());
    }

    @Test
    public void testUpdateRoleSuccess() {
        Role role = new Role();
        role.setId(1L);

        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));
        when(roleRepository.save(role)).thenReturn(role);

        Role updatedRole = roleService.update(role);

        assertNotNull(updatedRole);
        verify(roleRepository).findById(role.getId());
        verify(roleRepository).save(role);
    }

    @Test
    public void testUpdateRoleNotFound() {
        Role role = new Role();
        role.setId(1L);

        when(roleRepository.findById(role.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roleService.update(role));
        verify(roleRepository).findById(role.getId());
        verify(roleRepository, never()).save(any());
    }

    @Test
    public void testFindByIdSuccess() {
        Long roleId = 1L;
        Role role = new Role();
        role.setId(roleId);

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        Role foundRole = roleService.findById(roleId);

        assertNotNull(foundRole);
        assertEquals(roleId, foundRole.getId());
        verify(roleRepository).findById(roleId);
    }

    @Test
    public void testFindByIdNotFound() {
        Long roleId = 1L;

        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roleService.findById(roleId));
        verify(roleRepository).findById(roleId);
    }

    @Test
    public void testDeleteRoleSuccess() {
        Long roleId = 1L;
        Role role = new Role();
        role.setId(roleId);

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        roleService.delete(roleId);

        verify(roleRepository).findById(roleId);
        verify(roleRepository).deleteById(roleId);
    }

    @Test
    public void testDeleteRoleNotFound() {
        Long roleId = 1L;

        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roleService.delete(roleId));
        verify(roleRepository).findById(roleId);
        verify(roleRepository, never()).deleteById(any());
    }

    @Test
    public void testFindAllRoles() {
        List<Role> roles = List.of(new Role(), new Role());

        when(roleRepository.findAll()).thenReturn(roles);

        List<Role> foundRoles = roleService.findAll();

        assertEquals(2, foundRoles.size());
        verify(roleRepository).findAll();
    }
}

