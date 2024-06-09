package com.harmonious.foundear.mapper.auth.user;

import com.harmonious.foundear.dto.auth.user.UserDto;
import com.harmonious.foundear.entity.auth.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<User> toEntities(List<UserDto> userDtos);

    List<UserDto> toDtos(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);
}
