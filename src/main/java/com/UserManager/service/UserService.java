package com.UserManager.service;

import com.UserManager.dao.UserRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        checkIfUserExists(user);
        return userRepository.save(user);
    }

    private void checkIfUserExists(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            throw new ConflictException("User already exists by ID: " + user.getId());
        }
        if (userRepository.findByNationalCode(user.getNationalCode()).isPresent()) {
            throw new ConflictException("User already exists by National Code: " + user.getNationalCode());
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ConflictException("User already exists by Username: " + user.getUsername());
        }
    }


    public User update(User user) {
        findById(user.getId());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
    public User findById(Long id) {
        return findUser(() -> userRepository.findById(id), "User not found with ID: " + id);
    }

    public User findByUsername(String username) {
        return findUser(() -> userRepository.findByUsername(username), "User not found with username: " + username);
    }

    public User findByNationalCode(String nationalCode) {
        return findUser(() -> userRepository.findByNationalCode(nationalCode), "User not found with national code: " + nationalCode);
    }

    private User findUser(Supplier<Optional<User>> userSupplier, String errorMessage) {
        return userSupplier.get().orElseThrow(() -> new NotFoundException(errorMessage));
    }

    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

}
