package com.harmonious.foundear.dto.user.group;

import com.harmonious.foundear.dto.user.user.UserDto;
import com.harmonious.foundear.entity.user.GroupFunctionPermission;
import com.harmonious.foundear.entity.user.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.harmonious.foundear.entity.user.Group}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDto implements Serializable {
    private UUID id;
    private UUID parentId;
    private Organization org;
    private String name;
    private UUID createdBy;
    private Instant createdAt;
    private UUID lastUpdatedBy;
    private Instant lastUpdatedAt;
    private UUID approvedBy;
    private Instant approvedAt;
    private Instant lastVersionAt;
    private Short isDeleted;
    private Set<GroupFunctionPermission> groupFunctionPermissions = new LinkedHashSet<>();
    private Set<UserDto> users = new LinkedHashSet<>();
}