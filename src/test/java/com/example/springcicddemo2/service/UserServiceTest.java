package com.example.springcicddemo2.service;

import com.example.springcicddemo2.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void getAllUsers_shouldReturnInitialUsers() {
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void createUser_shouldCreateNewUser() {
        User newUser = new User(null, "엘사", "elsa@example.com");
        User created = userService.createUser(newUser);

        assertNotNull(created.getId());
        assertEquals("엘사", created.getName());
        assertEquals("elsa@example.com", created.getEmail());
    }

    @Test
    void getUserById_shouldReturnUser_whenExists() {
        List<User> users = userService.getAllUsers();
        Long existingId = users.get(0).getId();

        Optional<User> found = userService.getUserById(existingId);

        assertTrue(found.isPresent());
        assertEquals(existingId, found.get().getId());
    }

    @Test
    void getUserById_shouldReturnEmpty_whenNotExists() {
        Optional<User> found = userService.getUserById(999L);
        assertTrue(found.isEmpty());
    }

    @Test
    void updateUser_shouldUpdateExistingUser() {
        List<User> users = userService.getAllUsers();
        Long existingId = users.get(0).getId();

        User updatedDetails = new User(null, "수정된이름", "updated@example.com");
        Optional<User> updated = userService.updateUser(existingId, updatedDetails);

        assertTrue(updated.isPresent());
        assertEquals("수정된이름", updated.get().getName());
        assertEquals("updated@example.com", updated.get().getEmail());
    }

    @Test
    void
    deleteUser_shouldReturnTrue_whenUserExists() {
        List<User> users = userService.getAllUsers();
        Long existingId = users.get(0).getId();

        boolean deleted = userService.deleteUser(existingId);

        assertTrue(deleted);
        assertTrue(userService.getUserById(existingId).isEmpty());
    }

    @Test
    void deleteUser_shouldReturnFalse_whenUserNotExists() {
        boolean deleted = userService.deleteUser(999L);
        assertFalse(deleted);
    }
}