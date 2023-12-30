package com.harmonious.foundear.service.user;

import com.harmonious.foundear.dto.user.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(UUID userId);

    UserDto createUser(UserDto userDto);

    Optional<UserDto> updateUser(UUID userId, UserDto userDto);

    void deleteUser(UUID userId);
}
