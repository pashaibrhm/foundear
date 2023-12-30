package com.harmonious.foundear.service.user.impl;

import com.harmonious.foundear.dto.user.UserDto;
import com.harmonious.foundear.entity.user.User;
import com.harmonious.foundear.repository.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private AutoCloseable closeable;
    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        // Arrange
        List<User> users = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserDto> result = userService.getAllUsers();

        // Assert
        assertEquals(users.size(), result.size());
    }

    @Test
    void getUserById_shouldReturnUserIfExists() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<UserDto> result = userService.getUserById(userId);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void getUserById_shouldReturnEmptyOptionalIfUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<UserDto> result = userService.getUserById(userId);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void createUser_shouldSaveNewUser() {
        // Arrange
        UserDto userDto = new UserDto();
        User newUser = new User();
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        // Act
        User result = userService.createUser(userDto);

        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser_shouldUpdateExistingUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = new UserDto();
        User existingUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // Act
        User result = userService.updateUser(userId, userDto);

        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser_shouldCreateNewUserIfUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = new UserDto();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // Act
        User result = userService.updateUser(userId, userDto);

        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deleteUser_shouldDeleteUser() {
        // Arrange
        UUID userId = UUID.randomUUID();

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }
}