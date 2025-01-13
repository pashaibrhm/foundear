package com.harmonious.foundear.controller.auth.user;

import com.harmonious.foundear.dto.auth.user.UserDto;
import com.harmonious.foundear.exception.ResourceNotFoundException;
import com.harmonious.foundear.response.ApiResponse;
import com.harmonious.foundear.response.ResponseUtil;
import com.harmonious.foundear.service.auth.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {

    private final UserService userService;

    @Autowired
    public UserControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseUtil.success(200, "Users retrieved successfully.", users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable UUID userId) {
        UserDto user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", "userId", userId));
        return ResponseUtil.success(200, "User retrieved successfully.", user);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseUtil.success(201, "User created successfully.", createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable UUID userId, @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userId, userDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", "userId", userId));
        return ResponseUtil.success(200, "User updated successfully.", updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> deleteUser(@PathVariable UUID userId) {
        userService.softDeleteUser(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found or cannot be deleted", "userId", userId));
        return ResponseUtil.success(204, "User deleted successfully.", null);
    }
}
