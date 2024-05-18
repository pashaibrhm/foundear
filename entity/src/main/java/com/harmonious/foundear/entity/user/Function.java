package com.harmonious.foundear.entity.user;

import com.harmonious.foundear.entity.approval.ApprovalFunction;
import com.harmonious.foundear.entity.approval.ApprovalTransaction;
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
@Table(name = "mst_functions", schema = "foundear")
public class Function {
    @Id
    @Column(name = "function_id", nullable = false)
    private UUID id;

    @Column(name = "parent_id", nullable = false)
    private UUID parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "function_name", nullable = false, length = 50)
    private String functionName;

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

    @OneToMany(mappedBy = "function")
    private Set<ApprovalFunction> approvalFunctions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "function")
    private Set<ApprovalTransaction> approvalTransactions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "function")
    private Set<GroupFunctionPermission> groupFunctionPermissions = new LinkedHashSet<>();

}