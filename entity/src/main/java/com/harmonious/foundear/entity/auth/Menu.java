package com.harmonious.foundear.entity.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "mst_menu", schema = "auth")
public class Menu {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "parent_id", nullable = false)
    private UUID parentId;

    @Column(name = "name", nullable = false, length = 50)
    private String menuName;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "last_updated_by", nullable = false)
    private UUID lastUpdatedBy;

    @Column(name = "last_updated_at", nullable = false)
    private Instant lastUpdatedAt;

    @Column(name = "last_approved_by")
    private UUID lastApprovedBy;

    @Column(name = "last_approved_at")
    private Instant lastApprovedAt;

    @Column(name = "last_version_at", nullable = false)
    private Instant lastVersionAt;

    @ColumnDefault("1")
    @Column(name = "is_active", nullable = false)
    private Short isActive;

    @ColumnDefault("0")
    @Column(name = "is_deleted", nullable = false)
    private Short isDeleted;

    @OneToMany(mappedBy = "menu")
    private Set<Function> functions = new LinkedHashSet<>();

    @Column(name = "code", nullable = false, length = 30)
    private String code;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

}