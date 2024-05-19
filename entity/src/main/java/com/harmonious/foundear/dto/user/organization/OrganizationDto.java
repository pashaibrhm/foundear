package com.harmonious.foundear.dto.user.organization;

import com.harmonious.foundear.dto.user.group.GroupDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.harmonious.foundear.entity.user.Organization}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto implements Serializable {
    private UUID id;
    private String name;
    private String email;
    private UUID createdBy;
    private Instant createdAt;
    private UUID lastUpdatedBy;
    private Instant lastUpdatedAt;
    private UUID approvedBy;
    private Instant approvedAt;
    private Instant lastVersionAt;
    private Short isDeleted;
    private Set<GroupDto> groups = new LinkedHashSet<>();
}