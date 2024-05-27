package com.harmonious.foundear.service.user.group.impl;

import com.harmonious.foundear.dto.user.group.GroupDto;
import com.harmonious.foundear.entity.user.Group;
import com.harmonious.foundear.mapper.user.group.GroupMapper;
import com.harmonious.foundear.repository.user.group.GroupRepository;
import com.harmonious.foundear.service.user.group.GroupService;
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
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    private final Logger logger;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.logger = LoggerFactory.getLogger(GroupServiceImpl.class);
    }

    @Override
    public List<GroupDto> getAllGroups() {
        logger.info("Fetching all groups");
        List<Group> groupEntities = groupRepository.findAll();
        return groupMapper.toDtos(groupEntities);
    }

    @Override
    public Optional<GroupDto> getGroupById(UUID groupId) {
        logInfo(groupId);
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            return Optional.of(groupMapper.toDto(group));
        }
        logger.warn("Group with ID {} not found", groupId);
        return Optional.empty();
    }

    @Override
    public Optional<GroupDto> createGroup(GroupDto groupDto) {
        logger.info("Creating group: {}", groupDto);
        return Optional.of(groupMapper.toDto(groupRepository.save(groupMapper.toEntity(groupDto))));
    }

    @Override
    public Optional<GroupDto> updateGroup(UUID groupId, GroupDto groupDto) {
        Objects.requireNonNull(groupDto, "GroupDto cannot be null");

        if (!Objects.equals(groupId, groupDto.getId())) {
            throw new IllegalArgumentException("GroupDto id does not match with groupId");
        }

        logInfo(groupId);
        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        if (optionalGroup.isPresent()) {
            Group existingGroup = groupMapper.toEntity(groupDto);

            logger.info("Updating group: {}", groupDto);
            Group updatedGroup = groupRepository.save(existingGroup);
            return Optional.of(groupMapper.toDto(updatedGroup));
        } else {
            logger.warn("Group with ID {} not found for update", groupId);
            return Optional.empty();
        }
    }

    private void logInfo(UUID groupId) {
        logger.info("Fetching group by ID: {}", groupId);
    }

    @Override
    public Optional<GroupDto> deleteGroup(UUID groupId) {
        logger.info("Deleting group with ID: {}", groupId);
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            groupRepository.delete(group);
            return Optional.of(groupMapper.toDto(group));
        }
        logger.warn("Group with ID {} not found for deletion", groupId);
        return Optional.empty();
    }

    @Override
    public Optional<GroupDto> softDeleteGroup(UUID groupId) {
        try {
            return Optional.of(groupRepository.findById(groupId)
                    .map(group -> {
                        group.setIsDeleted((short) 1);
                        logger.info("Soft deleting group: {}", groupId);
                        groupRepository.save(group);
                        return group;
                    })
                    .orElseThrow(() -> {
                        logger.warn("User with ID {} not found for soft delete", groupId);
                        return new NoSuchElementException("User with ID " + groupId + " not found.");
                    })).map(groupMapper::toDto);
        } catch (NoSuchElementException e) {
            // Optionally handle the exception or rethrow
            return Optional.empty(); // Or rethrow if preferred
        }
    }
}
