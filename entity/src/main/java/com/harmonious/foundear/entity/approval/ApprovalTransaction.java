package com.harmonious.foundear.entity.approval;

import com.harmonious.foundear.entity.user.Function;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "trx_approval_transaction", schema = "approval")
public class ApprovalTransaction {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "approval_setup_id", nullable = false)
    private ApprovalSetup approvalSetup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "function_id", nullable = false)
    private Function function;

    @Column(name = "trx_status", nullable = false, length = 10)
    private String trxStatus;

    @Column(name = "serialized_data", nullable = false)
    private String serializedData;

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

}