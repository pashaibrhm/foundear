package com.harmonious.foundear.dto.auth.group;

import com.harmonious.foundear.dto.auth.user.UserDto;
import com.harmonious.foundear.entity.auth.Group;
import com.harmonious.foundear.entity.auth.GroupFunctionPermission;
import com.harmonious.foundear.entity.auth.Organization;
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
 * DTO for {@link Group}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDto implements Serializable {
    private UUID id;
    private UUID parentId;
    private transient Organization org;
    private String name;
    private UUID createdBy;
    private Instant createdAt;
    private UUID lastUpdatedBy;
    private Instant lastUpdatedAt;
    private UUID approvedBy;
    private Instant approvedAt;
    private Instant lastVersionAt;
    private Short isDeleted;
    @Builder.Default
    private transient Set<GroupFunctionPermission> groupFunctionPermissions = new LinkedHashSet<>();
    private transient Set<UserDto> users = new LinkedHashSet<>();
}