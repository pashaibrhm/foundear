CREATE TABLE IF NOT EXISTS approval.mst_approval_level_approvers
(
  approval_level_approver_id UUID                 NOT NULL
    CONSTRAINT "PK_MstApprovalLevelApprovers"
      PRIMARY KEY,
  approval_level_id          UUID                 NOT NULL
    CONSTRAINT "FK_MstApprovalLevelApprovers_ApprovalSetupId_MstApprovalSetups_"
      REFERENCES approval.mst_approval_levels,
  entity_id                  UUID                 NOT NULL,
  entity_type                UUID                 NOT NULL,
  priority_level             NUMERIC(1) DEFAULT 1 NOT NULL,
  created_by                 UUID                 NOT NULL,
  created_at                 TIMESTAMP            NOT NULL,
  last_updated_by            UUID                 NOT NULL,
  last_updated_at            TIMESTAMP            NOT NULL,
  last_approved_by           UUID,
  last_approved_at           TIMESTAMP,
  last_version_at            TIMESTAMP            NOT NULL,
  is_deleted                 SMALLINT   DEFAULT 0 NOT NULL
);

ALTER TABLE approval.mst_approval_level_approvers
  OWNER TO postgres;

