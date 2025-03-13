package com.UserManager;

import com.UserManager.dao.UserRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.User;
import com.UserManager.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testAddUserSuccess() {
        User user = new User();
        user.setId(1L);
        user.setNationalCode("123456789");
        user.setUsername("testUser");

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        when(userRepository.findByNationalCode(user.getNationalCode())).thenReturn(Optional.empty());
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        verify(userRepository).save(user);
    }

    @Test
    public void testAddUserConflictById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        assertThrows(ConflictException.class, () -> userService.save(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testAddUserConflictByNationalCode() {
        User user = new User();
        user.setNationalCode("123456789");

        when(userRepository.findByNationalCode(user.getNationalCode())).thenReturn(Optional.of(user));

        assertThrows(ConflictException.class, () -> userService.save(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testAddUserConflictByUsername() {
        User user = new User();
        user.setUsername("testUser");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        assertThrows(ConflictException.class, () -> userService.save(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testUpdateUserSuccess() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.update(user);

        assertNotNull(updatedUser);
        verify(userRepository).save(user);
    }

    @Test
    public void testUpdateUserNotFound() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.update(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testDeleteUserSuccess() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.delete(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    public void testDeleteUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.delete(userId));
        verify(userRepository, never()).deleteById(userId);
    }

    @Test
    public void testFindByIdSuccess() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(userId);

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.findById(userId));
    }

    @Test
    public void testFindByUsernameSuccess() {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        User foundUser = userService.findByUsername(username);

        assertNotNull(foundUser);
        assertEquals(username, foundUser.getUsername());
    }

    @Test
    public void testFindByUsernameNotFound() {
        String username = "testUser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.findByUsername(username));
    }

    @Test
    public void testFindByNationalCodeSuccess() {
        String nationalCode = "123456789";
        User user = new User();
        user.setNationalCode(nationalCode);

        when(userRepository.findByNationalCode(nationalCode)).thenReturn(Optional.of(user));

        User foundUser = userService.findByNationalCode(nationalCode);

        assertNotNull(foundUser);
        assertEquals(nationalCode, foundUser.getNationalCode());
    }

    @Test
    public void testFindByNationalCodeNotFound() {
        String nationalCode = "123456789";

        when(userRepository.findByNationalCode(nationalCode)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.findByNationalCode(nationalCode));
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = List.of(new User(), new User());

        when(userRepository.findAll()).thenReturn(users);

        List<User> foundUsers = userService.findAll();

        assertEquals(2, foundUsers.size());
        verify(userRepository).findAll();
    }

    @Test
    public void testPagingUsers() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> usersPage = new PageImpl<>(List.of(new User(), new User()));

        when(userRepository.findAll(pageable)).thenReturn(usersPage);

        Page<User> resultPage = userService.paging(pageable);

        assertEquals(2, resultPage.getContent().size());
        verify(userRepository).findAll(pageable);
    }
}

