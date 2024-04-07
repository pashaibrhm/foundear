package com.harmonious.foundear.mapper.user;

import com.harmonious.foundear.dto.user.UserDto;
import com.harmonious.foundear.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<User> toEntities(List<UserDto> userDtos);

    List<UserDto> toDtos(List<User> users);
}
