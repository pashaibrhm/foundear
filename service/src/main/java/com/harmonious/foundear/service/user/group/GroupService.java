package com.harmonious.foundear.service.user.group;

import com.harmonious.foundear.dto.user.group.GroupDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupService {
    List<GroupDto> getAllGroups();
    Optional<GroupDto> getGroupById(UUID groupId);
    Optional<GroupDto> createGroup(GroupDto groupDto);
    Optional<GroupDto> updateGroup(UUID groupId, GroupDto groupDto);
    Optional<GroupDto> deleteGroup(UUID groupId);
    Optional<GroupDto> softDeleteGroup(UUID groupId);
}
