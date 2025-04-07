package com.harmonious.foundear.service.auth.user.impl;

import com.harmonious.foundear.dto.auth.user.UserDto;
import com.harmonious.foundear.entity.auth.User;
import com.harmonious.foundear.mapper.auth.user.UserMapper;
import com.harmonious.foundear.repository.auth.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

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

        // 1. Create input DTO
        UserDto userDto = UserDto.createDummyUserDto();
        userDto.setId(userId);

        // 2. Mock DTO -> Entity conversion
        User convertedUser = User.createDummyUser();
        convertedUser.setId(userId);
        when(userMapper.toEntity(userDto)).thenReturn(convertedUser);

        // 3. Mock repository responses
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(User.createDummyUser())); // Any existing user
        when(userRepository.save(convertedUser))
                .thenReturn(convertedUser); // Return the same converted user

        // 4. Mock Entity -> DTO conversion
        UserDto expectedUserDto = UserDto.createDummyUserDto();
        expectedUserDto.setId(userId);
        when(userMapper.toDto(convertedUser)).thenReturn(expectedUserDto);

        // Act
        Optional<UserDto> result = userService.updateUser(userId, userDto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedUserDto, result.get());

        // Verify interactions
        verify(userRepository).findById(userId);
        verify(userRepository).save(convertedUser);
        verify(userMapper).toEntity(userDto);
        verify(userMapper).toDto(convertedUser);
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
    void softDeleteUser_shouldSoftDeleteExistingUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User(); // Create user instance
        user.setId(userId);
        UserDto userDto = new UserDto(); // Create UserDto instance
        userDto.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        // Act
        Optional<UserDto> result = userService.softDeleteUser(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toDto(user);
        assertTrue(result.isPresent());
        assertEquals(userDto, result.get());
    }

    @Test
    void softDeleteUser_shouldReturnEmptyIfUserNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<UserDto> result = userService.softDeleteUser(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(0)).save(any(User.class));
        verify(userMapper, times(0)).toDto(any(User.class));
        assertFalse(result.isPresent());
    }

    @Test
    void hardDeleteUser_shouldDeleteExistingUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User(); // Assuming a constructor or a builder pattern to create a user
        user.setId(userId);
        UserDto userDto = new UserDto(); // Assuming a constructor or a builder pattern to create a user DTO
        userDto.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        // Act
        Optional<UserDto> result = userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(user);
        verify(userMapper, times(1)).toDto(user);
        assertTrue(result.isPresent());
        assertEquals(userDto, result.get());
    }

    @Test
    void hardDeleteUser_shouldReturnEmptyIfUserNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<UserDto> result = userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(0)).delete(any(User.class));
        verify(userMapper, times(0)).toDto(any(User.class));
        assertFalse(result.isPresent());
    }
}