package com.harmonious.foundear.entity.approval;

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
@Table(name = "mst_approval_setups", schema = "approval")
public class ApprovalSetup {
    @Id
    @Column(name = "approval_setup_id", nullable = false)
    private UUID id;

    @Column(name = "setup_name", nullable = false, length = 30)
    private String setupName;

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

    @ColumnDefault("0")
    @Column(name = "is_deleted", nullable = false)
    private Short isDeleted;

    @OneToMany(mappedBy = "approvalSetup")
    private Set<ApprovalFunction> approvalFunctions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "approvalSetup")
    private Set<ApprovalLevel> approvalLevels = new LinkedHashSet<>();

    @OneToMany(mappedBy = "approvalSetup")
    private Set<ApprovalTransaction> approvalTransactions = new LinkedHashSet<>();

}