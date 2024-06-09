package com.harmonious.foundear.entity.approval;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "mst_approval_level", schema = "approval")
public class ApprovalLevel {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "approval_setup_id", nullable = false)
    private ApprovalSetup approvalSetup;

    @Column(name = "level_name", nullable = false, length = 30)
    private String levelName;

    @Column(name = "number_of_approval", nullable = false, precision = 1)
    private BigDecimal numberOfApproval;

    @Column(name = "approver_domain", nullable = false)
    private UUID approverDomain;

    @Column(name = "rule_statement")
    private String ruleStatement;

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

    @OneToMany(mappedBy = "approvalLevel")
    private Set<ApprovalLevelApprover> approvalLevelApprovers = new LinkedHashSet<>();

}