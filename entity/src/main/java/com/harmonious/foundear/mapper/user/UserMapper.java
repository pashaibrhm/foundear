package com.harmonious.foundear.mapper.user;

import com.harmonious.foundear.dto.user.UserDto;
import com.harmonious.foundear.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "userEmail", source = "userEmail")
    @Mapping(target = "userPassword", source = "userPassword")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "updatedBy", source = "updatedBy")
    @Mapping(target = "approvedAt", source = "approvedAt")
    @Mapping(target = "approvedBy", source = "approvedBy")
    @Mapping(target = "isDeleted", source = "isDeleted")
    UserDto toDto(User user);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "userEmail", source = "userEmail")
    @Mapping(target = "userPassword", source = "userPassword")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "updatedBy", source = "updatedBy")
    @Mapping(target = "approvedAt", source = "approvedAt")
    @Mapping(target = "approvedBy", source = "approvedBy")
    @Mapping(target = "isDeleted", source = "isDeleted")
    User toEntity(UserDto userDto);

    List<User> toEntities(List<UserDto> userDtos);

    List<UserDto> toDtos(List<User> users);
}
