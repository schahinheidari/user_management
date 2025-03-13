package com.UserManager;

import com.UserManager.dao.UserRoleRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.UserRole;
import com.UserManager.service.UserRoleService;
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
public class UserRoleServiceTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleService userRoleService;

    @Test
    public void testSaveUserRoleSuccess() {
        UserRole userRole = new UserRole();
        userRole.setId(1L);

        when(userRoleRepository.findById(userRole.getId())).thenReturn(Optional.empty());
        when(userRoleRepository.save(userRole)).thenReturn(userRole);

        UserRole savedUserRole = userRoleService.save(userRole);

        assertNotNull(savedUserRole);
        verify(userRoleRepository).findById(userRole.getId());
        verify(userRoleRepository).save(userRole);
    }

    @Test
    public void testSaveUserRoleConflict() {
        UserRole userRole = new UserRole();
        userRole.setId(1L);

        when(userRoleRepository.findById(userRole.getId())).thenReturn(Optional.of(userRole));

        assertThrows(ConflictException.class, () -> userRoleService.save(userRole));
        verify(userRoleRepository).findById(userRole.getId());
        verify(userRoleRepository, never()).save(any());
    }

    @Test
    public void testUpdateUserRoleSuccess() {
        UserRole userRole = new UserRole();
        userRole.setId(1L);

        when(userRoleRepository.findById(userRole.getId())).thenReturn(Optional.of(userRole));
        when(userRoleRepository.save(userRole)).thenReturn(userRole);

        UserRole updatedUserRole = userRoleService.update(userRole);

        assertNotNull(updatedUserRole);
        verify(userRoleRepository).findById(userRole.getId());
        verify(userRoleRepository).save(userRole);
    }

    @Test
    public void testUpdateUserRoleNotFound() {
        UserRole userRole = new UserRole();
        userRole.setId(1L);

        when(userRoleRepository.findById(userRole.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userRoleService.update(userRole));
        verify(userRoleRepository).findById(userRole.getId());
        verify(userRoleRepository, never()).save(any());
    }

    @Test
    public void testFindByIdSuccess() {
        Long id = 1L;
        UserRole userRole = new UserRole();
        userRole.setId(id);

        when(userRoleRepository.findById(id)).thenReturn(Optional.of(userRole));

        UserRole foundUserRole = userRoleService.findById(id);

        assertNotNull(foundUserRole);
        assertEquals(id, foundUserRole.getId());
        verify(userRoleRepository).findById(id);
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;

        when(userRoleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userRoleService.findById(id));
        verify(userRoleRepository).findById(id);
    }

    @Test
    public void testDeleteUserRoleSuccess() {
        Long id = 1L;
        UserRole userRole = new UserRole();
        userRole.setId(id);

        when(userRoleRepository.findById(id)).thenReturn(Optional.of(userRole));

        userRoleService.delete(id);

        verify(userRoleRepository).findById(id);
        verify(userRoleRepository).deleteById(id);
    }

    @Test
    public void testDeleteUserRoleNotFound() {
        Long id = 1L;

        when(userRoleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userRoleService.delete(id));
        verify(userRoleRepository).findById(id);
        verify(userRoleRepository, never()).deleteById(any());
    }

    @Test
    public void testFindAllUserRoles() {
        List<UserRole> userRoles = List.of(new UserRole(), new UserRole());

        when(userRoleRepository.findAll()).thenReturn(userRoles);

        List<UserRole> foundUserRoles = userRoleService.findAll();

        assertEquals(2, foundUserRoles.size());
        verify(userRoleRepository).findAll();
    }
}

