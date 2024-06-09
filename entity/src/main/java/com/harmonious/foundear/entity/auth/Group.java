package com.harmonious.foundear.entity.auth;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mst_group", schema = "auth")
public class Group {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "parent_id")
    private UUID parentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization org;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "last_updated_by", nullable = false)
    private UUID lastUpdatedBy;

    @Column(name = "last_updated_at", nullable = false)
    private Instant lastUpdatedAt;

    @Column(name = "approved_by")
    private UUID approvedBy;

    @Column(name = "approved_at")
    private Instant approvedAt;

    @Column(name = "last_version_at", nullable = false)
    private Instant lastVersionAt;

    @ColumnDefault("0")
    @Column(name = "is_deleted", nullable = false)
    private Short isDeleted;

    @OneToMany(mappedBy = "group")
    private Set<GroupFunctionPermission> groupFunctionPermissions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "group")
    private Set<User> users = new LinkedHashSet<>();

}