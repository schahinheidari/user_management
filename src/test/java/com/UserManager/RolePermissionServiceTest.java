package com.UserManager;

import com.UserManager.dao.RolePermissionRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.RolePermission;
import com.UserManager.service.RolePermissionService;
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
public class RolePermissionServiceTest {

    @Mock
    private RolePermissionRepository rolePermissionRepository;

    @InjectMocks
    private RolePermissionService rolePermissionService;

    @Test
    public void testSaveRolePermissionSuccess() {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(1L);

        when(rolePermissionRepository.findById(rolePermission.getId())).thenReturn(Optional.empty());
        when(rolePermissionRepository.save(rolePermission)).thenReturn(rolePermission);

        RolePermission savedRolePermission = rolePermissionService.save(rolePermission);

        assertNotNull(savedRolePermission);
        verify(rolePermissionRepository).findById(rolePermission.getId());
        verify(rolePermissionRepository).save(rolePermission);
    }

    @Test
    public void testSaveRolePermissionConflict() {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(1L);

        when(rolePermissionRepository.findById(rolePermission.getId())).thenReturn(Optional.of(rolePermission));

        assertThrows(ConflictException.class, () -> rolePermissionService.save(rolePermission));
        verify(rolePermissionRepository).findById(rolePermission.getId());
        verify(rolePermissionRepository, never()).save(any());
    }

    @Test
    public void testUpdateRolePermissionSuccess() {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(1L);

        when(rolePermissionRepository.findById(rolePermission.getId())).thenReturn(Optional.of(rolePermission));
        when(rolePermissionRepository.save(rolePermission)).thenReturn(rolePermission);

        RolePermission updatedRolePermission = rolePermissionService.update(rolePermission);

        assertNotNull(updatedRolePermission);
        verify(rolePermissionRepository).findById(rolePermission.getId());
        verify(rolePermissionRepository).save(rolePermission);
    }

    @Test
    public void testUpdateRolePermissionNotFound() {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(1L);

        when(rolePermissionRepository.findById(rolePermission.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rolePermissionService.update(rolePermission));
        verify(rolePermissionRepository).findById(rolePermission.getId());
        verify(rolePermissionRepository, never()).save(any());
    }

    @Test
    public void testFindByIdSuccess() {
        Long id = 1L;
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(id);

        when(rolePermissionRepository.findById(id)).thenReturn(Optional.of(rolePermission));

        RolePermission foundRolePermission = rolePermissionService.findById(id);

        assertNotNull(foundRolePermission);
        assertEquals(id, foundRolePermission.getId());
        verify(rolePermissionRepository).findById(id);
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;

        when(rolePermissionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rolePermissionService.findById(id));
        verify(rolePermissionRepository).findById(id);
    }

    @Test
    public void testDeleteRolePermissionSuccess() {
        Long id = 1L;
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(id);

        when(rolePermissionRepository.findById(id)).thenReturn(Optional.of(rolePermission));

        rolePermissionService.delete(id);

        verify(rolePermissionRepository).findById(id);
        verify(rolePermissionRepository).deleteById(id);
    }

    @Test
    public void testDeleteRolePermissionNotFound() {
        Long id = 1L;

        when(rolePermissionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rolePermissionService.delete(id));
        verify(rolePermissionRepository).findById(id);
        verify(rolePermissionRepository, never()).deleteById(any());
    }

    @Test
    public void testFindAllRolePermissions() {
        List<RolePermission> rolePermissions = List.of(new RolePermission(), new RolePermission());

        when(rolePermissionRepository.findAll()).thenReturn(rolePermissions);

        List<RolePermission> foundRolePermissions = rolePermissionService.findAll();

        assertEquals(2, foundRolePermissions.size());
        verify(rolePermissionRepository).findAll();
    }
}

