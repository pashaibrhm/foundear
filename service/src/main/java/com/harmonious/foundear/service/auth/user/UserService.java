package com.harmonious.foundear.service.auth.user;

import com.harmonious.foundear.dto.auth.user.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(UUID userId);

    UserDto createUser(UserDto userDto);

    Optional<UserDto> updateUser(UUID userId, UserDto userDto);

    Optional<UserDto> deleteUser(UUID userId);

    Optional<UserDto> softDeleteUser(UUID userId);
}
