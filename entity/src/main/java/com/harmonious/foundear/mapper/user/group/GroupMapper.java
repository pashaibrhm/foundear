package com.harmonious.foundear.mapper.user.group;

import com.harmonious.foundear.dto.user.group.GroupDto;
import com.harmonious.foundear.entity.user.Group;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {
    Group toEntity(GroupDto groupDto);

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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Group partialUpdate(GroupDto groupDto, @MappingTarget Group group);
}