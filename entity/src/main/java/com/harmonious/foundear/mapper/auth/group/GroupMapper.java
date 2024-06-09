package com.harmonious.foundear.mapper.auth.group;

import com.harmonious.foundear.dto.auth.group.GroupDto;
import com.harmonious.foundear.entity.auth.Group;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    Group toEntity(GroupDto groupDto);

    List<Group> toEntities(List<GroupDto> groupDtos);

    @AfterMapping
    default void linkGroupFunctionPermissions(@MappingTarget Group group) {
        group.getGroupFunctionPermissions().forEach(
                groupFunctionPermission -> groupFunctionPermission.setGroup(group));
    }

    @AfterMapping
    default void linkUsers(@MappingTarget Group group) {
        group.getUsers().forEach(user -> user.setGroup(group));
    }

    GroupDto toDto(Group group);

    List<GroupDto> toDtos(List<Group> groups);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Group partialUpdate(GroupDto groupDto, @MappingTarget Group group);
}