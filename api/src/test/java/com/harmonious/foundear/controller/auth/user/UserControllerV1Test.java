package com.harmonious.foundear.controller.auth.user;

import com.harmonious.foundear.dto.auth.user.UserDto;
import com.harmonious.foundear.exception.ResourceNotFoundException;
import com.harmonious.foundear.response.ApiResponse;
import com.harmonious.foundear.service.auth.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

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
        ResponseEntity<ApiResponse<List<UserDto>>> response = userControllerV1.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, Objects.requireNonNull(response.getBody()).getData());
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() { // Renamed slightly for clarity
        // Arrange
        UUID userId = UUID.randomUUID();
        // Create a specific UserDto instance to use for mocking AND assertion
        UserDto expectedUserDto = new UserDto(/* maybe set some fields if needed */);
        // Mock the service to return an Optional containing THIS specific instance
        when(userService.getUserById(userId)).thenReturn(Optional.of(expectedUserDto));

        // Act
        // *** Directly assign the result, as the controller returns ResponseEntity ***
        ResponseEntity<ApiResponse<UserDto>> responseEntity = userControllerV1.getUserById(userId);

        // Assert
        assertNotNull(responseEntity, "ResponseEntity should not be null");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "HTTP Status should be OK");

        ApiResponse<UserDto> responseBody = responseEntity.getBody();
        assertNotNull(responseBody, "Response body should not be null");
        // Optionally check the success message if your ResponseUtil/ApiResponse includes one
        assertEquals("User retrieved successfully.", responseBody.getMessage(), "Response message should match");

        // *** Directly compare the data in the body with the exact instance we expect ***
        assertEquals(expectedUserDto, responseBody.getData(), "Response data should match the expected UserDto");

        // Verify the service method was called
        verify(userService, times(1)).getUserById(userId);
        verifyNoMoreInteractions(userService); // Optional: ensure no other service methods were called
    }

    @Test
    void getUserById_shouldThrowResourceNotFoundException_whenUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();
        String expectedMessage = String.format("User not found: userId [%s]", userId); // Match exception format
        when(userService.getUserById(userId)).thenReturn(Optional.empty()); // Simulate user not found

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            // Call the method that is expected to throw
            userControllerV1.getUserById(userId);
        });

        // Optionally, assert details about the exception
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals("userId", exception.getField());

        // Verify that the service method was indeed called
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void createUser_shouldReturnCreatedUserWith201Status() {
        // Arrange
        UserDto inputDto = new UserDto();
        UserDto createdUser = new UserDto(); // You might want to set some fields
        when(userService.createUser(inputDto)).thenReturn(createdUser);

        // Act
        ResponseEntity<ApiResponse<UserDto>> response = userControllerV1.createUser(inputDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ApiResponse<UserDto> body = response.getBody();
        assertNotNull(body);
        assertEquals("User created successfully.", body.getMessage());
        assertEquals(createdUser, body.getData());
        verify(userService).createUser(inputDto);
    }

    @Test
    void updateUser_shouldReturnUpdatedUserWhenExists() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto inputDto = new UserDto();
        UserDto updatedUser = new UserDto();
        when(userService.updateUser(userId, inputDto)).thenReturn(Optional.of(updatedUser));

        // Act
        ResponseEntity<ApiResponse<UserDto>> response = userControllerV1.updateUser(userId, inputDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ApiResponse<UserDto> body = response.getBody();
        assertNotNull(body);
        assertEquals("User updated successfully.", body.getMessage());
        assertEquals(updatedUser, body.getData());
    }

    @Test
    void updateUser_shouldThrowNotFoundWhenUserMissing() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto inputDto = new UserDto();
        when(userService.updateUser(userId, inputDto)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            userControllerV1.updateUser(userId, inputDto);
        });

        verify(userService).updateUser(userId, inputDto);
    }

    @Test
    void deleteUser_shouldReturnNoContentWhenSuccessful() {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userService.softDeleteUser(userId)).thenReturn(Optional.of(new UserDto()));

        // Act
        ResponseEntity<ApiResponse<Object>> response = userControllerV1.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("User deleted successfully.", response.getBody().getMessage());
        verify(userService).softDeleteUser(userId);
    }

    @Test
    void deleteUser_shouldThrowNotFoundWhenUserMissing() {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userService.softDeleteUser(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            userControllerV1.deleteUser(userId);
        });
    }

    @Test
    void deleteUser_shouldReturn500OnServiceException() {
        // Arrange
        UUID userId = UUID.randomUUID();

        // Mock service to throw exception directly
        when(userService.softDeleteUser(userId))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userControllerV1.deleteUser(userId);
        });

        assertEquals("Database error", exception.getMessage());

        // Optional: Verify service interaction
        verify(userService).softDeleteUser(userId);
    }
}
