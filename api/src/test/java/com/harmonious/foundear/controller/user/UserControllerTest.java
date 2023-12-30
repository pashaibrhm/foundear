package com.harmonious.foundear.controller.user;

import com.harmonious.foundear.dto.user.UserDto;
import com.harmonious.foundear.service.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class UserControllerTest {

    private AutoCloseable closeable;

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        // Arrange
        List<UserDto> users = List.of(new UserDto(), new UserDto());
        when(userService.getAllUsers()).thenReturn(users);

        // Act
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void getUserById_shouldReturnUserIfExists() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Optional<UserDto> user = Optional.of(new UserDto());
        when(userService.getUserById(userId)).thenReturn(user);

        // Act
        ResponseEntity<Optional<UserDto>> response = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void getUserById_shouldReturnNotFoundIfUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Optional<UserDto>> response = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createUser_shouldReturnCreatedStatus() {
        // Arrange
        UserDto userDto = new UserDto();

        // Act
        ResponseEntity<UserDto> response = userController.createUser(userDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateUser_shouldReturnOkStatusIfUserExists() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = new UserDto();

        // Act
        ResponseEntity<Optional<UserDto>> response = userController.updateUser(userId, userDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateUser_shouldReturnNotFoundIfUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = new UserDto();
        doThrow(new RuntimeException()).when(userService).updateUser(userId, userDto);

        // Act
        ResponseEntity<Optional<UserDto>> response = userController.updateUser(userId, userDto);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteUser_shouldReturnNoContentIfUserExists() {
        // Arrange
        UUID userId = UUID.randomUUID();

        // Act
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteUser_shouldReturnNotFoundIfUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();
        doThrow(new RuntimeException()).when(userService).deleteUser(userId);

        // Act
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
