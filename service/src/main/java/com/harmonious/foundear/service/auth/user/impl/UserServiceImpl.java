package com.harmonious.foundear.service.auth.user.impl;

import com.harmonious.foundear.dto.auth.user.UserDto;
import com.harmonious.foundear.entity.auth.User;
import com.harmonious.foundear.mapper.auth.user.UserMapper;
import com.harmonious.foundear.repository.auth.user.UserRepository;
import com.harmonious.foundear.service.auth.user.UserService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Getter
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        logger.info("Fetching all users");
        List<User> userEntities = userRepository.findAll();
        return userMapper.toDtos(userEntities);
    }

    @Override
    public Optional<UserDto> getUserById(UUID userId) {
        logInfo(userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            logger.warn("User with ID {} not found", userId);
            return Optional.empty();
        }
        return optionalUser.map(userMapper::toDto);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        logger.info("Creating user: {}", userDto);
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Override
    public Optional<UserDto> updateUser(UUID userId, UserDto userDto) {
        Objects.requireNonNull(userDto, "UserDto cannot be null");

        if (!Objects.equals(userId, userDto.getId())) {
            throw new IllegalArgumentException("UserDto id does not match with userId");
        }

        logInfo(userId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = userMapper.toEntity(userDto);

            logger.info("Updating user: {}", userDto);
            User updatedUser = userRepository.save(existingUser);
            return Optional.of(userMapper.toDto(updatedUser));
        } else {
            logger.warn("User with ID {} not found for update", userId);
            return Optional.empty();
        }
    }

    private void logInfo(UUID userId) {
        logger.info("Fetching user by ID: {}", userId);
    }

    @Override
    public Optional<UserDto> deleteUser(UUID userId) {
        logger.info("Deleting user: {}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            userRepository.delete(existingUser);
            return Optional.of(userMapper.toDto(existingUser));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> softDeleteUser(UUID userId) {
        try {
            return Optional.of(userRepository.findById(userId)
                    .map(user -> {
                        user.setIsDeleted((short) 1);
                        logger.info("Soft deleting user: {}", userId);
                        userRepository.save(user);
                        return user;
                    })
                    .orElseThrow(() -> {
                        logger.warn("User with ID {} not found for soft delete", userId);
                        return new NoSuchElementException("User with ID " + userId + " not found.");
                    })).map(userMapper::toDto);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
}
