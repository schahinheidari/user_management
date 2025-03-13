package com.UserManager;

import com.UserManager.dao.PermissionRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.Permission;
import com.UserManager.service.PermissionService;
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
public class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionService permissionService;

    @Test
    public void testSavePermissionSuccess() {
        Permission permission = new Permission();
        permission.setId(1L);

        when(permissionRepository.findById(permission.getId())).thenReturn(Optional.empty());
        when(permissionRepository.save(permission)).thenReturn(permission);

        Permission savedPermission = permissionService.save(permission);

        assertNotNull(savedPermission);
        verify(permissionRepository).findById(permission.getId());
        verify(permissionRepository).save(permission);
    }

    @Test
    public void testSavePermissionConflict() {
        Permission permission = new Permission();
        permission.setId(1L);

        when(permissionRepository.findById(permission.getId())).thenReturn(Optional.of(permission));

        assertThrows(ConflictException.class, () -> permissionService.save(permission));
        verify(permissionRepository).findById(permission.getId());
        verify(permissionRepository, never()).save(any());
    }

    @Test
    public void testUpdatePermissionSuccess() {
        Permission permission = new Permission();
        permission.setId(1L);

        when(permissionRepository.findById(permission.getId())).thenReturn(Optional.of(permission));
        when(permissionRepository.save(permission)).thenReturn(permission);

        Permission updatedPermission = permissionService.update(permission);

        assertNotNull(updatedPermission);
        verify(permissionRepository).findById(permission.getId());
        verify(permissionRepository).save(permission);
    }

    @Test
    public void testUpdatePermissionNotFound() {
        Permission permission = new Permission();
        permission.setId(1L);

        when(permissionRepository.findById(permission.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> permissionService.update(permission));
        verify(permissionRepository).findById(permission.getId());
        verify(permissionRepository, never()).save(any());
    }

    @Test
    public void testFindByIdSuccess() {
        Long permissionId = 1L;
        Permission permission = new Permission();
        permission.setId(permissionId);

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.of(permission));

        Permission foundPermission = permissionService.findById(permissionId);

        assertNotNull(foundPermission);
        assertEquals(permissionId, foundPermission.getId());
        verify(permissionRepository).findById(permissionId);
    }

    @Test
    public void testFindByIdNotFound() {
        Long permissionId = 1L;

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> permissionService.findById(permissionId));
        verify(permissionRepository).findById(permissionId);
    }

    @Test
    public void testDeletePermissionSuccess() {
        Long permissionId = 1L;
        Permission permission = new Permission();
        permission.setId(permissionId);

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.of(permission));

        permissionService.delete(permissionId);

        verify(permissionRepository).findById(permissionId);
        verify(permissionRepository).deleteById(permissionId);
    }

    @Test
    public void testDeletePermissionNotFound() {
        Long permissionId = 1L;

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> permissionService.delete(permissionId));
        verify(permissionRepository).findById(permissionId);
        verify(permissionRepository, never()).deleteById(any());
    }

    @Test
    public void testFindAllPermissions() {
        List<Permission> permissions = List.of(new Permission(), new Permission());

        when(permissionRepository.findAll()).thenReturn(permissions);

        List<Permission> foundPermissions = permissionService.findAll();

        assertEquals(2, foundPermissions.size());
        verify(permissionRepository).findAll();
    }
}


