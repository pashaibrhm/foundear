package com.harmonious.foundear.service.user.impl;

import com.harmonious.foundear.dto.user.UserDto;
import com.harmonious.foundear.entity.user.User;
import com.harmonious.foundear.mapper.user.UserMapper;
import com.harmonious.foundear.repository.user.UserRepository;
import com.harmonious.foundear.service.user.UserService;
import lombok.Getter;
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

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userEntities = userRepository.findAll();
        return userMapper.toDtos(userEntities);
    }

    @Override
    public Optional<UserDto> getUserById(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(userMapper::toDto);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Override
    public Optional<UserDto> updateUser(UUID userId, UserDto userDto) {
        Objects.requireNonNull(userDto, "UserDto cannot be null");

        if (!Objects.equals(userId, userDto.getUserId())) {
            throw new IllegalArgumentException("UserDto id does not match with userId");
        }

        Optional<User> optionalUser = userRepository.findById(userId);

        return optionalUser.map(user -> {
            // Update user fields with values from userDto
            user = this.copyPropertiesFromDto(userDto);

            // Convert and return the updated user as UserDto
            return userMapper.toDto(userRepository.save(user));
        });
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    /**
     * @param userDto Dto object to be copied into the entity object without the id
     * @return User entity object that has copied the properties from the dto object
     */
    private User copyPropertiesFromDto(UserDto userDto) {
        User user = new User();

        user.setUserName(userDto.getUserName());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserPassword(userDto.getUserPassword());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setCreatedBy(userDto.getCreatedBy());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setUpdatedBy(userDto.getUpdatedBy());
        user.setApprovedAt(userDto.getApprovedAt());
        user.setApprovedBy(userDto.getApprovedBy());
        user.setIsDeleted(userDto.getIsDeleted());

        return user;
    }
}
