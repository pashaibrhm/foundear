package com.harmonious.foundear.service.user.impl;

import com.harmonious.foundear.dto.user.UserDto;
import com.harmonious.foundear.entity.user.User;
import com.harmonious.foundear.mapper.user.UserMapper;
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
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private AutoCloseable closeable;
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        // Arrange
        User user1 = new User(/* set user properties */);
        User user2 = new User(/* set user properties */);
        List<User> userEntities = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userEntities);

        UserDto userDto1 = new UserDto(/* set userDto properties */);
        UserDto userDto2 = new UserDto(/* set userDto properties */);
        List<UserDto> expectedUserDtos = Arrays.asList(userDto1, userDto2);

        when(userMapper.toDtos(userEntities)).thenReturn(expectedUserDtos);

        // Act
        List<UserDto> actualUserDtos = userService.getAllUsers();

        // Assert
        assertEquals(expectedUserDtos, actualUserDtos);
    }

    @Test
    void getUserById_shouldReturnUserIfExists() {
        // Arrange
        UUID userId = UUID.randomUUID();

        User user = new User(/* set user properties */);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDto expectedUserDto = new UserDto(/* set userDto properties */);
        when(userMapper.toDto(user)).thenReturn(expectedUserDto);

        // Act
        Optional<UserDto> actualUserDto = userService.getUserById(userId);

        // Assert
        assertEquals(Optional.of(expectedUserDto), actualUserDto);
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
        UserDto userDto = new UserDto(/* set userDto properties */);

        User userEntity = new User(/* set userEntity properties */);
        when(userMapper.toEntity(userDto)).thenReturn(userEntity);

        User savedUserEntity = new User(/* set savedUserEntity properties */);
        when(userRepository.save(userEntity)).thenReturn(savedUserEntity);

        UserDto expectedUserDto = new UserDto(/* set expectedUserDto properties */);
        when(userMapper.toDto(savedUserEntity)).thenReturn(expectedUserDto);

        // Act
        UserDto actualUserDto = userService.createUser(userDto);

        // Assert
        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void updateUser_shouldUpdateExistingUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = new UserDto(/* set userDto properties */);
        userDto.setUserId(userId);

        User existingUser = new User(/* set existingUser properties */);
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        User updatedUser = new User(/* set updatedUser properties */);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);

        UserDto expectedUserDto = new UserDto(/* set expectedUserDto properties */);
        when(userMapper.toDto(updatedUser)).thenReturn(expectedUserDto);

        // Act
        Optional<UserDto> actualUserDto = userService.updateUser(userId, userDto);

        // Assert
        assertTrue(actualUserDto.isPresent());
        assertEquals(expectedUserDto, actualUserDto.get());
    }

    @Test
    void updateUser_shouldValidateIfTheUserNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = new UserDto(/* set userDto properties */);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(userId, userDto));
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