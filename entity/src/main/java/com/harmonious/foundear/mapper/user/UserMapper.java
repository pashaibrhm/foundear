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
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "approvedBy", source = "approvedBy")
    @Mapping(target = "isDeleted", source = "isDeleted")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "district", source = "district")
    @Mapping(target = "village", source = "village")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "approvedAt", source = "approvedAt")
    @Mapping(target = "province", source = "province")
    @Mapping(target = "group", source = "group")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "middleName", source = "middleName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "addressDetail", source = "addressDetail")
    @Mapping(target = "lastUpdatedBy", source = "lastUpdatedBy")
    @Mapping(target = "lastUpdatedAt", source = "lastUpdatedAt")
    @Mapping(target = "lastVersionAt", source = "lastVersionAt")
    @Mapping(target = "lockCount", source = "lockCount")
    @Mapping(target = "isLocked", source = "isLocked")
    @Mapping(target = "failedLoginAttempts", source = "failedLoginAttempts")
    @Mapping(target = "userSessions", source = "userSessions")
    UserDto toDto(User user);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "approvedBy", source = "approvedBy")
    @Mapping(target = "isDeleted", source = "isDeleted")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "district", source = "district")
    @Mapping(target = "village", source = "village")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "approvedAt", source = "approvedAt")
    @Mapping(target = "province", source = "province")
    @Mapping(target = "group", source = "group")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "middleName", source = "middleName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "addressDetail", source = "addressDetail")
    @Mapping(target = "lastUpdatedBy", source = "lastUpdatedBy")
    @Mapping(target = "lastUpdatedAt", source = "lastUpdatedAt")
    @Mapping(target = "lastVersionAt", source = "lastVersionAt")
    @Mapping(target = "lockCount", source = "lockCount")
    @Mapping(target = "isLocked", source = "isLocked")
    @Mapping(target = "failedLoginAttempts", source = "failedLoginAttempts")
    @Mapping(target = "userSessions", source = "userSessions")
    User toEntity(UserDto userDto);

    List<User> toEntities(List<UserDto> userDtos);

    List<UserDto> toDtos(List<User> users);
}
