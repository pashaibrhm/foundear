CREATE TABLE IF NOT EXISTS approval.mst_approval_levels
(
  approval_level_id  UUID               NOT NULL
    CONSTRAINT "PK_MstApprovalLevels"
      PRIMARY KEY,
  approval_setup_id  UUID               NOT NULL
    CONSTRAINT "FK_MstApprovalLevels_ApprovalSetupId_MstApprovalSetups_Approval"
      REFERENCES approval.mst_approval_setups,
  level_name         VARCHAR(30)        NOT NULL,
  number_of_approval NUMERIC(1)         NOT NULL,
  approver_domain    UUID               NOT NULL,
  rule_statement     VARCHAR(255),
  created_by         UUID               NOT NULL,
  created_at         TIMESTAMP          NOT NULL,
  last_updated_by    UUID               NOT NULL,
  last_updated_at    TIMESTAMP          NOT NULL,
  last_approved_by   UUID,
  last_approved_at   TIMESTAMP,
  last_version_at    TIMESTAMP          NOT NULL,
  is_deleted         SMALLINT DEFAULT 0 NOT NULL
);

ALTER TABLE approval.mst_approval_levels
  OWNER TO postgres;

