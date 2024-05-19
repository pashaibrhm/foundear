package com.harmonious.foundear.service.user.impl;

import com.harmonious.foundear.dto.user.user.UserDto;
import com.harmonious.foundear.entity.user.User;
import com.harmonious.foundear.mapper.user.user.UserMapper;
import com.harmonious.foundear.repository.user.UserRepository;
import com.harmonious.foundear.service.user.UserService;
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

    private final Logger logger;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.logger = LoggerFactory.getLogger(UserServiceImpl.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        logger.info("Fetching all users");
        List<User> userEntities = userRepository.findAll();
        return userMapper.toDtos(userEntities);
    }

    @Override
    public Optional<UserDto> getUserById(UUID userId) {
        logger.info("Fetching user by ID: {}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);
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

        if (!Objects.equals(userId, userDto.getUserId())) {
            throw new IllegalArgumentException("UserDto id does not match with userId");
        }

        logger.info("Fetching user by ID: {}", userId);
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

    @Override
    public void softDeleteUser(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setIsDeleted(true);

            logger.info("Soft deleting user: {}", userId);
            userRepository.save(existingUser);
        } else {
            logger.warn("User with ID {} not found for soft delete", userId);
            throw new NoSuchElementException("User with ID " + userId + " not found.");
        }
    }

    @Override
    public void hardDeleteUser(UUID userId) {
        logger.info("Hard deleting user: {}", userId);
        userRepository.deleteById(userId);
    }
}
