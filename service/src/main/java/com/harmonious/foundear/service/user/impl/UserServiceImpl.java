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
        return this.convertUserEntitiesToUserDtos(userEntities);
    }

    @Override
    public Optional<UserDto> getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(this::convertUserEntityToUserDto);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return this.convertUserEntityToUserDto(saveNewUser(userDto));
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

            // Save the updated user
            User updatedUser = userRepository.save(user);

            // Convert and return the updated user as UserDto
            return convertUserEntityToUserDto(updatedUser);
        });
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Define a new function that takes a userDto as a parameter and returns a user
     *
     * @param userDto the userDto to be created
     * @return User entity
     */
    public User saveNewUser(UserDto userDto) {
        // Convert the userDto to a user entity
        User newUser = convertUserDtoToUserEntity(userDto);
        // Save the user to the repository and return it
        return userRepository.save(newUser);
    }

    /**
     * @param userDto The Dto object to be converted
     * @return User entity
     */
    private User convertUserDtoToUserEntity(UserDto userDto) {
        return userMapper.toEntity(userDto);
    }

    /**
     * @param user the user to be converted into dto object
     * @return UserDto object
     */
    private UserDto convertUserEntityToUserDto(User user) {
        return userMapper.toDto(user);
    }

    /**
     * @param users The list of user entities to be converted
     * @return The list of converted user entities
     */
    private List<UserDto> convertUserEntitiesToUserDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(this.convertUserEntityToUserDto(user));
        }

        return userDtos;
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
