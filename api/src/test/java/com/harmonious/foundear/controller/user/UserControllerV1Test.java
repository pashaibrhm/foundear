package com.harmonious.foundear.controller.user;

import com.harmonious.foundear.dto.user.user.UserDto;
import com.harmonious.foundear.entity.regional.*;
import com.harmonious.foundear.entity.user.Group;
import com.harmonious.foundear.service.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerV1Test {

    private AutoCloseable closeable;

    @Mock
    private UserService userService;

    private UserControllerV1 userControllerV1;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        userControllerV1 = new UserControllerV1(userService);
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
        ResponseEntity<List<UserDto>> response = userControllerV1.getAllUsers();

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
        ResponseEntity<Optional<UserDto>> response = userControllerV1.getUserById(userId);

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
        ResponseEntity<Optional<UserDto>> response = userControllerV1.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createUser_shouldReturnCreatedStatus() {
        // Arrange
        UserDto userDto = new UserDto();

        // Act
        ResponseEntity<UserDto> response = userControllerV1.createUser(userDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateUser_shouldReturnOkStatusIfUserExists() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = new UserDto();

        // Act
        ResponseEntity<Optional<UserDto>> response = userControllerV1.updateUser(userId, userDto);

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
        ResponseEntity<Optional<UserDto>> response = userControllerV1.updateUser(userId, userDto);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteUser_shouldReturnNoContentIfUserExists() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = UserDto.builder()
                .userId(userId)
                .createdAt(Instant.now())
                .createdBy(UUID.randomUUID())
                .approvedBy(UUID.randomUUID())
                .isDeleted(false)
                .city(new City())
                .district(new District())
                .village(new Village())
                .country(new Country())
                .approvedAt(Instant.now())
                .province(new Province())
                .group(new Group())
                .firstName("John")
                .middleName("A")
                .lastName("Doe")
                .username("johndoe")
                .email("johndoe@example.com")
                .password("securepassword")
                .addressDetail("123 Main St")
                .lastUpdatedBy(UUID.randomUUID())
                .lastUpdatedAt(Instant.now())
                .lastVersionAt(Instant.now())
                .lockCount(BigDecimal.ZERO)
                .isLocked((short) 0)
                .failedLoginAttempts(new LinkedHashSet<>())
                .userSessions(new LinkedHashSet<>())
                .build();
        when(userService.getUserById(userId)).thenReturn(Optional.of(userDto));

        // Act
        ResponseEntity<Void> response = userControllerV1.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).softDeleteUser(userId);
    }

    @Test
    void deleteUser_shouldReturnNotFoundIfUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = userControllerV1.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, never()).softDeleteUser(userId);
    }

    @Test
    void deleteUser_shouldReturnInternalServerErrorOnException() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = UserDto.builder()
                .userId(userId)
                .createdAt(Instant.now())
                .createdBy(UUID.randomUUID())
                .approvedBy(UUID.randomUUID())
                .isDeleted(false)
                .city(new City())
                .district(new District())
                .village(new Village())
                .country(new Country())
                .approvedAt(Instant.now())
                .province(new Province())
                .group(new Group())
                .firstName("John")
                .middleName("A")
                .lastName("Doe")
                .username("johndoe")
                .email("johndoe@example.com")
                .password("securepassword")
                .addressDetail("123 Main St")
                .lastUpdatedBy(UUID.randomUUID())
                .lastUpdatedAt(Instant.now())
                .lastVersionAt(Instant.now())
                .lockCount(BigDecimal.ZERO)
                .isLocked((short) 0)
                .failedLoginAttempts(new LinkedHashSet<>())
                .userSessions(new LinkedHashSet<>())
                .build();
        when(userService.getUserById(userId)).thenReturn(Optional.of(userDto));
        doThrow(new RuntimeException("Database error")).when(userService).softDeleteUser(userId);

        // Act
        ResponseEntity<Void> response = userControllerV1.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(userService, times(1)).softDeleteUser(userId);
    }
}
