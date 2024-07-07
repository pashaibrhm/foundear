package com.harmonious.foundear.controller.auth.group;

import com.harmonious.foundear.dto.auth.group.GroupDto;
import com.harmonious.foundear.service.auth.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupControllerV1 {

    private final GroupService groupService;

    @Autowired
    public GroupControllerV1(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groups = groupService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<Optional<GroupDto>> getGroupById(@PathVariable UUID groupId) {
        try {
            Optional<GroupDto> group = groupService.getGroupById(groupId);

            if (group.isPresent()) {
                return new ResponseEntity<>(group, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        GroupDto createdGroup = groupService.createGroup(groupDto);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<Optional<GroupDto>> updateGroup(@PathVariable UUID groupId, @RequestBody GroupDto groupDto) {
        try {
            Optional<GroupDto> updatedGroup = groupService.updateGroup(groupId, groupDto);
            return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Optional<GroupDto>> deleteGroup(@PathVariable UUID groupId) {
        Optional<GroupDto> existingGroupOptional = groupService.getGroupById(groupId);

        if (existingGroupOptional.isPresent()) {
            try {
                Optional<GroupDto> deletedGroupOptional = groupService.softDeleteGroup(groupId);
                if (deletedGroupOptional.isPresent()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
